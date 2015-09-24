package com.ufscar.alunos.mqc.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ufscar.alunos.mqc.R;

public class MenuActivity extends Activity {
    private Button buttonMenuLH,buttonMenuRH, buttonMenuRB,buttonMenuLB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //referencias para conseguir manipular os botoes
        buttonMenuLH = (Button) findViewById(R.id.button_left_high); //botao left high
        buttonMenuRH = (Button) findViewById(R.id.button_right_high); // botao right high
        buttonMenuRB = (Button) findViewById(R.id.button_right_bottom);
        buttonMenuLB = (Button) findViewById(R.id.button_left_bottom);

        //acao dos botoes ao serem clicados
        buttonMenuLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ProvaActivity.class);//sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });

        buttonMenuRH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, TrabalhoActivity.class);//sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });

        buttonMenuRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, FaltasActivity.class);//sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });

        buttonMenuLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, LocalActivity.class);//sao intençoes que o S.O vai inicializar
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

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
