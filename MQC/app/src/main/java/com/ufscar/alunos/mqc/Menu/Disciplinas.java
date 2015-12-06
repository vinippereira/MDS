package com.ufscar.alunos.mqc.Menu;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.ufscar.alunos.mqc.R;

import java.util.List;

import Logic.Course;
import Logic.Disciplina;


public class Disciplinas extends Fragment {

    private FloatingActionButton discAdd;
    private ListView mListView;
    private MyAdapterDisciplinas mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.activity_disciplinas, container, false);



        mListView = (ListView) rootView.findViewById(R.id.disciplnas_list);

        Bundle bundle = getActivity().getIntent().getExtras();
        String id = bundle.getString("id");
        courseLoad(id);

        discAdd = (FloatingActionButton) rootView.findViewById(R.id.button_floating_d);


        discAdd.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

//                                           Log.i("Disciplinas",((InicialCursos)getActivity()).getName_course());

                                           Intent intent = new Intent(getActivity(), DiscRegisterActivity.class);
                                           intent.putExtra("course", ((InicialCursos)getActivity()).getName_course());
                                           startActivity(intent);

                                           getActivity().finish();
                                       }
                                   }
        );

//        TextView teste = (TextView) rootView.findViewById(R.id.textView_teste);
//
//        teste.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getActivity(), InicialProvTrab.class);
//
//                //intent.putExtra("disciplina", ((InicialCursos)getActivity()).getName_course());
//                intent.putExtra("disciplina", "DisciplinaX");
//                intent.putExtra("objectID_disc", "6B5Dw0pqHD");
//                startActivity(intent);
//
//                getActivity().finish();
//            }
//        });





        return rootView;
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


    public void courseLoad(String id){
        //Recupera a referencia para a string com ID do curso


        //intent.putExtra("disciplina", ((InicialCursos)getActivity()).getName_course());
//        intent.putExtra("disciplina", "DisciplinaX");
//        intent.putExtra("objectID_disc", "6B5Dw0pqHD");
//        startActivity(intent);


        //Acessa todas as linhas da tabela Disciplinas onde na coluna curso possui o valor contido
        //em id e lista todas as disciplinas e professores dessas


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Disciplina");
        query.whereEqualTo("course", ParseObject.createWithoutData("Course", id));

        query.findInBackground(new FindCallback<ParseObject>() {
                                   public void done(List<ParseObject> markers, ParseException e) {
                                       if (e == null) {
                                           // your logic here
                                           String name[] = new String[markers.size()];
                                           String teacher[] = new String[markers.size()];


                                           for (int i = 0; i < markers.size(); i++) {
                                               name[i] = markers.get(i).getString("name");
                                               teacher[i] = markers.get(i).getString("teacher");

                                           }

                                           // specify an adapter (see also next example)

                                           mAdapter = new MyAdapterDisciplinas(getActivity(), name, teacher);
                                           //mAdapter = new MyAdapterDisciplinas(getActivity(), name);

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
