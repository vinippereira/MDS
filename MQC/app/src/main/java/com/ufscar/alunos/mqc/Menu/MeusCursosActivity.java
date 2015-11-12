package com.ufscar.alunos.mqc.Menu;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.parse.*;

import android.support.design.widget.FloatingActionButton;

import com.ufscar.alunos.mqc.R;

import java.util.List;

public class MeusCursosActivity extends Fragment {

    private FloatingActionButton couseAdd;
    //Identificadores para a lista
    private ListView mListView;
    private MyAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View v = inflater.inflate(R.layout.activity_meus_cursos, container, false);
        mListView = (ListView) v.findViewById(R.id.cursos_list_view);

        //Acessa todos os dados de uma tabela
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Course");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> markers, ParseException e) {
                if (e == null) {
                    // your logic here
                    String aux[] = new String[markers.size()];

                    for(int i = 0; i<markers.size();i++){
                        aux[i] = markers.get(i).getString("name");
                    }

                    // specify an adapter (see also next example)

                    mAdapter = new MyAdapter(getActivity() , aux);
                    mListView.setAdapter(mAdapter);


                } else {
                    // handle Parse Exception here
                    e.getCause();
                }
            }
        });

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

}
