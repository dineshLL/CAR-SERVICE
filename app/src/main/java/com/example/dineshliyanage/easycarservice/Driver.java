package com.example.dineshliyanage.easycarservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Driver extends AppCompatActivity {

    private String name;
    private Button startChat;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        final String title = (String) getIntent().getExtras().get("NAME");
        getSupportActionBar().setTitle(title);



        startChat=(Button) findViewById(R.id.btn_msg);

        startChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> map = new HashMap<String, Object>();
                map.put(title,"");
                root.updateChildren(map);
                request_user_name(((TextView)view).getText().toString());

            }
        });

    }
    private void request_user_name(final String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Name:");
        final EditText input_field=new EditText(this);
        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                name = input_field.getText().toString();
                Intent intent = new Intent(getApplicationContext(),ChatRoom.class);
                intent.putExtra("TITLE", title);
                intent.putExtra("user_name",name);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                request_user_name("as");
            }
        });

        builder.show();
    }


    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()== android.R.id.home) startActivity(new Intent(getBaseContext(), MapsActivity.class));

        return super.onOptionsItemSelected(item);
    }
}
