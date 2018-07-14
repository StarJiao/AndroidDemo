package com.star.demo.application;

import android.app.Application;
import android.content.SharedPreferences;

import com.star.demo.application.AppUtil.ThemeId;

public class DemoApplication extends Application {
    private static DemoApplication instance;

    public static DemoApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        SharedPreferences sp = getSharedPreferences("ThemeSetting", MODE_PRIVATE);
        int themeId = sp.getInt("ThemeId", 0);
        switch (themeId) {
            case 0:
                AppUtil.themeId = ThemeId.DEFAULT;
                break;
            case 1:
                AppUtil.themeId = ThemeId.BLACK;
                break;
            case 2:
                AppUtil.themeId = ThemeId.BLUE;
                break;
            default:
                break;
        }
    }
}
