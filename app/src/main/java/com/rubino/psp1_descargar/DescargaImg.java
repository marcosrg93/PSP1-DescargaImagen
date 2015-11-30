package com.rubino.psp1_descargar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by marco on 29/11/2015.
 */
public class DescargaImg  extends AsyncTask<String, Integer, String> {

    private LeerWeb dw;
    private int posicion;
    private ImageButton iv;

    public DescargaImg(LeerWeb dw, int posicion) {
        this.dw = dw;
        this.posicion = posicion;
        this.iv = iv;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(dw.obtenerLista().get(posicion).toString());
            Log.v("GUARDANDO..", dw.obtenerLista().get(posicion).toString());
            InputStream in = url.openStream();
            OutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory()+"imgdescargar.jpg");
            byte[] buffer = new byte[2048];
            int longitud;
            while ((longitud = in.read(buffer)) != -1) {
                out.write(buffer, 0, longitud);
            }
            in.close();
            out.close();
            Log.v("GUARDADO", "CORRECTO");


        } catch (MalformedURLException ex) {

        } catch (IOException ex) {

        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        File f = new File(Environment.getExternalStorageDirectory()+"/psp.jpg");

        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        /*iv.setImageBitmap(bitmap);*/
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    }


}
