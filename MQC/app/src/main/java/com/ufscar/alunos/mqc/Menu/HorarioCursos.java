package com.ufscar.alunos.mqc.Menu;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.parse.*;

import com.ufscar.alunos.mqc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HorarioCursos extends Fragment {

    private ArrayList<String> listGroup;
    private HashMap<String, ArrayList<String>> listData;
    private HashMap<String, TextView> listTextViewHour;

    private final String[] days = {"seg", "ter", "quar", "quin", "sex"};


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View rootView = inflater.inflate(R.layout.activity_horario_cursos, container, false);

        listTextViewHour = new HashMap<>();

        listTextViewHour.put("seg", (TextView) rootView.findViewById(R.id.seg_textView));
        listTextViewHour.put("ter", (TextView) rootView.findViewById(R.id.ter_textView));
        listTextViewHour.put("quar", (TextView) rootView.findViewById(R.id.quar_textView));
        listTextViewHour.put("quin", (TextView) rootView.findViewById(R.id.quin_textView));
        listTextViewHour.put("sex", (TextView) rootView.findViewById(R.id.sex_textView));

        discLoad();

//        buildList();
//
//        ExpandableListView expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
//
//        expandableListView.setAdapter(new ExpandableAdapter(getContext(), listGroup, listData));
//
//
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                Toast.makeText(getContext(), "Group " + i, Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
//
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int i) {
//                Toast.makeText(getContext(), "Group expand " + i, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int i) {
//                Toast.makeText(getContext(), "Group collapse" + i, Toast.LENGTH_SHORT).show();
//            }
//        });

       // expandableListView.setGroupIndicator(getResources().getDrawable());

       return rootView;
    }

    public void buildList(){
        listGroup = new ArrayList<>();
        listData = new HashMap<>();

        listGroup.add("Grupo 1");
        listGroup.add("Grupo 2");
        listGroup.add("Grupo 3");
        listGroup.add("Grupo 4");

        ArrayList<String> auxList = new ArrayList<>();
        auxList.add("item 1");
        auxList.add("item 2");
        auxList.add("item 3");
        auxList.add("item 4");
        listData.put(listGroup.get(0), auxList);

        ArrayList<String> auxList2 = new ArrayList<>();
        auxList.add("item 1");
        auxList.add("item 2");
        auxList.add("item 3");
        auxList.add("item 4");
        listData.put(listGroup.get(1), auxList2);

        ArrayList<String> auxList3 = new ArrayList<>();
        auxList.add("item 1");
        auxList.add("item 2");
        auxList.add("item 3");
        auxList.add("item 4");
        listData.put(listGroup.get(2), auxList3);

        ArrayList<String> auxList4 = new ArrayList<>();
        auxList.add("item 1");
        auxList.add("item 2");
        auxList.add("item 3");
        auxList.add("item 4");
        listData.put(listGroup.get(3),auxList4);
    }


    public void discLoad(){

        //Acessa todos os dados de uma tabela
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Disciplina");
        query.whereEqualTo("owner", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
                                   public void done(List<ParseObject> markers, ParseException e) {
                                       if (e == null) {

                                           for(ParseObject p: markers){
                                               if (!p.getString("day_1").isEmpty()){

                                                   TextView t = listTextViewHour.get(p.getString("day_1"));
                                                   String text = p.getString("hour_1")+" - "+p.getString("name")+"\n";
                                                   t.append(text);
                                               }

                                               if (!p.getString("day_2").isEmpty()){

                                                   TextView t = listTextViewHour.get(p.getString("day_2"));
                                                   String text = p.getString("hour_2")+" - "+p.getString("name")+"\n";
                                                   t.append(text);
                                               }
                                           }

                                           for (int i =0;i< listTextViewHour.size();i++) {
                                               if(listTextViewHour.get(days[i]).getText().toString().isEmpty())
                                                   listTextViewHour.get(days[i]).setText("Você não tem nenhuma disciplina neste dia!");
                                           }

                                       } else {
                                           // handle Parse Exception here
                                           e.getCause();
                                       }
                                   }
                               }
        );
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
