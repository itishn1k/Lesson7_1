package com.example.lesson7_1;

import android.annotation.SuppressLint;

public class CalculatorModel {
    private Integer firstArg;
    private Integer secondArg;

    private final StringBuilder inputStr = new StringBuilder();

    private Integer actionSelected;

    private State state;

    public CalculatorModel() {
        state = State.FIRST_ARG_INPUT;
    }

    public void onNumPressed(Integer buttonId) {

        if (state == State.RESULT_SHOW) {
            state = State.FIRST_ARG_INPUT;
            inputStr.setLength(0);
        }

        if (state == State.OPERATION_SELECTED) {
            state = State.SECOND_ARG_INPUT;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 9) {
            switch (buttonId) {
                case R.id.btn_zero:
                    if (inputStr.length() != 0) {
                        inputStr.append("0");
                    }
                    break;
                case R.id.btn_one:
                    inputStr.append("1");
                    break;
                case R.id.btn_two:
                    inputStr.append("2");
                    break;
                case R.id.btn_three:
                    inputStr.append("3");
                    break;
                case R.id.btn_four:
                    inputStr.append("4");
                    break;
                case R.id.btn_five:
                    inputStr.append("5");
                    break;
                case R.id.btn_six:
                    inputStr.append("6");
                    break;
                case R.id.btn_seven:
                    inputStr.append("7");
                    break;
                case R.id.btn_eight:
                    inputStr.append("8");
                    break;
                case R.id.btn_nine:
                    inputStr.append("9");
                    break;
            }
        }

    }

    public void onActionPressed(Integer actionId) {
        if (actionId == R.id.btn_action_equals && state == State.SECOND_ARG_INPUT && inputStr.length() > 0) {
            secondArg = Integer.parseInt(inputStr.toString());
            state = State.RESULT_SHOW;
            inputStr.setLength(0);
            switch (actionSelected) {
                case R.id.btn_action_plus:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.btn_action_minus:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.btn_action_multiply:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.btn_action_division:
                    inputStr.append(firstArg / secondArg);
                    break;
            }

        } else if (inputStr.length() > 0 && state == State.FIRST_ARG_INPUT && actionId != R.id.btn_action_equals) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.OPERATION_SELECTED;
            actionSelected = actionId;
        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            default:
                return inputStr.toString();
            case OPERATION_SELECTED:

                return str
                        .append(firstArg)

//                        .append(' ')
//                        .append(getOperationChar())
                        .toString();
            case SECOND_ARG_INPUT:
                return str
//                        .append(firstArg).append(' ')
//                        .append(getOperationChar())
                        .append(' ')
                        .append(inputStr).toString();
            case RESULT_SHOW:
                return
                        str
//                                .append(firstArg).append(' ')
//                        .append(getOperationChar())
//                        .append(' ')
//                        .append(secondArg)
//                        .append(" = ") если вернуть закоменченый код будет отображаться
//                        полная операция(firstArg+secondArg=) сейчас отобр только 40
                                .append(inputStr).toString();
        }
    }

    @SuppressLint("NonConstantResourceId")
    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.btn_action_plus:
                return '+';
            case R.id.btn_action_minus:
                return '-';
            case R.id.btn_action_multiply:
                return '*';
            case R.id.btn_action_division:
            default:
                return '/';

        }
    }

    public void reset() {
        state = State.FIRST_ARG_INPUT;
        inputStr.setLength(0);
    }
}
