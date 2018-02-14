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
        while (!isFinished || !files.isEmpty()) {
            String string;
            if (!files.isEmpty()) {
                File file = new File(files.poll());
                try {
                    FileInputStream fstream = new FileInputStream(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    while ((string = br.readLine()) != null) {
                        if (string.contains(text)) {
                            paths.add(file.toString());
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

    synchronized Queue<String> getFiles() {
        return this.files;
    }
    @GuardedBy("this")
    synchronized List<String> result() {
        return this.paths;
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
    @ThreadSafe
    public class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            // skip service dirs like .git
            if (dir.toString().contains(".")) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }
        @GuardedBy("this")
        @Override
        public synchronized FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (exts.contains(getExtension(file.toString()))) {
                files.offer(file.toString());
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
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

        List<String> result = ps.result();
        System.out.println("Result:");
        for (String path : result) {
            System.out.println(path);
        }
    }
}



