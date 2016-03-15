package com.example.erica.recsfromtechs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Register extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    SharedPreferences passwords;
    SharedPreferences.Editor editPasswords;
    SharedPreferences userInfo;
    SharedPreferences.Editor editUserInfo;

    Spinner spinner;

    MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new MyDBHandler(this, null, null, 1);

        passwords  = getSharedPreferences("MyPref", MODE_PRIVATE);
        editPasswords = passwords.edit();
        userInfo = getSharedPreferences("AnotherPref", MODE_PRIVATE);
        editUserInfo = userInfo.edit();

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.majors,R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    /**
     * Creates a user object based off of the information provided from the user such as username,
     * password, name, major, and email.
     * @param view The current layout with all the Android widgets
     */
    public void addUser(View view) {
        EditText usernameText = (EditText) findViewById(R.id.username);
        EditText passwordText = (EditText) findViewById(R.id.password);
        EditText emailText = (EditText) findViewById(R.id.email);
        EditText nameText = (EditText) findViewById(R.id.name);
        //EditText majorText = (TextView) findViewById(R.id.major);
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String text = mySpinner.getSelectedItem().toString();
        User currentUser = new User(nameText.getText().toString(),emailText.getText().toString(),text, usernameText.getText().toString(),passwordText.getText().toString(), 0);
        dbHandler.addUser(currentUser);
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

        /*editPasswords.putString(usernameText.getText().toString(), passwordText.getText().toString());
        editPasswords.commit();
        editUserInfo.putString(usernameText.getText().toString()+"name", nameText.getText().toString());
        editUserInfo.commit();
        editUserInfo.putString(usernameText.getText().toString()+"email", emailText.getText().toString());
        editUserInfo.commit();
        editUserInfo.putString(usernameText.getText().toString()+"major",majorText.getText().toString());
        editUserInfo.commit();
        Intent intent = new Intent(this,Login.class);
        Bundle bundle = new Bundle();
        bundle.putString("userName",currentUser.getName());
        bundle.putString("userEmail", currentUser.getEmail());
        bundle.putString("userMajor", currentUser.getMajor());
        intent.putExtras(bundle);
        startActivity(intent);*/
    }

    /**
     * Allows the user to cancel their registration and takes them back to the welcome screen
     * @param view The current layout with all the Android widgets
     */
    public void cancel(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText = (TextView) view;
        //Toast.makeText(this,"You Selected: " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
