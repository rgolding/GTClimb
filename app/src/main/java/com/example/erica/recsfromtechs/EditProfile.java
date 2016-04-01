package com.example.erica.recsfromtechs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditProfile extends AppCompatActivity {
    SharedPreferences userInfo;
    SharedPreferences.Editor editUserInfo;
    SharedPreferences currentUser;
    SharedPreferences.Editor editCurrentUser;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        userInfo = getSharedPreferences("AnotherPref", MODE_PRIVATE);
        editUserInfo = userInfo.edit();
        currentUser = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        editCurrentUser = currentUser.edit();
        dbHandler = new MyDBHandler(this, null, null, 1);
        Intent oldIntent = getIntent();
        String user = oldIntent.getStringExtra("user");
        //String userName = oldIntent.getStringExtra("userName");
        //String userEmail = oldIntent.getStringExtra("userEmail");
        //String userMajor = oldIntent.getStringExtra("userMajor");
        String userName = dbHandler.getName(currentUser.getString("username", null));
        TextView nameText = (TextView)findViewById(R.id.name);
        nameText.setText(userName);
        String userEmail = dbHandler.getEmail(currentUser.getString("username", null));
        TextView emailText = (TextView)findViewById(R.id.email);
        emailText.setText(userEmail);
        String userMajor = dbHandler.getMajor(currentUser.getString("username",null));
        TextView majorText = (TextView)findViewById(R.id.major);
        majorText.setText(userMajor);
        TextView usernameText = (TextView) findViewById(R.id.username);
        usernameText.setText(currentUser.getString("username",null));
        TextView passwordText = (TextView) findViewById(R.id.password);
        passwordText.setText(currentUser.getString("username",null));

    }

    /**
     * Allows the user to change their name
     * @param view The current layout with all the Android widgets
     */
    public void editName(View view) {
        TextView nameText = (TextView)findViewById(R.id.name);
        EditText changedName = (EditText)findViewById(R.id.newName);
        nameText.setText(changedName.getText().toString());
        dbHandler.setName(currentUser.getString("username", null), changedName.getText().toString());

//        editUserInfo.putString("name", changedName.getText().toString());
//        editUserInfo.commit();
    }

    public void editUsername(View view) {
        TextView usernameText = (TextView)findViewById(R.id.username);
        EditText changedUsername = (EditText)findViewById(R.id.newUsername);
        usernameText.setText(changedUsername.getText().toString());
        dbHandler.setUsername(currentUser.getString("username", null), changedUsername.getText().toString());
        editCurrentUser.putString("username", changedUsername.getText().toString());
        editCurrentUser.commit();

//        editUserInfo.putString("name", changedName.getText().toString());
//        editUserInfo.commit();
    }

    public void editPassword(View view) {
        TextView passwordText = (TextView)findViewById(R.id.password);
        EditText changedPassword = (EditText)findViewById(R.id.newPassword);
        passwordText.setText(changedPassword.getText().toString());
        dbHandler.setPassword(currentUser.getString("username",null),changedPassword.getText().toString());
    }
    /**
     * Allows the user to change their email
     * @param view The current layout with all the Android widgets
     */
    public void editEmail(View view) {
        TextView emailText = (TextView)findViewById(R.id.email);
        EditText changedEmail = (EditText)findViewById(R.id.newEmail);
        emailText.setText(changedEmail.getText().toString());
        dbHandler.setEmail(currentUser.getString("username", null), changedEmail.getText().toString());
//        editUserInfo.putString("email", changedEmail.getText().toString());
//        editUserInfo.commit();
    }

    /**
     * Allows the user to change their major
     * @param view The current layout with all the Android widgets
     */
    public void editMajor(View view) {
        TextView majorText = (TextView) findViewById(R.id.major);
        EditText changedMajor = (EditText)findViewById(R.id.newMajor);
        majorText.setText(changedMajor.getText().toString());
        dbHandler.setMajor(currentUser.getString("username", null), changedMajor.getText().toString());
//        editUserInfo.putString("major", changedMajor.getText().toString());
//        editUserInfo.commit();
    }

    /**
     * Allows the user to go back to the dashboard
     * @param view The current layout with all the Android widgets
     */
    public void backToDashboard(View view) {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }


}