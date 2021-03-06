package com.ufscar.alunos.mqc.Menu;

import android.provider.CalendarContract;
import android.provider.ContactsContract;
import java.util.GregorianCalendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.ufscar.alunos.mqc.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProvTrabRegisterActivity extends AppCompatActivity {

    private String evento;
    private String disc;
    private String objectID_disc; //objectID

    int year_x, month_x, day_x;
    EditText editText;

    private final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prov_trab_register);

        evento = getIntent().getStringExtra("evento");
        disc = getIntent().getStringExtra("disciplina");
        objectID_disc = getIntent().getStringExtra("objectID_disc");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(evento.equalsIgnoreCase("prova")){
            toolbar.setTitle("Nova prova");
        }else
            toolbar.setTitle("Novo trabalho");

        setSupportActionBar(toolbar);

        showDialogOnTextViewClick();

        Calendar c = Calendar.getInstance();
        year_x = c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);

        Button btn = (Button) findViewById(R.id.button_save_pt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trabalho ou curso
                final ParseObject savept = new ParseObject(evento);

                EditText name = (EditText) findViewById(R.id.editText_name_pt);
                EditText desc = (EditText) findViewById(R.id.editText2_desc_pt);

                EditText editTextdate = (EditText) findViewById(R.id.editText_date);
                String sdate = editTextdate.getText().toString();

                DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                Date date = null;
                try{
                   date = formatter.parse(sdate);
                }catch (Exception e){
//                    Log.i("ERRO NA CONVERSAO DA DATA","ERRO");
                }

               final String descri= desc.getText().toString();
                final String title = name.getText().toString();
                savept.put("name", name.getText().toString());
                savept.put("descricao", desc.getText().toString());
                savept.put("date",date);

                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Disciplina");

                query.whereEqualTo("objectId",objectID_disc);

                query.findInBackground(new FindCallback<ParseObject>() {
                                           public void done(List<ParseObject> markers, ParseException e) {
                                               if (e == null) {
                                                   savept.put("disciplina", markers.get(0));
                                                   savept.saveInBackground();

                                                   Intent intent = new Intent(getApplication(), InicialProvTrab.class);
                                                   intent.putExtra("disciplina", disc);
                                                   intent.putExtra("objectID_disc", objectID_disc);
                                                   startActivity(intent);

                                                   if(evento.equalsIgnoreCase("prova"))
                                                     Toast.makeText(getApplicationContext(), "Prova salva com sucesso!", Toast.LENGTH_LONG).show();
                                                   else
                                                       Toast.makeText(getApplicationContext(), "Trabalho salvo com sucesso!", Toast.LENGTH_LONG).show();

                                                   //insere no google calendar
                                                   Intent intentCalendar = new Intent(Intent.ACTION_INSERT);
                                                   intentCalendar.setType("vnd.android.cursor.item/event");
                                                   intentCalendar.putExtra(CalendarContract.Events.TITLE, title + "-" + disc);
                                                   intentCalendar.putExtra(CalendarContract.Events.DESCRIPTION, descri);
                                                   GregorianCalendar calDate = new GregorianCalendar(day_x, month_x, year_x);
                                                   intentCalendar.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                                                   intentCalendar.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                                                           calDate.getTimeInMillis());
                                                   intentCalendar.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                                                           calDate.getTimeInMillis());
                                                   startActivity(intentCalendar);

                                                   finish();
                                               } else {
                                                   // handle Parse Exception here
                                                   e.getCause();
                                               }
                                           }
                                       }
                );
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener,year_x, month_x, day_x);

        return null;
    }


    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year_x = i;
            month_x = i1 + 1;
            day_x = i2;
            String text = day_x+"/"+month_x+"/"+year_x;

            editText.setText(text);

//            Toast.makeText(ProvTrabRegisterActivity.this, year_x+"/"+month_x+"/"+day_x, Toast.LENGTH_LONG).show();
        }
    };

    public void showDialogOnTextViewClick (){
        editText = (EditText) findViewById(R.id.editText_date);
        editText.setInputType(InputType.TYPE_NULL);

        editText.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent intent = new Intent(getApplication(), InicialProvTrab.class);
        intent.putExtra("disciplina",disc);
        intent.putExtra("objectID_disc",objectID_disc);
        startActivity(intent);

        finish();
    }

}
