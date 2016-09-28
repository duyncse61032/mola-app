package vn.edu.fpt.mola.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import vn.edu.fpt.mola.app.model.Role;
import vn.edu.fpt.mola.app.model.UserPrincipal;
import vn.edu.fpt.mola.app.controller.learner.MainLearner;
import vn.edu.fpt.mola.app.controller.teacher.MainTeacher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        UserPrincipal user = ((MolaApp)getApplication()).getUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if(savedInstanceState == null && user.getRoleList() != null) {
            Role teacherRole = new Role();
            teacherRole.setId(1);
            Role learnerRole = new Role();
            learnerRole.setId(2);
            if (user.getRoleList().contains(teacherRole)) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.teacher_home_container, new MainTeacher())
                        .commit();
            }
            if (user.getRoleList().contains(learnerRole)) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.learner_home_container, new MainLearner())
                        .commit();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_logout) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
