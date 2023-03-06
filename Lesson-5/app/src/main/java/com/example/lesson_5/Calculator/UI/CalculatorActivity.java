package com.example.lesson_5.Calculator.UI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson_5.Calculator.model.CalculatorImpl;
import com.example.lesson_5.Calculator.model.Operator;
import com.example.lesson_5.Calculator.model.Theme;
import com.example.lesson_5.Calculator.model.ThemeRepository;
import com.example.lesson_5.Calculator.model.ThemeRepositoryImpl;
import com.example.lesson_5.R;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {
    private ThemeRepository themeRepository;
    private TextView resultTxt;
    public static SharedPreferences preferences;
    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        themeRepository = ThemeRepositoryImpl.getINSTANCE(this);
        setTheme(Theme.ONE.getThemeRes());
        resultTxt = findViewById(R.id.result);

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
        Button themeOne = findViewById(R.id.theme1);
        Button themeTwo = findViewById(R.id.theme2);
        Button themeThree = findViewById(R.id.theme3);
        if( themeOne != null){
            themeOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    themeRepository.saveTheme(Theme.ONE);

                    recreate();
                }
            });
        }
        if( themeTwo != null){
            themeTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    themeRepository.saveTheme(Theme.TWO);

                    recreate();
                }
            });
        }
        if( themeThree != null){
            themeThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    themeRepository.saveTheme(Theme.THREE);

                    recreate();
                }
            });
        }
    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}