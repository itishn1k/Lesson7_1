package com.example.lesson7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CalculatorModel calculator;

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer[] numberIds = new Integer[]{
                R.id.btn_zero,
                R.id.btn_one,
                R.id.btn_two,
                R.id.btn_three,
                R.id.btn_four,
                R.id.btn_five,
                R.id.btn_six,
                R.id.btn_seven,
                R.id.btn_eight,
                R.id.btn_nine
        };

        Integer[] actionsIds = new Integer[]{
                R.id.btn_action_plus,
                R.id.btn_action_minus,
                R.id.btn_action_multiply,
                R.id.btn_action_division,
                R.id.btn_action_equals
        };

        text = findViewById(R.id.tv_result);

        calculator = new CalculatorModel();

        View.OnClickListener numberButtonClickListener = view -> {
            calculator.onNumPressed(view.getId());
            text.setText(calculator.getText());
        };

        View.OnClickListener actionButtonOnclickListener = view -> {
            calculator.onActionPressed(view.getId());
            text.setText(calculator.getText());
        };

        for (Integer numberId : numberIds) {
            findViewById(numberId).setOnClickListener(numberButtonClickListener);
        }

        for (Integer actionsId : actionsIds) {
            findViewById(actionsId).setOnClickListener(actionButtonOnclickListener);
        }

        findViewById(R.id.btn_clear).setOnClickListener(view -> {
            calculator.reset();
            text.setText(calculator.getText());
        });
    }
}