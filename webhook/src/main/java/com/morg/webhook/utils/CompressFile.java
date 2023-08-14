package com.morg.webhook.utils;

import android.util.Log;

import com.morg.webhook.utils.callback.DeleteCallback;
import com.morg.webhook.utils.callback.ZipCallback;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFile {

    public static void deleteAllFile(String folderPath, DeleteCallback deleteCallback) {
        try {
            File file = new File(folderPath);
            File[] files = file.listFiles();

            for (File temp : files) {
                if (temp.exists()) {
                    if (temp.delete()) {
                        Log.d("File", "File berhasil dihapus");
                    }
                }
            }
            deleteCallback.delete("Success");
        } catch (Exception e) {
            Log.e("deleteAllFile", e.toString());
            deleteCallback.delete("Failed");
        }
    }

    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.delete()) {
                Log.d("File", "File berhasil dihapus");
            }
        } catch (Exception e) {
            Log.e("deleteFile", e.toString());
        }
    }

    public static void deleteFile(List<String> filePaths) {
        try {
            for (String path : filePaths) {
                File file = new File(path);
                if (file.delete()) {
                    Log.d("File", "File berhasil dihapus");
                }
            }
        } catch (Exception e) {
            Log.e("deleteFile", e.toString());
        }
    }

    public static void zip(String[] _files, String zipFileName) {
        try (FileOutputStream dest = new FileOutputStream(zipFileName); ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                dest))) {


            byte[] data = new byte[2048];

            for (int i = 0; i < _files.length; i++) {
                Log.v("", "Adding: " + _files[i]);
                try (FileInputStream fi = new FileInputStream(_files[i]); BufferedInputStream origin = new BufferedInputStream(fi, 2048)) {
                    ZipEntry entry = new ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1));
                    out.putNextEntry(entry);
                    int count;

                    while ((count = origin.read(data, 0, 2048)) != -1) {
                        out.write(data, 0, count);
                    }
                } catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }
        } catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
    }

    public static void zip(String[] _files, String zipFileName, String salesmanID, ZipCallback zipCallback) {
        try (FileOutputStream dest = new FileOutputStream(zipFileName);
             ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                     dest))) {
            byte[] data = new byte[2048];

            for (int i = 0; i < _files.length; i++) {
                Log.v("Compress", "Adding: " + _files[i]);
                try (FileInputStream fi = new FileInputStream(_files[i]); BufferedInputStream origin = new BufferedInputStream(fi, 2048)) {
                    ZipEntry entry = new ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1));
                    out.putNextEntry(entry);
                    int count;

                    while ((count = origin.read(data, 0, 2048)) != -1) {
                        out.write(data, 0, count);
                    }
                } catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }
            zipCallback.compressSuccess("Sucess");
        } catch (Exception e) {
            Log.e("ERROR", e.toString());
            if (zipFileName.equals("/storage/emulated/0/NX2OUTPUT/NOO_PHOTO/imageNOO.zip") || zipFileName.equals("/storage/emulated/0/NX2OUTPUT/NXORDERPICTURE" + salesmanID + ".zip")) {
                zipCallback.compressSuccess("NextFile");
            } else {
                zipCallback.compressFailed("Failed");
            }
        }
    }

    public static void deleteFile(String filePath, String fileName) {
        File file = new File(filePath, fileName);

        if (file.exists()) {
            if (file.delete()) {
                Log.d("File", "File berhasil dihapus");
            }
        }
    }

    public static boolean checkFolderIsEmpty(String path) {
        boolean empty = false;
        File directory = new File(path);
        File[] contents = directory.listFiles();

        // the directory file is not really a directory..
        // Folder contains files
        if (contents == null) {
            empty = true;
        }
        // Folder is empty
        else empty = contents.length == 0;
        return empty;
    }
}
