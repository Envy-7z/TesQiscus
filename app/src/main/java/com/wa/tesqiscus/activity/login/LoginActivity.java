package com.wa.tesqiscus.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qiscus.sdk.Qiscus;
import com.wa.tesqiscus.activity.main.MainActivity;
import com.wa.tesqiscus.R;
import com.wa.tesqiscus.TesQiscusApp;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {

    private EditText edtPassword , edtDisplayName ,
            edtUserID ;
    private Button btnLogin;
    private LoginPresenter loginPresenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        if (Qiscus.hasSetupUser()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        edtUserID = findViewById(R.id.etEmail);
        edtPassword = findViewById(R.id.etPass);
        edtDisplayName = findViewById(R.id.etNama);
        loginPresenter = new LoginPresenter(this, TesQiscusApp.getInstance().getComponent().getUserRepository());
        btnLogin = findViewById(R.id.btnLanjut);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLanjut) {
            if (TextUtils.isEmpty(edtDisplayName.getText().toString())) {
                edtDisplayName.setError("Must not Empty!");
                edtDisplayName.requestFocus();
            } else if (TextUtils.isEmpty(edtUserID.getText().toString())) {
                edtUserID.setError("Must not Empty!");
                edtUserID.requestFocus();
            } else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                edtPassword.setError("Must not Empty!");
                edtPassword.requestFocus();
            } else {
                loginPresenter.login(
                        edtUserID.getText().toString().trim(),
                        edtPassword.getText().toString().trim(),
                        edtDisplayName.getText().toString().trim());
            }
        }
    }

    @Override
    public void showHomePage() {
        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }
}