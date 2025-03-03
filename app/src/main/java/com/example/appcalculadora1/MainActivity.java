package com.example.appcalculadora1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView display;
    double resultado = 0;
    char operacion = ' ';
    String currentinpunt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.display);

        setButtonClickListeners(R.id.uno);
        setButtonClickListeners(R.id.dos);
        setButtonClickListeners(R.id.tres);
        setButtonClickListeners(R.id.cuatro);
        setButtonClickListeners(R.id.cinco);
        setButtonClickListeners(R.id.seis);
        setButtonClickListeners(R.id.siete);
        setButtonClickListeners(R.id.ocho);
        setButtonClickListeners(R.id.nueve);
        setButtonClickListeners(R.id.cero);
        setButtonClickListeners(R.id.punto);
        setOperacionesClickListener(R.id.suma, '+');
        setOperacionesClickListener(R.id.resta, '-');
        setOperacionesClickListener(R.id.multiplicacion, '*');
        setOperacionesClickListener(R.id.division, '/');

        setOperacionesClickListener(R.id.potencia, '^');
        setOperacionesClickListener(R.id.modulo, '%');

        findViewById(R.id.raiz).setOnClickListener(v -> {
            if (!currentinpunt.isEmpty()) {
                double value = Double.parseDouble(currentinpunt);
                resultado = Math.sqrt(value);
                currentinpunt = String.valueOf(resultado);
                updateDisplay();
                operacion = ' ';
            }
        });
        findViewById(R.id.clear).setOnClickListener(v -> {
            cleanDisplay();
        });

        findViewById(R.id.igual).setOnClickListener(v -> {
            calculateResul();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setButtonClickListeners(int buttonId) {
        findViewById(buttonId).setOnClickListener(v -> {
            Button button = (Button) v;
            String value = button.getText().toString();

            if (value.equals(".") && currentinpunt.contains(".")) {
                return;
            }
            currentinpunt += value;
            updateDisplay();
        });
    }

    private void updateDisplay() {
        display.setText(currentinpunt.isEmpty() ? "0" : currentinpunt);
    }

    private void setOperacionesClickListener(int buttonId, char op) {
        findViewById(buttonId).setOnClickListener(v -> {
            if (!currentinpunt.isEmpty()) {
                resultado = Double.parseDouble(currentinpunt);
                currentinpunt = "";
                operacion = op;
            }
        });
    }

    private void calculateResul() {
        if (!currentinpunt.isEmpty()) {
            double secondNumber = Double.parseDouble(currentinpunt);
            switch (operacion) {
                case '+':
                    resultado += secondNumber;
                    break;
                case '-':
                    resultado -= secondNumber;
                    break;
                case '*':
                    resultado *= secondNumber;
                    break;
                case '/':
                    resultado /= secondNumber;
                    break;
                case '^':
                    resultado = Math.pow(resultado, secondNumber);
                    break;
                case '%':
                    resultado %= secondNumber;
                    break;
                default:
                    break;
            }
            currentinpunt = String.valueOf(resultado);
            updateDisplay();
            operacion = ' ';
        }
    }

    private void cleanDisplay() {
        display.setText("0");
        currentinpunt = "";
        resultado = 0;
        operacion = ' ';
    }
}
