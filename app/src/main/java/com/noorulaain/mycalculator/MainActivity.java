package com.noorulaain.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        private TextView dataTextView;
        private TextView resultTextView;
        private String currentInput = "";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            dataTextView = findViewById(R.id.data_tv);
            resultTextView = findViewById(R.id.Result_tv);
        }
    public void OnClearClick(View view) {
        if(currentInput.isEmpty()){
            currentInput = "";
            updateDataTextView();
        } else {
            String expression = dataTextView.getText().toString();
            if (resultTextView.getVisibility() == View.VISIBLE) {
                currentInput = resultTextView.getText().toString();
                resultTextView.setVisibility(View.GONE);
            } else {
                currentInput += " " + expression;
            }
            updateDataTextView();
        }


    }

//        public void OnClearClick(View view) {
//
//                currentInput = "";
//                updateDataTextView();
//
//
//        }

        public void OnBackSpaceClick(View view) {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                updateDataTextView();
            }
        }

        public void onOperatorclick(View view) {
            String operator = ((TextView) view).getText().toString();
            currentInput += " " + operator + " ";
            updateDataTextView();
        }

        public void onDigitclick(View view) {
            String digit = ((TextView) view).getText().toString();
            currentInput += digit;
            updateDataTextView();



        }
            public void onAllClearclick(View view) {
                currentInput = "";
                updateDataTextView();
                resultTextView.setVisibility(View.GONE);
            }
public void onEqualclick(View view) {
    String[] parts = currentInput.split(" ");
    if (parts.length % 2 == 0) {
        Toast.makeText(this, "Invalid expression", Toast.LENGTH_LONG).show();
        return;
    }

    double result = evaluateExpression(parts);

    resultTextView.setText(String.valueOf(result));
    resultTextView.setVisibility(View.VISIBLE);
}

    private double evaluateExpression(String[] parts) {
        double result = Double.parseDouble(parts[0]);
        for (int i = 1; i < parts.length; i += 2) {
            String operator = parts[i];
            double operand = Double.parseDouble(parts[i + 1]);
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        Toast.makeText(this, "Divided by zero", Toast.LENGTH_LONG).show();
                        return Double.NaN;
                    }
                    break;
                default:
                    Toast.makeText(this, "Invalid operator", Toast.LENGTH_LONG).show();
                    return Double.NaN;
            }
        }
        return result;
    }


    public void onPercentageClick(View view) {
        double value = Double.parseDouble(dataTextView.getText().toString());
        double percentage = value / 100;
        dataTextView.setText(String.valueOf(percentage));
        currentInput = String.valueOf(percentage);
    }

        private void updateDataTextView() {
            dataTextView.setText(currentInput);
        }
    }
