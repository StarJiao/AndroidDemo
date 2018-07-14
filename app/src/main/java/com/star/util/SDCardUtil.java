package com.star.util;

import android.os.Environment;

import java.io.File;

public class SDCardUtil
{
    public static File getSDCardPath()
    {
        File sdDir = null;
        if (sdCardExist())
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录 
        }
        return sdDir;
    }
    
    public static boolean sdCardExist()
    {
        return Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在 
    }
}
