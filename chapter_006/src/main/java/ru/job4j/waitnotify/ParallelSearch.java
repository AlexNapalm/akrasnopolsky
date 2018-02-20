package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@ThreadSafe
public class ParallelSearch {
    /**
     * Path from where to search.
     */
    private final String root;
    /**
     * Text to search.
     */
    private final String text;
    /**
     * List of extensions to search.
     */
    private final List<String> exts;
    private volatile boolean isFinished = false;

    private Thread search = null;
    private Thread read = null;

    @GuardedBy("this")
    private final Queue<String> files = new LinkedList<>();

    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();


    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public void startSearch() {
        Path startDir = Paths.get(root);
        try {
            Files.walkFileTree(startDir, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
        isFinished = true;
    }
    @GuardedBy("this")
    public synchronized void startRead() {
        while (!isFinished || !this.filesIsEmpty()) {
            String string;
            if (!this.filesIsEmpty()) {
                File file = new File(pollFile());
                try {
                    FileInputStream fstream = new FileInputStream(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    while ((string = br.readLine()) != null) {
                        if (string.contains(text)) {
                            this.addPath(file.toString());
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    public void init() {
        this.search = new Thread() {
            @Override
            public void run() {
                startSearch();
            }
        };
        this.read = new Thread() {
            @Override
            public void run() {
                startRead();
            }
        };
    }
    @GuardedBy("this")
    public synchronized Queue<String> getFiles() {
        return this.files;
    }
    @GuardedBy("this")
    public synchronized List<String> getResult() {
        return this.paths;
    }
    @GuardedBy("this")
    public synchronized void offer(String filePath) {
        this.files.offer(filePath);
    }
    @GuardedBy("this")
    public synchronized String pollFile() {
        return this.files.poll();
    }
    @GuardedBy("this")
    public synchronized void addPath(String path) {
        this.paths.add(path);
    }
    @GuardedBy("this")
    public synchronized boolean filesIsEmpty() {
        return this.files.isEmpty();
    }

    public String getExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    /**
     * Class implementing FileVisitor methods to search through dir tree.
     */
    public class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            // skip service dirs like .git
            if (dir.toString().contains(".")) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (exts.contains(getExtension(file.toString()))) {
                offer(file.toString());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> exts = new ArrayList<>(Arrays.asList("txt", "uml"));
        String root = "R:\\Java Projects\\akrasnopolsky";
        String text = "job4j";
        ParallelSearch ps = new ParallelSearch(root, text, exts);
        ps.init();

        ps.search.start();
        ps.read.start();
        ps.search.join();
        ps.read.join();

        List<String> result = ps.getResult();
        System.out.println("Result:");
        for (String path : result) {
            System.out.println(path);
        }
    }
}



