package com.shadiz.usergin.shadowview.utils;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;


/**
 * Created by oldman on 26.10.16.
 */

public class WalkTree {
    private List<String> getListPath() {
        String sdcard = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        String sdcard2 = "/sdcard2";
        String sdcardMnt = "/mnt/sdcard2";
        String sdcardExt = "/mnt/extSdCard";
        String sdcardSd = "/sdcard/.externalSD";

        List<String> dirList = new ArrayList<String>();
        dirList.add(sdcard);
        dirList.add(sdcard2);
        dirList.add(sdcardMnt);
        dirList.add(sdcardExt);
        dirList.add(sdcardSd);
        return dirList;
    }

    public int isNumberAccount() {
        for (String storage : getListPath()) {
            File file = new File(storage);
            if (file != null)
                if (file.exists() && file.isDirectory()) {
                    int id = walk(file);
                    if (id != -1)
                        return id;
                }
        }
        return -1;
    }

    private int walk(File file) {
        File[] list = file.listFiles();
        if (list != null)
            for (File f : list) {
                if (f.isDirectory()) {
                    walk(f);
                } else {
                    String sID = f.getName();
                    if (sID.indexOf("fg.apk") != -1) {
                        try {
                            return Integer.valueOf(sID.substring(0, sID.indexOf("f")));
                        } catch (NumberFormatException e) {
                            System.err.println("Неверный формат строки!");
                        }
                    }
                }
            }

        return -1;
    }

    public static int findId() {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        try {
            Scheduler single = Schedulers.from(exec);

            CountDownLatch cdl = new CountDownLatch(1);

            getAllFiles(new File("."))
                    .subscribeOn(single)
                    .observeOn(Schedulers.trampoline())
                    .map(WalkTree::checkId)
                    .filter(id -> id > -1)
                    .subscribe(System.out::println, Throwable::printStackTrace, () -> { System.out.println("---"); cdl.countDown(); });

            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
        return -1;
    }

    private  static int checkId(File file) {
        String string = file.getAbsolutePath();
        if (string.contains("fg.apk")) {
            try {
                return Integer.valueOf(string.substring(0, string.indexOf("f")));
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат строки!");
            }
        }
        return -1;
    }

    private static Observable<File[]> getFiles(File parent) {
        return Observable.create(s -> {
            System.out.println(Thread.currentThread() + ": " + parent);
            s.onNext(parent.listFiles());
            s.onCompleted();
        });
    }
    private static Observable<File> getAllFiles(File root) {
        Observable<File[]> ofs = getFiles(root);
        return ofs.flatMap(fs -> {
            Observable<File> result = Observable.empty();
            List<File> regularFiles = new ArrayList<>();
            for (File f : fs) {
                if (f.isDirectory()) {
                    result = result.mergeWith(getAllFiles(f));
                } else {
                    regularFiles.add(f);
                }
            }

            return result.mergeWith(Observable.from(regularFiles));
        });
    }
}
