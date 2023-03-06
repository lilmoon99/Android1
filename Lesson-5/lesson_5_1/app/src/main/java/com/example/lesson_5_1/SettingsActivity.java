package com.example.lesson_5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements Constants {

    private EditText editName;
    private EditText editSurname;
    private EditText editAge;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        EditText editName = findViewById(R.id.editName);
        Account account = getIntent().getExtras().getParcelable(YOUR_ACCOUNT);
        populateView(account);
        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent();
                intentResult.putExtra(YOUR_ACCOUNT,createAccount());
                setResult(RESULT_OK,intentResult);
                finish();
            }
        });

    }
    private Account createAccount(){
        Account account = new Account(
                editName.getText().toString(),
                editSurname.getText().toString(),
                Integer.parseInt(editAge.getText().toString()),
                editEmail.getText().toString());
        return account;
    }

    private void populateView(Account account) {
        editName.setText(account.getName());
        editSurname.setText(account.getSurName());
        editAge.setText(String.format(Locale.getDefault(),"%d",account.getAge()));
        editEmail.setText(account.getEmail());
    }

    private void initView() {
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);
    }
}