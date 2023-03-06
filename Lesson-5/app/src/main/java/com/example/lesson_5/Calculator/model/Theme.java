package com.example.lesson_5.Calculator.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.example.lesson_5.R;

public enum Theme {
    ONE(R.style.CalculatorTheme, R.string.theme_1, "1"),
    TWO(R.style.CalculatorThemeDark, R.string.theme_2, "2"),
    THREE(R.style.CalculatorThemeAlternate, R.string.theme_3, "3");
    @StyleRes
    private int themeRes;
    @StringRes
    private int title;
    private String key;
    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
