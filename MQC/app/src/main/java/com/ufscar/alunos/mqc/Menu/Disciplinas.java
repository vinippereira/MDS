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

import com.ufscar.alunos.mqc.R;

import Logic.Disciplina;


public class Disciplinas extends Fragment {

    private FloatingActionButton discAdd;
    private ListView mListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.activity_disciplinas, container, false);

        //View v = inflater.inflate(R.layout.activity_disciplinas, container, false);

       // mListView = (ListView) v.findViewById(R.id.disc_list_view);

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

        TextView teste = (TextView) rootView.findViewById(R.id.textView_teste);

        teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), InicialProvTrab.class);

                //intent.putExtra("disciplina", ((InicialCursos)getActivity()).getName_course());
                intent.putExtra("disciplina", "X");
                startActivity(intent);

                getActivity().finish();
            }
        });





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

}
