package com.ufscar.alunos.mqc.Menu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.ufscar.alunos.mqc.R;

import Logic.Course;

public class CourseRegisterActivity extends AppCompatActivity {

    private int selectedColor;
    private Button btn_save;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Novo curso");
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //instância um novo curso
        course = new Course();

        // Obtém  o array de cores.
        final String[] colors = getResources().getStringArray(R.array.colors);

        //Cria o adapter.
        final ColorsAdapter adapter = new ColorsAdapter(this, R.layout.spinner_row, colors);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Listener a ser chamado quando uma cor for seleccionada no Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Obtém e guarda a cor selecionada
                //selectedColor = adapter.getColor(position);
                course.setColor(colors[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final EditText editTextName = (EditText) findViewById(R.id.name);
        final EditText editTextLocal = (EditText) findViewById(R.id.local);

        btn_save = (Button) findViewById(R.id.button_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                course.setName(editTextName.getText().toString());
                course.setLocal(editTextLocal.getText().toString());

                //salvando objeto no banco
                ParseObject saveCourse = new ParseObject("Course");

                saveCourse.put("name", course.getName());
                saveCourse.put("local", course.getLocal());
                saveCourse.put("color", course.getColor());
                saveCourse.put("owner", ParseUser.getCurrentUser());
                saveCourse.saveInBackground();


                Context context = getApplicationContext();
                CharSequence text = "Curso salvo com sucesso!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                finish();
            }
        });



    }

}
