package com.example.erica.recsfromtechs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * This is the activity for the login screen
 * you need to enter a password and username
 * that will be checked in the database
 * to grant you further access to the app
 */
public class Login extends AppCompatActivity {


    private SharedPreferences.Editor editCurrentUser;

    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences currentUser;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentUser = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        editCurrentUser = currentUser.edit();
        editCurrentUser.apply();
        dbHandler = new MyDBHandler(this);
    }

    /**
     * Allows the User to cancel their login and go back to the welcome page
     * @param view The current layout with all the Android widgets
     */
    public void cancel(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /**
     * When the user types in a correct username and password, it will take them to the Dashboard
     * If not, then it will allow the user to know that one of the two is incorrect
     * @param view The current layout with all the Android widgets
     */
    public void loginSuccessful(View view) throws Exception {
        EditText usernameText = (EditText) findViewById(R.id.username);
        EditText passwordText = (EditText) findViewById(R.id.password);
        if (dbHandler.authenticateUser(usernameText.getText().toString(), passwordText.getText().toString())) {
            editCurrentUser.putString("username", usernameText.getText().toString());
            editCurrentUser.commit();
            Intent intent = new Intent(this,dashboard.class);
            startActivity(intent);

        } else {
            int duration = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();
            CharSequence text = "Username/Password Incorrect or Account is Locked";
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();
        }

    }

}
