package com.example.labux_felis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private TextView usernameError;
    private TextView passwordError;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        usernameField = findViewById(R.id.usernamefield);
        passwordField = findViewById(R.id.passwordfield);
        usernameError = findViewById(R.id.usernameError);
        passwordError = findViewById(R.id.passwordError);

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndLogin();
            }
        });
    }

    private void validateAndLogin() {
        usernameError.setVisibility(View.GONE);
        passwordError.setVisibility(View.GONE);

        String usernameInput = usernameField.getText().toString().trim();
        String passwordInput = passwordField.getText().toString().trim();

        boolean isValid = true;

        if (usernameInput.isEmpty()) {
            usernameError.setText("Username is required");
            usernameError.setVisibility(View.VISIBLE);
            isValid = false;
        }

        if (passwordInput.isEmpty()) {
            passwordError.setText("Password is required");
            passwordError.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (passwordInput.length() < 8) {
            passwordError.setText("Password must be at least 8 characters long");
            passwordError.setVisibility(View.VISIBLE);
            isValid = false;
        }

        if (isValid) {
            username = usernameInput;


            GlobalState.getInstance().setUsername(username);


            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish();
        }
    }
}
