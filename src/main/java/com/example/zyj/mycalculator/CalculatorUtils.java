package com.example.zyj.mycalculator;

/**
 * Created by Administrator on 2016/6/16.
 */
public class CalculatorUtils {

    public static final int PLUS = 0;
    public static final int MINUS = 1;
    public static final int MULTIPLY = 2;
    public static final int DIVIDE = 3;
    public static final int NONE = -1;
    public static final int ASD = -999;

    public static double calculate(double x, double y, int operation) {
        double result = 0;
        switch (operation) {
            case PLUS:
                result = plus(x, y);
                break;
            case MINUS:
                result = minus(x, y);
                break;
            case MULTIPLY:
                result = multiply(x, y);
                break;
            case DIVIDE:
                result = divide(x, y);
                break;
            case NONE:
                result = y;
                break;
        }
        return result;
    }

    public static double plus(double x, double y) {
        return x + y;
    }


    public static double minus(double x, double y) {
        return x - y;
    }


    public static double multiply(double x, double y) {
        return x * y;
    }

    public static double divide(double x, double y) {
        return x / y;
    }
}
