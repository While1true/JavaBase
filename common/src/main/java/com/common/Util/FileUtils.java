package com.common.Util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * Created by vange on 2017/9/15.
 */

public class FileUtils {

    /***根据文件后缀回去MIME类型****/

    public static String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        return URLConnection.getFileNameMap().getContentTypeFor(file.toString());
    }


    public static boolean isDocFile(File file) {
        String all = "xlsx pptx html docx xml txt pdf";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return false;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex + 1, fName.length()).toLowerCase();

        if (!TextUtils.isEmpty(end) && all.contains(end)) {
            return true;
        }
        return false;
    }

    public static void openBySystem(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        FileProvider7.setIntentDataAndType(context, intent, getMIMEType(file), file, true);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openAssignFolder(Context context, String path) {
        File file = new File(path);
        if (null == file || !file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "file/*");
        try {
            context.startActivity(intent);
            context.startActivity(Intent.createChooser(intent, "选择浏览工具"));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void send(Context context, File file, String type) {
        try {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.addCategory(Intent.CATEGORY_DEFAULT);
            share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            share.putExtra(Intent.EXTRA_STREAM, FileProvider7.getUriForFile(context, file));
            FileProvider7.setIntentData(context, share, file, true);
            share.setDataAndType(FileProvider7.getUriForFile(context, file), type);
            context.startActivity(Intent.createChooser(share, "分享到"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(Context context, File file) {
        send(context, file, getMIMEType(file));
    }

    public static void sendText(Context context, String text) {
        try {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.addCategory(Intent.CATEGORY_DEFAULT);
            share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            share.putExtra(Intent.EXTRA_TEXT, text);
            share.setType("text/plain");
            context.startActivity(Intent.createChooser(share, "分享到"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static File saveImageToGallery(Context context, Bitmap bmp, String filename) {
        // 首先保存图片
        File appDir = new File(MemoryUtils.FILE, filename);
        if (appDir.exists() && appDir.getTotalSpace() != 0) {
            return appDir;
        }
        if (!appDir.getParentFile().exists()) {
            appDir.getParentFile().mkdir();
        }
        try {
            FileOutputStream fos = new FileOutputStream(appDir);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    appDir.getAbsolutePath(), filename, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + filename)));
        return appDir;
    }


    public static File Uri2File(Context context, Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = context.getContentResolver().query(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        return new File(img_path);
    }

    /**
     * 写入文件
     *
     * @param file
     */
    public static void writeFile(InputStream is, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[4096];
            while (-1 != (len = is.read(buffer))) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
