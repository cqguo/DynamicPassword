package com.example.dynamicpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dynamicpassword.PasswordGenerator.PasswordGenerator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText initPassword;
    EditText initFactor;
    Button generating;
    Spinner precision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPassword = findViewById(R.id.initPassword);
        initPassword.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
//        initPassword.setHint("0000");
        initFactor = findViewById(R.id.factor);
        initFactor.setKeyListener(DigitsKeyListener.getInstance(".0123456789"));
//        initFactor.setHint("1");
        generating = findViewById(R.id.generating);
        generating.setOnClickListener(this);
        precision = findViewById(R.id.precision);
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        String password = initPassword.getText().toString();
        String factor = initFactor.getText().toString();
        if(password.equals("")||factor.equals("")){
            Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        int pos = precision.getSelectedItemPosition();
        ResultActivity.start(MainActivity.this, password, factor, pos);
    }
}
