package com.example.lesson_5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Constants {
    private EditText txtName;
    private Account account;
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = new Account();
        initView();
    }

    private void initView() {
        Button btnGreetings = findViewById(R.id.btnGreetings);
        final TextView txtGreetings = findViewById(R.id.textHello);
        txtName = findViewById(R.id.textName);
        btnGreetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String sayHello = getString(R.string.say_hello) + name;
                txtGreetings.setText(sayHello);
            }
        });
        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runSettings = new Intent(MainActivity.this,SettingsActivity.class);
                populateAccount();
                runSettings.putExtra(YOUR_ACCOUNT,account);
                startActivityForResult(runSettings,REQUEST_CODE_SETTING_ACTIVITY);
            }
        });
    }

    private void populateAccount() {
        account.setName(txtName.getText().toString());
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK){
            account = data.getParcelableExtra(YOUR_ACCOUNT);
            populateView();
        }
    }
    private void populateView(){
        txtName.setText(account.getName());
    }


}