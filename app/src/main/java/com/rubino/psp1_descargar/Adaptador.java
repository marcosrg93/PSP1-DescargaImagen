package com.rubino.psp1_descargar;

/**
 * Created by marco on 06/10/2015.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class Adaptador extends ArrayAdapter<String>{


    private String c;
    private Context ctx;
    private int res;
    private LayoutInflater lInflator;
    private List<String> valores;

    static class ViewHolder {
        public TextView tv1, tv2;
        public ImageView iv;
    }

    public Adaptador(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.ctx = context;//actividad
        this.res = resource;//layout del item
        this.valores = objects;//lista de valores
        this.lInflator = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1
        ViewHolder vh = new ViewHolder();

        if(convertView==null){
            convertView = lInflator.inflate(res, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tvNombre);
            vh.tv1 = tv;
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
            vh.iv = iv;
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.iv.setTag(position);
        vh.tv1.setText(valores.get(position));
        Log.v("ADAPTER",valores.toString());
        vh.iv.setImageResource(R.drawable.ic_img);


        return convertView;
    }



}


