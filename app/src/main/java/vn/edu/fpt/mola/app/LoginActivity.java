package vn.edu.fpt.mola.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.fpt.mola.app.service.AuthenticationService;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameText;
    private EditText mPasswordText;
    private Button mLoginButton;
    private TextView mRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.delete_time_frame_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mUsernameText = (EditText) findViewById(R.id.username_text);
        mPasswordText = (EditText) findViewById(R.id.password_text);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mRegisterLink = (TextView) findViewById(R.id.register_link);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        mRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });
    }

    private void login() {
        final String username = mUsernameText.getText().toString();
        final String password = mPasswordText.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");


        AsyncTask<String, Void, Boolean> loginTask = new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... strings) {
                return new AuthenticationService(LoginActivity.this).login(username, password);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.show();
                mLoginButton.setEnabled(false);
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                progressDialog.dismiss();
                mLoginButton.setEnabled(true);
                if (success) {
                    onLoginSuccess();
                } else {
                    onLoginFailed();
                }
            }
        };

        loginTask.execute(username, password);
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void onLoginFailed() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
    }

    private void goToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
