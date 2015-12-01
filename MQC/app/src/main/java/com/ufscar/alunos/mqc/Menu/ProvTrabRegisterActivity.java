package com.ufscar.alunos.mqc.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ufscar.alunos.mqc.R;

public class ProvTrabRegisterActivity extends AppCompatActivity {

    private String evento;
    private String disc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prov_trab_register);

        evento = getIntent().getStringExtra("evento");
        disc = getIntent().getStringExtra("disciplina");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(evento.equalsIgnoreCase("prova")){
            toolbar.setTitle("Nova prova");
        }else
            toolbar.setTitle("Novo trabalho");

        setSupportActionBar(toolbar);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent intent = new Intent(getApplication(), InicialProvTrab.class);
        intent.putExtra("disciplina",disc);
        startActivity(intent);

        finish();
    }

}
