package com.star.demo.packageinatallreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PackageInstallReceiver extends BroadcastReceiver
{
    @SuppressWarnings("deprecation")
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction()))
        {
            Toast.makeText(context, "有应用被添加", Toast.LENGTH_LONG).show();
        }
        else if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction()))
        {
            Toast.makeText(context, "有应用被删除", Toast.LENGTH_LONG).show();
        }
        else if (Intent.ACTION_PACKAGE_CHANGED.equals(intent.getAction()))
        {
            Toast.makeText(context, "有应用被改变", Toast.LENGTH_LONG).show();
        }
        else if (Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction()))
        {
            Toast.makeText(context, "有应用被替换", Toast.LENGTH_LONG).show();
        }
        else if (Intent.ACTION_PACKAGE_RESTARTED.equals(intent.getAction()))
        {
            Toast.makeText(context, "有应用被重启", Toast.LENGTH_LONG).show();
        }
        else if (Intent.ACTION_PACKAGE_INSTALL.equals(intent.getAction()))
        {
            Toast.makeText(context, "有应用被安装", Toast.LENGTH_LONG).show();
        }
    }
}
