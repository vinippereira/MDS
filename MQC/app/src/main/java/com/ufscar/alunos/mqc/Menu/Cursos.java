package com.ufscar.alunos.mqc.Menu;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.parse.*;

import android.support.design.widget.FloatingActionButton;

import com.ufscar.alunos.mqc.R;

import java.util.List;

public class Cursos extends Fragment {

    private FloatingActionButton couseAdd;
    //Identificadores para a lista
    private ListView mListView;
    private MyAdapter mAdapter;
    private FloatingActionButton courseAdd;
    private Button openDisciplinas;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View v = inflater.inflate(R.layout.activity_cursos, container, false);
        mListView = (ListView) v.findViewById(R.id.cursos_list_view);

        courseLoad();

        courseAdd = (FloatingActionButton) v.findViewById(R.id.button_floating);

        courseAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CourseRegisterActivity.class);
                startActivity(intent);

                getActivity().finish();
            }
        }
        );

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//        });


       return v;
    }

      /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meus_cursos, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
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

    public void courseLoad(){
        //Acessa todos os dados de uma tabela
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Course");
        query.whereEqualTo("owner", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
                                   public void done(List<ParseObject> markers, ParseException e) {
                                       if (e == null) {
                                           // your logic here
                                           String name[] = new String[markers.size()];
                                           // String color;

                                           for (int i = 0; i < markers.size(); i++) {
                                               name[i] = markers.get(i).getString("name");
                                               //color = markers.get(i).getString("color");
//                                               getActivity().findViewById(R.id.button_color).getBackground().setColorFilter
//                                                       (0xFFFF0000,PorterDuff.Mode.MULTIPLY);
                                           }

                                           // specify an adapter (see also next example)

                                           mAdapter = new MyAdapter(getActivity(), name);
                                           mListView.setAdapter(mAdapter);


                                       } else {
                                           // handle Parse Exception here
                                           e.getCause();
                                       }
                                   }
                               }
        );
    }

}
