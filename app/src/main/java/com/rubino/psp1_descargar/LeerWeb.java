package com.rubino.psp1_descargar;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2dam on 16/11/2015.
 */
public class LeerWeb  extends AsyncTask<String, Integer, String> {

    private ListView lv;
    private Context ctx;
    //private ProgressBar pb;
    private ArrayList<String> list;
    private Adaptador adp;

    public LeerWeb(ListView lv, Context ctx) {
        this.lv=lv;
        this.ctx= ctx;
        //this.pb = pb;
    }

    @Override
    protected String doInBackground(String... params) {

        list= new ArrayList<String>();
        URL url = null;
        String url2=params[0];
        String servidor="";
        String rutaImagen="";

        try {
            url = new URL(params[0]);
            Log.v("PRUEBA", url.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String linea, out = "";
        try {
            while ((linea = in.readLine()) != null) {
                if(linea.contains("img src")){
                    //--------------- Seleccionamos la URL de la imagen
                    int pos=0;
                    int pos2=0;
                    char c = (char)34;
                    pos=linea.indexOf("img src="+c);
                    pos2=linea.indexOf(c,pos+9);
                    rutaImagen=linea.substring(pos+9, pos2);

                    linea.trim();
                    out += linea + "\n";
                    //--------------- Guardamos la URL del servidor

                    pos=url2.indexOf("//");
                    pos2=url2.indexOf("/",pos+2);
                    servidor=url2.substring(0,pos2);
                    list.add(servidor+rutaImagen);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i=1; i<=100; i++){
            if(i%1==0){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);

            }
        }
        for(int i =0; i <list.size();i++){
            Log.v("Enlace", list.get(i));
        }
        return out;

    }

    @Override
    protected void onPostExecute(String result) {
    }

    @Override
    protected void onPreExecute() {
        //pb.setMax(100);
        //pb.setProgress(0);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //pb.setProgress(pb.getProgress()+1);
        lv.setAdapter(adp);
        adp=  new Adaptador(ctx, R.layout.item, list);
        lv.setTag(list);
    }

    public ArrayList<String> obtenerLista(){
        return list;
    }
}
