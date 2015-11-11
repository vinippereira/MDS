package com.ufscar.alunos.mqc.Menu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ufscar.alunos.mqc.R;

/**
 * Created by lucasbocanegra on 11/11/15.
 */
public class ColorsAdapter extends ArrayAdapter<String> {
    private final LayoutInflater inflater;

    public ColorsAdapter(Context context, int resource, String[] colors) {
        super(context, resource, colors);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
        return getCustomView(position, cnvtView, prnt);
    }

    @Override
    public View getView(int pos, View cnvtView, ViewGroup prnt) {
        return getCustomView(pos, cnvtView, prnt);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        ImageView image = (ImageView)row.findViewById(R.id.imageView);

        //Obtém a cor referente a esta posição
        int color = getColor(position);
        // Obtém a referência ao circlo
        Drawable circle = image.getDrawable();
        //Atribui a cor
        circle.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        return row;
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    public int getColor(int position){
        return Color.parseColor(getItem(position));
    }
}