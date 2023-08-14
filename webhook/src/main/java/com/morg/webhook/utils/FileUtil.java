package com.morg.webhook.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FileUtil {
    private final String folderPath;
    private final Context context;
    private String TAG = FileUtil.class.getSimpleName();

    public FileUtil(Context context) {
        this.context = context;
        folderPath = getApplicationName() + " " + dateFormat();
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

}
