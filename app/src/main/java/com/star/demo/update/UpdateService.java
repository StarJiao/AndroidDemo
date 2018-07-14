package com.star.demo.update;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class UpdateService extends Service
{
    private static Handler handler;
    
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
    
    public static void startAction(Context ctx, Handler _handler)
    {
        Intent i = new Intent(ctx, UpdateService.class);
        i.setAction("0");
        ctx.startService(i);
        handler = _handler;
        System.out.println("UserID");
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        System.out.println("onStartCommand");
        handler.sendEmptyMessage(1);
        return super.onStartCommand(intent, flags, startId);
    }
}
