package com.example.lesson_3_hw.UI;


import com.example.lesson_3_hw.model.Calculator;
import com.example.lesson_3_hw.model.Operator;

import java.text.DecimalFormat;

public class CalculatorPresenter {

    private DecimalFormat formater = new DecimalFormat();

    private final CalculatorView view;
    private final Calculator calculator;

    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;
    private Operator lastOperator;
    private double result;


    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if (argTwo == null) {

            argOne = argOne * 10 + digit;

            showFormatted(argOne);
        } else {
            argTwo = argTwo * 10 + digit;

            showFormatted(argTwo);
        }
    }


    public void onOperatorPressed(Operator operator) {

        if (selectedOperator != null) {

            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
            result = argOne;
        }
        selectedOperator = operator;
        argTwo = 0.0;

    }

    public void onDotPressed() {

    }

    private void showFormatted(double value) {
        view.showResult(formater.format(value));
    }

    public void onEqualPressed(Operator operator) {
        if (operator == Operator.EQUAL_TO){
            showFormatted(result);
            selectedOperator = null;
        }
    }
}
