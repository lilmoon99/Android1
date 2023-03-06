package com.example.lesson_5.Calculator.model;

import static com.example.lesson_5.Calculator.UI.CalculatorActivity.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ThemeRepositoryImpl implements ThemeRepository {



    private static ThemeRepository INSTANCE;
    private static final String KEY_THEME = "KEY_THEME";

    public ThemeRepositoryImpl(Context context) {
        preferences = context.getSharedPreferences("theme.xml",Context.MODE_PRIVATE);
    }

    @Override
    public Theme getSavedTheme() {
        @NotNull
        String savedKey = preferences.getString(KEY_THEME,Theme.ONE.getKey());

        for (Theme theme : Theme.values()) {
            if (theme.getKey().equals(savedKey)) {
                return theme;
            }
        }
        return Theme.ONE;
    }

    @Override
    public void saveTheme(Theme theme) {
        preferences.edit().putString(KEY_THEME, theme
                        .getKey())
                .apply();
    }

    @Override
    public List<Theme> gatAll() {
        return Arrays.asList(Theme.values());
    }

    public static ThemeRepository getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ThemeRepositoryImpl(context);
        }
        return INSTANCE;
    }
}
