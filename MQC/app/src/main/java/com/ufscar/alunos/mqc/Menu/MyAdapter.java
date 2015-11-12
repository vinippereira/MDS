package com.ufscar.alunos.mqc.Menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ufscar.alunos.mqc.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

public class MyAdapter extends BaseAdapter {
    private String[] list;
    private Context context;

    public MyAdapter(Context context, String[] list){
        this.context = context;
        this.list = list;
    }



    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int i) {
        return list[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //uso qualquer servico do sistema, por exemplo bateria, tipo de conexao atual
        View root = inflater.inflate(R.layout.row_cursos,null);
        TextView name = (TextView) root.findViewById(R.id.textRowId);
        name.setText(list[position]);

        //premite a cada linha da lista ser clicavel
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InicialCursos.class);
                context.startActivity(intent);
            }
        });
        return root;
    }

    private ViewClickListener mViewClickListener;

    public interface ViewClickListener {
        void onImageClicked(int position);
    }

    public void setViewClickListener (ViewClickListener viewClickListener) {
        mViewClickListener = viewClickListener;
    }
}
