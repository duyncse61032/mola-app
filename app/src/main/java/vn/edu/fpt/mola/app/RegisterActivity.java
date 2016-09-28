package vn.edu.fpt.mola.app;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.fpt.mola.app.model.Language;
import vn.edu.fpt.mola.app.model.Role;
import vn.edu.fpt.mola.app.model.UserForm;
import vn.edu.fpt.mola.app.service.AuthenticationService;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameText;
    private EditText mPasswordText;
    private EditText mFirstNameText;
    private EditText mLastNameText;
    private RadioGroup mRoleRadio;
    private RadioGroup mLanguageRadio;
    private Button mRegisterButton;
    private TextView mLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsernameText = (EditText) findViewById(R.id.username_text);
        mPasswordText = (EditText) findViewById(R.id.password_text);
        mFirstNameText = (EditText) findViewById(R.id.firstname_text);
        mLastNameText = (EditText) findViewById(R.id.lastname_text);
        mRoleRadio = (RadioGroup) findViewById(R.id.role_radio);
        mLanguageRadio = (RadioGroup) findViewById(R.id.language_radio);

        mRegisterButton = (Button) findViewById(R.id.register_button);
        mLoginLink = (TextView) findViewById(R.id.login_link);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
    }

    public void register() {
        final String username = mUsernameText.getText().toString();
        final String password = mPasswordText.getText().toString();
        final String firstName = mFirstNameText.getText().toString();
        final String lastName = mLastNameText.getText().toString();
        Role newRole = new Role();
        Language newLanguage = new Language();
        final long roleId;
        final long languageId;


        UserForm newUser = new UserForm();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.addRole(newRole);

        final int roleRadioId = mRoleRadio.getCheckedRadioButtonId();
        if (roleRadioId == R.id.teache_role_radio) {
            roleId = 1;
            newUser.addTeachingLanguageList(newLanguage);
        } else if (roleRadioId == R.id.learner_role_radio){
            roleId = 2;
            newUser.addLearningLanguage(newLanguage);
        } else {
            return;
        }
        newRole.setId(roleId);

        final int languageRadioId = mLanguageRadio.getCheckedRadioButtonId();
        if (languageRadioId == R.id.vietnamese_radio) {
            languageId = 237;
        } else if (languageRadioId == R.id.english_radio) {
            languageId = 57;
        } else if (languageRadioId == R.id.japanese_radio) {
            languageId = 103;
        } else {
            return;
        }
        newLanguage.setId(languageId);



        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registering...");

        AsyncTask<UserForm, Void, Boolean> registerTask = new AsyncTask<UserForm, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(UserForm... params) {
                return new AuthenticationService(RegisterActivity.this)
                        .register(params[0]);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.show();
                mRegisterButton.setEnabled(false);
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                progressDialog.dismiss();
                mRegisterButton.setEnabled(true);
                if (success) {
                    onRegisterSuccess();
                } else {
                    onRegisterFailed();
                }
            }
        };

        registerTask.execute(newUser);
    }

    private void onRegisterSuccess() {
        this.setResult(RESULT_OK);
        this.finish();
    }

    private void onRegisterFailed() {
        Toast.makeText(this, "Register Failed", Toast.LENGTH_LONG).show();
    }

    public void goToLogin() {
        this.setResult(RESULT_CANCELED);
        this.finish();
    }
}
