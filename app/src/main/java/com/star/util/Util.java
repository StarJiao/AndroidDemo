package com.star.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * Created by Star on 2016/6/24.
 */
public class Util {
    public static String getIMEI(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    //尝试打开wifi
    private static boolean tryOpenMAC(WifiManager manager) {
        boolean softOpenWifi = false;
        int state = manager.getWifiState();
        if (state != WifiManager.WIFI_STATE_ENABLED && state != WifiManager.WIFI_STATE_ENABLING) {
            manager.setWifiEnabled(true);
            softOpenWifi = true;
        }
        return softOpenWifi;
    }

    //尝试关闭MAC
    private static void closeWifi(WifiManager manager) {
        manager.setWifiEnabled(false);
    }

    //尝试获取MAC地址
    private static String getMacAddress(WifiManager manager) {
        WifiInfo wifiInfo = manager.getConnectionInfo();
        if (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getMacAddress())) {
            return null;
        }
        return wifiInfo.getMacAddress().replaceAll(":", "").trim().toUpperCase();
    }

    //尝试读取MAC地址
    public static String getMacAddress(Context context) {
        int internal = 20;
        String mac;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        mac = getMacAddress(wifiManager);
        if (!TextUtils.isEmpty(mac)) {
            return mac;
        }

        //获取失败，尝试打开wifi获取
        boolean isWifiOpen = tryOpenMAC(wifiManager);
        for (int index = 0; index < internal; index++) {
            //如果第一次没有成功，第二次做100毫秒的延迟。
            if (index != 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mac = getMacAddress(wifiManager);
            if (!TextUtils.isEmpty(mac)) {
                Log.i("tag", "after " + index + " times ,we get it");
                break;
            }
        }

        //尝试关闭wifi
        if (isWifiOpen) {
            closeWifi(wifiManager);
        }
        return mac;
    }

    /**
     * 生成二维码 要转换的地址或字符串,可以是中文
     *
     * @param url
     * @param width
     * @param height
     * @return
     */
    public static Bitmap createQRImage(String url, final int width, final int height) {
        try {
            // 判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    } else {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
