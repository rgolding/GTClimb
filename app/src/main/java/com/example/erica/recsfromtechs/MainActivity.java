package com.example.erica.recsfromtechs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * The main activity class is the opening activity for the app
 * you can pick between registering or logging in
 *
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wv = (WebView) findViewById(R.id.webView1);
        wv.loadUrl("file:///android_asset/logo2.gif");
        wv.setBackgroundColor(333333);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //lol
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Takes the user to the login page
     * @param view The current layout with all the Android widgets
     */
    public void login(View view) {
        Intent intent = new Intent(this, Login.class);


        startActivity(intent);
    }

    /**
     * Takes the user to the registration page with all the Android widgets
     * @param view the view we're currently looking at
     */
    public void register(View view) {
        Intent intent = new Intent(this, Register.class);

        startActivity(intent);
    }
}
