//package com.ufscar.alunos.mqc.Menu;
//
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.ExpandableListView;
//import android.widget.Toast;
//
//import com.ufscar.alunos.mqc.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class ExpandableActivity extends AppCompatActivity {
//
//    private ArrayList<String> listGroup;
//    private HashMap<String, ArrayList<String>> listData;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_expandable);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//        buildList();
//
//        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
//
//        expandableListView.setAdapter(new ExpandableAdapter(this, listGroup, listData));
//
//
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                Toast.makeText(getApplicationContext(), "Group " + i, Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
//
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int i) {
//                Toast.makeText(getApplicationContext(), "Group expand " + i, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int i) {
//                Toast.makeText(getApplicationContext(), "Group collapse" + i, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//    }
//
//    public void buildList(){
//        listGroup = new ArrayList<>();
//        listData = new HashMap<>();
//
//        listGroup.add("Grupo 1");
//        listGroup.add("Grupo 2");
//        listGroup.add("Grupo 3");
//        listGroup.add("Grupo 4");
//
//        ArrayList<String> auxList = new ArrayList<>();
//        auxList.add("item 1");
//        auxList.add("item 2");
//        auxList.add("item 3");
//        auxList.add("item 4");
//
//        listData.put(listGroup.get(0), auxList);
//
//        ArrayList<String> auxList2 = new ArrayList<>();
//        auxList.add("item 1");
//        auxList.add("item 2");
//        auxList.add("item 3");
//        auxList.add("item 4");
//        listData.put(listGroup.get(1), auxList2);
//
//        ArrayList<String> auxList3 = new ArrayList<>();
//        auxList.add("item 1");
//        auxList.add("item 2");
//        auxList.add("item 3");
//        auxList.add("item 4");
//        listData.put(listGroup.get(2), auxList3);
//
//        ArrayList<String> auxList4 = new ArrayList<>();
//        auxList.add("item 1");
//        auxList.add("item 2");
//        auxList.add("item 3");
//        auxList.add("item 4");
//        listData.put(listGroup.get(3),auxList4);
//    }
//
//}
