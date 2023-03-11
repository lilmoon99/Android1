package com.example.calculatoractivity.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.calculatoractivity.R;
import com.example.calculatoractivity.model.CalculatorImpl;
import com.example.calculatoractivity.model.Operator;
import com.google.android.material.button.MaterialButton;
import java.util.HashMap;
import java.util.Map;


public class CalculatorActivity extends AppCompatActivity implements CalculatorView {
    public static final int THEME_1 = 1;
    public static final int THEME_2 = 2;
    public static final int THEME_3 = 3;
    private static final String APP_PREFERENCES = "MYSETTINGS";
    public static final String THEME_CODE = "Theme";
    public TextView resultTxt;
    private CalculatorPresenter presenter;
    MaterialButton settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyTheme1));
        setContentView(R.layout.activity_calculator);
        resultTxt = findViewById(R.id.result);
        settingsBtn = findViewById(R.id.settings);

        ActivityResultLauncher<Intent> settingsActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()== Activity.RESULT_OK){
                    Intent intent = result.getData();
                    assert intent != null;
                    int themeCode = intent.getIntExtra("Theme",0);
                    setAppTheme(themeCode);
                    recreate();
                }
            }
        });
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(CalculatorActivity.this, SettingsActivity.class);
                settingsActivityResultLauncher.launch(settings);
            }
        });

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);
        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };

        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_add, Operator.ADD);
        operators.put(R.id.key_sub, Operator.SUB);
        operators.put(R.id.key_mul, Operator.MULT);
        operators.put(R.id.key_div, Operator.DIV);
        operators.put(R.id.key_equal_to, Operator.EQUAL_TO);

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
                presenter.onEqualPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.key_add).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_sub).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_mul).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_div).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_equal_to).setOnClickListener(operatorClickListener);

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDotPressed();
            }
        });

    }


    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
//
//    private void setAppTheme(int codeStyle) {
//        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit().putInt(THEME_CODE, MODE_PRIVATE);
//        editor.apply();
//    }
    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }
    // Чтение настроек, параметр «тема»
    private int getCodeStyle(int codeStyle){
// Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES,
                MODE_PRIVATE);
//Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(THEME_CODE, codeStyle);
    }
    // Сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(THEME_CODE, codeStyle);
        editor.apply();
    }
    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case THEME_1:
                return R.style.MyTheme1;
            case THEME_2:
                return R.style.MyTheme2;
            case THEME_3:
                return R.style.MyTheme3;
            default:
                return R.style.MyTheme1;
        }
    }
}