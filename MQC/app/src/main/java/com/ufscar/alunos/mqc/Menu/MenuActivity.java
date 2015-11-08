package com.ufscar.alunos.mqc.Menu;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
//import com.parse.*;

import com.ufscar.alunos.mqc.R;

public class MenuActivity extends Fragment  {
    private Button buttonMenuLH,buttonMenuRH, buttonMenuRB,buttonMenuLB;
    private Toolbar mToobar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

     View rootView =   inflater.inflate(R.layout.activity_menu, container, false);
        //referencias para conseguir manipular os botoes


        buttonMenuLH = (Button) rootView.findViewById(R.id.button_left_high); //botao left high
        buttonMenuRH = (Button) rootView.findViewById(R.id.button_right_high); // botao right high
        buttonMenuRB = (Button) rootView.findViewById(R.id.button_right_bottom);
        buttonMenuLB = (Button) rootView.findViewById(R.id.button_left_bottom);



        buttonMenuLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProvaActivity.class);//sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });

         //acao dos botoes ao serem clicados
        buttonMenuLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProvaActivity.class); //sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });

        buttonMenuRH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrabalhoActivity.class);//sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });

        buttonMenuRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FaltasActivity.class);//sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });

        buttonMenuLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LocalActivity.class);//sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });


        // Enable Local Datastore.
        /*Parse.enableLocalDatastore(getActivity());

        Parse.initialize(getActivity(), "MZILWm0EqFyy1lOauqRy9gHB1a4j5kJZ6pW1Z6U5", "hzVeLBtrkieewXP3r1WfvFMlh1xK33LAdH0SNV7b");
//*/
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();

        return rootView ;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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

}
