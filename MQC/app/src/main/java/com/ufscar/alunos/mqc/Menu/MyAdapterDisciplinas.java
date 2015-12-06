package com.ufscar.alunos.mqc.Menu;

/**
 * Created by Vinicius on 05/12/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.ufscar.alunos.mqc.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

public class MyAdapterDisciplinas extends BaseAdapter{

    private String[] listDisciplina;
    private String[] listTeacher;
    private Context context;

    public MyAdapterDisciplinas(Context context, String[] listDisciplina, String [] listTeacher){
        this.context = context;
        this.listDisciplina = listDisciplina;
        this.listTeacher = listTeacher;
    }

    @Override
    public int getCount() {
        return listDisciplina.length;
    }

    @Override
    public Object getItem(int i) {
        return listDisciplina[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //uso qualquer servico do sistema, por exemplo bateria, tipo de conexao atual
        View root = inflater.inflate(R.layout.row_disciplinas,null);

        TextView nameDisci = (TextView) root.findViewById(R.id.nameDisci);
        nameDisci.setText(listDisciplina[position]);

        TextView teacher = (TextView) root.findViewById(R.id.teacher);
        teacher.setText(listTeacher[position]);

//        //premite a cada linha da lista ser clicavel
//        root.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ParseQuery<ParseObject> query = ParseQuery.getQuery("Course");
//                query.whereEqualTo("name", list[position]);
//                query.whereEqualTo("owner", ParseUser.getCurrentUser());
//
//                query.findInBackground(new FindCallback<ParseObject>() {
//                    public void done(List<ParseObject> cList, ParseException e) {
//                        if (e == null) {
//                            Intent intent = new Intent(context, InicialCursos.class);
//                            intent.putExtra("course", cList.get(0).getString("name"));
//                            intent.putExtra("id", cList.get(0).getString("objectId"));
//
//                            context.startActivity(intent);
//                        } else {
//                            Log.d("score", "Error: " + e.getMessage());
//                        }
//                    }
//                });
//            }
//
//
//        });
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



