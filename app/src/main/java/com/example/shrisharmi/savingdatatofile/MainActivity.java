package com.example.shrisharmi.savingdatatofile;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button SAVE,LOAD;
    EditText message;
    String Message;
    int dataBlock = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SAVE= (Button)findViewById(R.id.save);
        LOAD= (Button)findViewById(R.id.Load);
        message = (EditText)findViewById(R.id.msg);
        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message = message.getText().toString();
                try {
                    FileOutputStream fileoutput = openFileOutput("filetext.txt", Context.MODE_PRIVATE);
                    OutputStreamWriter output = new OutputStreamWriter(fileoutput);
                    try {
                        output.write(Message);
                        output.flush();
                        output.close();
                        Toast.makeText(getBaseContext(), "Data is saved", Toast.LENGTH_LONG).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        LOAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileinput = openFileInput("filetext.txt");
                    InputStreamReader inputreader = new InputStreamReader(fileinput);
                    char[] data = new char[dataBlock];
                    String final_data ="";
                    int size;
                    try {
                        while ((size = inputreader.read(data)) > 0)
                        {
                           String readData = String.copyValueOf(data, 0, size);
                            final_data+= readData;
                            data = new char[dataBlock];

                        }
                        Toast.makeText(getBaseContext(), "Message:" + final_data, Toast.LENGTH_LONG).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
}
