package ru.job4j.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class ParallelSearch {

    private final Lock lock = new ReentrantLock();
    /**
     * Start of the search.
     */
    private String root;
    /**
     * Text to search in the file.
     */
    private String text;
    /**
     * List of extensions
     */
    @GuardedBy("lock")
    private List<String> exts;

    /**
     * Result list, contains paths to the files.
     */
    private List<String> result = new ArrayList<>();

    private ExecutorService executor;

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        int cores = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(cores);
    }

    /**
     * Starts the searching.
     */
    public synchronized void search() throws InterruptedException, ExecutionException {
        File file = new File(this.root);
        directorySearch(file);
        executor.shutdown();
        while (!executor.isTerminated()) {
            continue;
        }
    }

    /**
     * Recursive folders search
     * @param path path
     */
    @GuardedBy("lock")
    private void directorySearch(File path) throws InterruptedException, ExecutionException {
        synchronized (this.lock) {
            if (path.list() != null) {
                for (File file : path.listFiles()) {
                    if (file.isFile()) {
                        if (exts.contains(getExtension(file))) {
                            executor.execute(new TextFinder(this, this.text, file.getPath()));
                        }
                    } else {
                        directorySearch(file);
                    }
                }
            }
        }
    }

    /**
     * Gets file extension.
     */
    private String getExtension(File file) {
        String extension = "";
        String fileName = file.getName();

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return extension;
    }

    /**
     * adds path to result list.
     * @param path path.
     */
    @GuardedBy("lock")
    public void addResult(String path) {
        synchronized (this.lock) {
            this.result.add(path);
        }
    }

    public List<String> getResult() {
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> exts = new ArrayList<>(Arrays.asList("txt", "java", "class"));
        ParallelSearch ps = new ParallelSearch("R:\\Java Projects\\", "job4j for life", exts);
        ps.search();

        List<String> result = ps.getResult();
        System.out.println("Result:");
        for (String path : result) {
            System.out.println(path);
        }
    }
}