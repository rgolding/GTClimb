package com.example.erica.recsfromtechs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class AdminPage extends AppCompatActivity {



    private String currentUser;
    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LinkedList<User> allUsers = new LinkedList<>();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new MyDBHandler(this, null);





        //get all the user from the database
        //put them in allUsers linked list

        //test
        allUsers = dbHandler.listOfUsers();


        final String[] names = new String[allUsers.size()];
        int[] isBlocked = new int[allUsers.size()];

        int index = 0;
        for (User i : allUsers) {
            names[index] = allUsers.get(index).getUsername();
            isBlocked[index] = allUsers.get(index).getIsLocked();
            index++;
        }

        UserList adapter = new
                UserList(AdminPage.this, names, isBlocked);
        ListView list = (ListView) findViewById(R.id.list3);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(AdminPage.this, "You Clicked at " + names[position], Toast.LENGTH_SHORT).show();

                currentUser = names[position];

                TextView selectedUserText = (TextView) findViewById(R.id.currentUser);
                selectedUserText.setText("Current User: " + currentUser);
                TextView isLocked = (TextView) findViewById(R.id.isLocked);
                if(dbHandler.getIsLocked(currentUser) == 0) {
                    isLocked.setText("Is Locked: NO");
                } else {
                    isLocked.setText("Is Locked: YES");
                }
                TextView isBlocked = (TextView) findViewById(R.id.isblocked);
                if(dbHandler.getIsBlocked(currentUser) == 0) {
                    isBlocked.setText("Is Blocked: NO");
                } else {
                    isBlocked.setText("Is Blocked: YES");
                }



            }
        });


    }
    //Needs to implemented with database
    public void lock(View view) {
        dbHandler.setLocked(currentUser,1);
        TextView isLocked = (TextView) findViewById(R.id.isLocked);
        updateTable();

    }
    public void unlock(View view) {
        dbHandler.setLocked(currentUser,0);
        updateTable();

    }
    public void block(View view) {
        dbHandler.setBlocked(currentUser);
        updateTable();
    }
    public void back(View view) {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

    private void updateTable() {
        TextView isLocked = (TextView) findViewById(R.id.isLocked);
        if(dbHandler.getIsLocked(currentUser) == 0) {
            isLocked.setText("Is Locked: NO");
        } else {
            isLocked.setText("Is Locked: YES");
        }
        TextView isBlocked = (TextView) findViewById(R.id.isblocked);
        if(dbHandler.getIsBlocked(currentUser) == 0) {
            isBlocked.setText("Is Blocked: NO");
        } else {
            isBlocked.setText("Is Blocked: YES");
        }


    }

}
