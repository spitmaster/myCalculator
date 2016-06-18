package com.example.zyj.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_plus;
    private Button btn_minus;
    private Button btn_multiply;
    private Button btn_divide;
    private Button btn_equal;
    private Button btn_c;
    private Button btn_ac;
    private Button btn_point;

    private EditText et_display;

    private String displaying = "0";
    private String formerNumber = "";
    private String secondNumber = "";
    private boolean isEditingFirst = true;  // true表示正在编辑第一个数字，false表示正在编辑第二个数字
    private boolean isFirstNumberDouble = false;
    private boolean isFirstNumberProvided = false;
    private boolean isSecondNumberDouble = false;
    private boolean isSecondNumberProvided = false;
    private boolean isDisplayingDouble = false;
    private int operation = CalculatorUtils.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        et_display = (EditText) findViewById(R.id.et_display);
        et_display.setText("0");
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_c = (Button) findViewById(R.id.btn_c);
        btn_ac = (Button) findViewById(R.id.btn_ac);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_ac.setOnClickListener(this);
        btn_point.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                pressNumber(0);
                break;
            case R.id.btn_1:
                pressNumber(1);
                break;
            case R.id.btn_2:
                pressNumber(2);
                break;
            case R.id.btn_3:
                pressNumber(3);
                break;
            case R.id.btn_4:
                pressNumber(4);
                break;
            case R.id.btn_5:
                pressNumber(5);
                break;
            case R.id.btn_6:
                pressNumber(6);
                break;
            case R.id.btn_7:
                pressNumber(7);
                break;
            case R.id.btn_8:
                pressNumber(8);
                break;
            case R.id.btn_9:
                pressNumber(9);
                break;
            case R.id.btn_point:
                pressPoint();
                break;


            case R.id.btn_plus:
                pressPlus();
                break;
            case R.id.btn_minus:
                pressMinus();
                break;
            case R.id.btn_multiply:
                pressMultiply();
                break;
            case R.id.btn_divide:
                pressDivide();
                break;
            case R.id.btn_ac:
                pressAC();
                break;
            case R.id.btn_c:
                pressC();
                break;
            case R.id.btn_equal:
                pressEqual();

                if (isFirstNumberProvided && isSecondNumberProvided) {
                    double resault = CalculatorUtils.calculate(new Double(formerNumber), new Double(secondNumber), operation);
                    formerNumber = "" + resault;
                    displaying = formerNumber;
                    et_display.setText(displaying);
                    secondNumber = "";
                    isFirstNumberProvided = true;
                    isSecondNumberProvided = false;
                    isSecondNumberDouble = false;
                }
                break;

        }

    }

    private void pressEqual() {
        if (!isFirstNumberProvided) {   //如果第一个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = true;
        } else if (!isSecondNumberProvided && isEditingFirst) {   //如果第一个数有，第二个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = true;
        } else if (!isSecondNumberProvided && !isEditingFirst) {  //如果第一个数有，第二个数没有，正在输入第二个数，但显示的是第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
        } else if (isSecondNumberProvided && !isEditingFirst) {    //如果两个数都有，正在输入第二个数
            double resault = CalculatorUtils.calculate(new Double(formerNumber), new Double(secondNumber), operation);
            formerNumber = "" + resault;
            displaying = formerNumber;
            et_display.setText(displaying);
            secondNumber = "";
            isFirstNumberProvided = true;
            isSecondNumberProvided = false;
            isSecondNumberDouble = false;
            operation = CalculatorUtils.NONE;
        }
    }


    private void pressPlus() {
        if (!isFirstNumberProvided) {   //如果第一个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
            operation = CalculatorUtils.PLUS;
        } else if (!isSecondNumberProvided && isEditingFirst) {   //如果第一个数有，第二个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
            operation = CalculatorUtils.PLUS;
        } else if (!isSecondNumberProvided && !isEditingFirst) {  //如果第一个数有，第二个数没有，正在输入第二个数，但显示的是第一个数
            operation = CalculatorUtils.PLUS;
        } else if (isSecondNumberProvided && !isEditingFirst) {    //如果两个数都有，正在输入第二个数
            operation = CalculatorUtils.PLUS;
            double resault = CalculatorUtils.calculate(new Double(formerNumber), new Double(secondNumber), operation);
            formerNumber = "" + resault;
            displaying = formerNumber;
            et_display.setText(displaying);
            secondNumber = "";
            isFirstNumberProvided = true;
            isSecondNumberProvided = false;
            isSecondNumberDouble = false;
        }
    }

    private void pressDivide() {
        if (!isFirstNumberProvided) {   //如果第一个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
            operation = CalculatorUtils.DIVIDE;
        } else if (!isSecondNumberProvided && isEditingFirst) {   //如果第一个数有，第二个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
            operation = CalculatorUtils.DIVIDE;
        } else if (!isSecondNumberProvided && !isEditingFirst) {  //如果第一个数有，第二个数没有，正在输入第二个数，但显示的是第一个数
            operation = CalculatorUtils.DIVIDE;
        } else if (isSecondNumberProvided && !isEditingFirst) {    //如果两个数都有，正在输入第二个数
            operation = CalculatorUtils.DIVIDE;
            double resault = CalculatorUtils.calculate(new Double(formerNumber), new Double(secondNumber), operation);
            formerNumber = "" + resault;
            displaying = formerNumber;
            et_display.setText(displaying);
            secondNumber = "";
            isFirstNumberProvided = true;
            isSecondNumberProvided = false;
            isSecondNumberDouble = false;
        }
    }

    private void pressMultiply() {
        if (!isFirstNumberProvided) {   //如果第一个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
            operation = CalculatorUtils.MULTIPLY;
        } else if (!isSecondNumberProvided && isEditingFirst) {   //如果第一个数有，第二个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
            operation = CalculatorUtils.MULTIPLY;
        } else if (!isSecondNumberProvided && !isEditingFirst) {  //如果第一个数有，第二个数没有，正在输入第二个数，但显示的是第一个数
            operation = CalculatorUtils.MULTIPLY;
        } else if (isSecondNumberProvided && !isEditingFirst) {    //如果两个数都有，正在输入第二个数
            operation = CalculatorUtils.MULTIPLY;
            double resault = CalculatorUtils.calculate(new Double(formerNumber), new Double(secondNumber), operation);
            formerNumber = "" + resault;
            displaying = formerNumber;
            et_display.setText(displaying);
            secondNumber = "";
            isFirstNumberProvided = true;
            isSecondNumberProvided = false;
            isSecondNumberDouble = false;
        }
    }

    private void pressMinus() {

        if (!isFirstNumberProvided) {   //如果第一个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
            operation = CalculatorUtils.MINUS;
        } else if (!isSecondNumberProvided && isEditingFirst) {   //如果第一个数有，第二个数没有，正在输入第一个数
            formerNumber = displaying;
            isFirstNumberProvided = true;
            isEditingFirst = false;
            operation = CalculatorUtils.MINUS;
        } else if (!isSecondNumberProvided && !isEditingFirst) {  //如果第一个数有，第二个数没有，正在输入第二个数，但显示的是第一个数
            operation = CalculatorUtils.MINUS;
        } else if (isSecondNumberProvided && !isEditingFirst) {    //如果两个数都有，正在输入第二个数
            operation = CalculatorUtils.MINUS;
            double resault = CalculatorUtils.calculate(new Double(formerNumber), new Double(secondNumber), operation);
            formerNumber = "" + resault;
            displaying = formerNumber;
            et_display.setText(displaying);
            secondNumber = "";
            isFirstNumberProvided = true;
            isSecondNumberProvided = false;
            isSecondNumberDouble = false;
        }

    }

    private void pressAC() {    //初始化
        displaying = "0";
        et_display.setText(displaying);
        formerNumber = "";
        secondNumber = "";
        isEditingFirst = true;  // true表示正在编辑第一个数字，false表示正在编辑第二个数字
        isFirstNumberDouble = false;
        isFirstNumberProvided = false;
        isSecondNumberDouble = false;
        isSecondNumberProvided = false;
        isDisplayingDouble = false;
        operation = CalculatorUtils.NONE;

    }

    private void pressNumber(int n) {

        if (!isFirstNumberProvided) {   //如果第一个数没有，正在输入第一个数
            if (n == 0)
                return;
            displaying = "" + n;
            et_display.setText(displaying);
            isFirstNumberProvided = true;
            formerNumber = displaying;
        } else if (!isSecondNumberProvided && isEditingFirst) {   //如果第一个数有，第二个数没有，正在输入第一个数
            displaying = et_display.getText().toString() + n;
            et_display.setText(displaying);
            formerNumber = displaying;
        } else if (!isSecondNumberProvided && !isEditingFirst) {  //如果第一个数有，第二个数没有，正在输入第二个数，但显示的是第一个数
            displaying = "" + n;
            et_display.setText(displaying);
            if (n == 0) {
                isSecondNumberProvided = false;
            } else {
                isSecondNumberProvided = true;
            }
            secondNumber = displaying;
        } else if (isSecondNumberProvided && !isEditingFirst) {    //如果两个数都有，正在输入第二个数
            displaying = et_display.getText().toString() + n;
            et_display.setText(displaying);
            secondNumber = displaying;
        }
    }

    private void pressPoint() {
        if (isDisplayingDouble) return;
        if (!isFirstNumberProvided) {                               //如果第一个数没有，正在输入第一个数
            displaying = et_display.getText().toString() + ".";
            formerNumber = "" + 0;
            isFirstNumberDouble = true;
        } else if (!isSecondNumberProvided && isEditingFirst) {     //如果第一个数有，第二个数没有，正在输入第一个数
            displaying = et_display.getText().toString() + ".";
            isFirstNumberDouble = true;
        } else if (!isSecondNumberProvided && !isEditingFirst) {    //如果第一个数有，第二个数没有，正在输入第二个数，但显示的是第一个数
            displaying = "0.";
            secondNumber = "" + 0;
            isSecondNumberProvided = true;
            isSecondNumberDouble = true;
        } else if (isSecondNumberProvided && !isEditingFirst) {     //如果两个数都有，正在输入第二个数
            displaying = et_display.getText().toString() + ".";
            isSecondNumberDouble = true;
        }
        isDisplayingDouble = true;
        et_display.setText(displaying);
    }

    private void pressC() {
        displaying = "0";
        if (!isFirstNumberProvided) {                               //如果第一个数没有，正在输入第一个数
            formerNumber = displaying;
            et_display.setText(displaying);
            isFirstNumberProvided = false;
            isDisplayingDouble = false;
            isFirstNumberDouble = false;
        } else if (!isSecondNumberProvided && isEditingFirst) {     //如果第一个数有，第二个数没有，正在输入第一个数
            formerNumber = displaying;
            et_display.setText(displaying);
            isFirstNumberProvided = false;
            isDisplayingDouble = false;
            isFirstNumberDouble = false;
        } else if (!isSecondNumberProvided && !isEditingFirst) {    //如果第一个数有，第二个数没有，正在输入第二个数，但显示的是第一个数
            secondNumber = displaying;
            et_display.setText(displaying);
            isSecondNumberProvided = false;
            isDisplayingDouble = false;
            isSecondNumberDouble = false;
        } else if (isSecondNumberProvided && !isEditingFirst) {     //如果两个数都有，正在输入第二个数
            secondNumber = displaying;
            et_display.setText(displaying);
            isSecondNumberProvided = false;
            isDisplayingDouble = false;
            isSecondNumberDouble = false;
        }
    }


}
