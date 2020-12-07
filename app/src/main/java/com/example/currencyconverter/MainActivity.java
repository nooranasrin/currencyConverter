package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private HashMap<String, Converter> converter = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner(R.id.fromSpinner);
        createSpinner(R.id.toSpinner);

        this.converter.put("Indian Rupee - Saudi Riyal", amount -> amount * 0.0508623);
        this.converter.put("Indian Rupee - UAE Dirham", amount -> amount * 0.0498061);
        this.converter.put("Indian Rupee - US Dollar", amount -> amount * 0.0135622);
        this.converter.put("Indian Rupee - Indian Rupee", amount -> amount);

        this.converter.put("Saudi Riyal - Saudi Riyal", amount -> amount);
        this.converter.put("Saudi Riyal - UAE Dirham", amount -> amount * 0.979333);
        this.converter.put("Saudi Riyal - US Dollar", amount -> amount * 0.266667);
        this.converter.put("Saudi Riyal - Indian Rupee", amount -> amount * 19.6749);

        this.converter.put("UAE Dirham - Saudi Riyal", amount -> amount * 1.02110);
        this.converter.put("UAE Dirham - UAE Dirham", amount -> amount);
        this.converter.put("UAE Dirham - US Dollar", amount -> amount * 0.272294);
        this.converter.put("UAE Dirham - Indian Rupee", amount -> amount * 20.0811);

        this.converter.put("US Dollar - Saudi Riyal", amount -> amount * 3.75000);
        this.converter.put("US Dollar - UAE Dirham", amount -> amount * 3.67250);
        this.converter.put("US Dollar - US Dollar", amount -> amount);
        this.converter.put("US Dollar - Indian Rupee", amount -> amount * 73.7468);
    }

    private void createSpinner(int spinnerId) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencyType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void convert(View view) {
        Spinner fromSpinner = findViewById(R.id.fromSpinner);
        Spinner toSpinner = findViewById(R.id.toSpinner);
        String from = fromSpinner.getSelectedItem().toString();
        String to = toSpinner.getSelectedItem().toString();
        EditText fromView = findViewById(R.id.fromAmount);
        double fromAmount = Double.parseDouble(fromView.getText().toString());
        double convertedValue = this.converter.get(from + " - " + to).convert(fromAmount);
        TextView toView = findViewById(R.id.toAmount);
        toView.setText(Double.toString(convertedValue));
    }
}