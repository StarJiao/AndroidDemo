package com.star.demo.application;

import com.star.demo.R;

public class AppUtil
{
    public static ThemeId themeId = ThemeId.BLUE;
    
    public static int getCurrentTheme()
    {
        switch (themeId)
        {
            case BLACK:
                return R.style.MyTheme_Black;
            case BLUE:
                return R.style.MyTheme_Blue;
            default:
                return R.style.AppTheme;
        }
    }
    
    enum ThemeId
    {
        DEFAULT, BLACK, BLUE
    }
}
