package com.ufscar.alunos.mqc.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.ufscar.alunos.mqc.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Provas extends Fragment {

    private ListView mListView;
    private MyAdapterProvas mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.activity_provas, container, false);


        mListView = (ListView) rootView.findViewById(R.id.provas_lista);

        Bundle bundle = getActivity().getIntent().getExtras();
        String id = bundle.getString("objectID_disc");
        provasLoad(id);


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.button_floating_provas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProvTrabRegisterActivity.class);

                intent.putExtra("disciplina", ((InicialProvTrab) getActivity()).getName_disc());
                intent.putExtra("evento", "Prova");
                intent.putExtra("objectID_disc", ((InicialProvTrab) getActivity()).getObjetcID_disc());
                intent.putExtra("course", ((InicialProvTrab) getActivity()).getCourse());

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
    public boolean onOptionsItemSelected(MenuItem item) {
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


    public void provasLoad(String id) {
        //Recupera a referencia para a string com ID do curso


        //intent.putExtra("disciplina", ((InicialCursos)getActivity()).getName_course());
//        intent.putExtra("disciplina", "DisciplinaX");
//        intent.putExtra("objectID_disc", "6B5Dw0pqHD");
//        startActivity(intent);


        //Acessa todas as linhas da tabela Disciplinas onde na coluna curso possui o valor contido
        //em id e lista todas as disciplinas e professores dessas


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Prova");
        query.whereEqualTo("disciplina", ParseObject.createWithoutData("Disciplina", id));

        query.findInBackground(new FindCallback<ParseObject>() {
                                   public void done(List<ParseObject> markers, ParseException e) {
                                       if (e == null) {
                                           // your logic here
                                           String name[] = new String[markers.size()];
                                           String data[] = new String[markers.size()];
                                           DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

                                           for (int i = 0; i < markers.size(); i++) {
                                               name[i] = markers.get(i).getString("name");
                                               data[i] = df.format(markers.get(i).getDate("date"));

                                           }

                                           // specify an adapter (see also next example)

                                           mAdapter =  new MyAdapterProvas(getActivity(), name, data);

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


