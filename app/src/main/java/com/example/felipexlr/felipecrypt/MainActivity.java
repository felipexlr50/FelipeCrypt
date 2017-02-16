package com.example.felipexlr.felipecrypt;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.felipexlr.felipecrypt.core.EncryptEngine;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {


    private EncryptEngine encode;
    private FloatingActionMenu menu;
    private EditText edtKey;
    private EditText edtTexto;
    private FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu = (FloatingActionMenu) findViewById(R.id.menu2);
        edtKey = (EditText) findViewById(R.id.edtKey);
        edtTexto = (EditText) findViewById(R.id.edtMText);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.nfab1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.nfab2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.nfab3);

        encode = new EncryptEngine();
        setFabs();


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

    private void setFabs(){
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codificar();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodificar();
            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtKey.setText("");
                edtTexto.setText("");
            }
        });
    }

    public void codificar(){
        if(!edtTexto.getText().toString().equals("")){

           class Code extends AsyncTask<String,Void,String>{

               @Override
               protected void onPreExecute() {
                   progressBar.setVisibility(View.VISIBLE);

               }
               @Override
               protected String doInBackground(String... params) {
                   return encode.encode(params[0],encode.keyEncrypt2(params[1]));
               }
               @Override
               protected void onPostExecute(String text) {
                   edtTexto.setText(text);
                   progressBar.setVisibility(View.INVISIBLE);
               }
           }

           new Code().execute(edtTexto.getText().toString(),edtKey.getText().toString());
        }else {
            Toast.makeText(MainActivity.this, "Sem texto para codificar", Toast.LENGTH_LONG).show();
        }
    }

    public void decodificar(){
        if(!edtTexto.getText().toString().equals("")){

            class Decode extends AsyncTask<String,Void,String>{

                @Override
                protected void onPreExecute() {
                    progressBar.setVisibility(View.VISIBLE);

                }
                @Override
                protected String doInBackground(String... params) {
                    return encode.decode(params[0],encode.keyEncrypt2(params[1]));
                }
                @Override
                protected void onPostExecute(String text) {
                    edtTexto.setText(text);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            new Decode().execute(edtTexto.getText().toString(),edtKey.getText().toString());
        }else {
            Toast.makeText(MainActivity.this, "Sem texto para decodificar", Toast.LENGTH_LONG).show();
        }
    }

}
