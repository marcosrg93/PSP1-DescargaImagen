package com.rubino.psp1_descargar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by marco on 29/11/2015.
 */
public class ListaImagenes extends AppCompatActivity {

    private ListView lv;
    private ProgressBar pb;
    private String url;
    private ArrayList<String> list;
    private WebView wv;
    private LeerWeb descargarWeb;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagenes_lista);

        init();

    }

    public void init(){



        iv =(ImageView)findViewById(R.id.iv);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        Intent i= getIntent();
        url=i.getStringExtra("url");

        lv= (ListView) findViewById(R.id.lv);
        pb= (ProgressBar) findViewById(R.id.progressBar);

        descargarWeb = new LeerWeb(lv, this);
        descargarWeb.execute(new String[]{url});

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                dgmostrar(view, position);
            }
        });

    }




    public  void dgmostrar(View v, final int posicion){
        android.app.AlertDialog.Builder alert= new android.app.AlertDialog.Builder(this);

        LayoutInflater inflater= LayoutInflater.from(this);

        final View vista = inflater.inflate(R.layout.dg_mostrar, null);

        String url= descargarWeb.obtenerLista().get(posicion).toString();
        wv = (WebView) vista.findViewById(R.id.webView);
        wv.loadUrl(url);

        alert.setView(vista);
        alert.setPositiveButton("Guardar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        DescargaImg di= new DescargaImg(descargarWeb, posicion);
                        di.execute();
                    }
                });
        alert.setNegativeButton("Cancelar", null);
        alert.show();
    }
}
