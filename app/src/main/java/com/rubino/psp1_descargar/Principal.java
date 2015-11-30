package com.rubino.psp1_descargar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    private FloatingActionButton fab;
    private EditText eturl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

    }

    public void init(){
        eturl = (EditText) findViewById(R.id.et);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verListaImagen(view);

            }
        });

    }

    public void verListaImagen(View v){
        Intent intent = new Intent(this, ListaImagenes.class);
        intent.putExtra("url", eturl.getText().toString());
        startActivity(intent);
    }

}