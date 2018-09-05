package com.star.util;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void saveBitmapToJpegFile(Bitmap bitmap, String filePath) {
        try {
            File file = new File(filePath);
            checkFileExistOrCreate(file);
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkFileExistOrCreate(File path) {
        File folder = path.getParentFile();
        if (!checkDirectoryExistOrCreate(folder)) {
            return false;
        }
        try {
            if (path.exists()) {
                return path.isFile() || path.delete() && path.createNewFile();
            } else {
                return path.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkDirectoryExistOrCreate(File path) {
        if (path.exists()) {
            return path.isDirectory() || path.delete() && path.mkdirs();
        } else {
            return path.mkdirs();
        }
    }
}
