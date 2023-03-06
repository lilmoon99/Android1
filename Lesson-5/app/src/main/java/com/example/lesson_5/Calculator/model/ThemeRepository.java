package com.example.lesson_5.Calculator.model;

import java.util.List;

public interface ThemeRepository {

    Theme getSavedTheme();

    void saveTheme(Theme theme);

    List<Theme> gatAll();
}
