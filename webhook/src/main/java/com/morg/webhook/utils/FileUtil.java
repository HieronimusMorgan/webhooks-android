package com.morg.webhook.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
    private final String folderPath;
    private final Context context;
    private final String TAG = FileUtil.class.getSimpleName();

    public FileUtil(Context context) {
        this.context = context;
        folderPath = getApplicationName() + " " + dateFormat();
    }

    public static void writeToFile(String data, File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            Log.e("FileUtil", "Error writing file", e);
        }
    }

    private String getApplicationName() {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    public File getAbsoluteFolder() {
        return new File(context.getFilesDir().toString() + "/export/" + folderPath);
    }

    public File getAbsoluteFile() {
        File dir = getAbsoluteFolder();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, folderPath + ".zip");
    }

    public String[] getFileFromFolder(String path) {
        List<String> temp = new ArrayList<>();
        try {
            File file = new File(path);
            File[] files = file.listFiles();
            for (File f : files) {
                temp.add(f.getAbsolutePath());
            }
        } catch (Exception e) {
            Log.e(TAG, "getFileFromFolder: ", e);
        }

        String[] stringArray = new String[temp.size()];
        stringArray = temp.toArray(stringArray);
        return stringArray;
    }

    public void copy(File source, File destination) {
        try (InputStream in = new FileInputStream(source)) {
            try (OutputStream out = new FileOutputStream(destination)) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void deleteFiles() {
        try {
            File file = new File(context.getFilesDir().toString() + "/export");

            if (file.exists()) {
                String deleteCmd = "rm -r " + file.getAbsolutePath();
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec(deleteCmd);
                } catch (IOException e) {
                    Log.e(TAG, "deleteFiles: ", e);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "deleteFile: ", e);
        }
    }

    private String dateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", new Locale("in", "ID"));
        return sdf.format(new Date());
    }

    public static void zip(String[] _files, String zipFileName) {
        try (FileOutputStream dest = new FileOutputStream(zipFileName); ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                dest))) {
            byte[] data = new byte[2048];
            for (String file : _files) {
                Log.v("", "Adding: " + file);
                try (FileInputStream fi = new FileInputStream(file); BufferedInputStream origin = new BufferedInputStream(fi, 2048)) {
                    ZipEntry entry = new ZipEntry(file.substring(file.lastIndexOf("/") + 1));
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
}
