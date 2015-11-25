package com.ufscar.alunos.mqc.Menu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.ufscar.alunos.mqc.R;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

public class DiscRegisterActivity extends AppCompatActivity   {

    Button btn_save;
    TextView textView_day_1;
    EditText editText_day_1;

    TextView textView_day_2;
    EditText editText_day_2;
//    Switch switch_seg;

    int year_x, month_x, day_x;
    int hour_x, min_x;

    static final int DIALOG_ID = 0;
    static final int DIALOG_ID_2 = 1;


    private String name_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_register);


        name_course = getIntent().getStringExtra("course");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Nova disciplina");
       // toolbar.setTitle(name_course);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


//        final Calendar cal = Calendar.getInstance();
//        year_x = cal.get(Calendar.YEAR);
//        month_x = cal.get(Calendar.MONTH);
//        day_x = cal.get(Calendar.DAY_OF_MONTH);

//      showDialogOnButtomClick();
        showDialogOnTextViewClick();

        editText_day_1 = (EditText) findViewById(R.id.editText_day_1);
        editText_day_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = editText_day_1.getText().toString();
                if (text.isEmpty()) {
                    textView_day_1.setText("00:00");
                    textView_day_1.setEnabled(false);
                } else {
                    textView_day_1.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        editText_day_2 = (EditText) findViewById(R.id.editText_day_2);
        editText_day_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = editText_day_2.getText().toString();
                if (text.isEmpty()) {
                    textView_day_2.setText("00:00");
                    textView_day_2.setEnabled(false);
                } else {
                    textView_day_2.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_save = (Button) findViewById(R.id.btnSave);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ParseObject saveDisc = new ParseObject("Disciplina");
                EditText name = (EditText) findViewById(R.id.text_name);
                EditText teacher = (EditText) findViewById(R.id.text_teacher);

                saveDisc.put("name", name.getText().toString());
                saveDisc.put("teacher", teacher.getText().toString());

                if(!textView_day_1.isEnabled() && !textView_day_2.isEnabled()){
                    Toast.makeText(DiscRegisterActivity.this, "Selecione um horário", Toast.LENGTH_LONG).show();
                }else if(textView_day_1.isEnabled() && textView_day_1.isEnabled()){

                    saveDisc.put("day_1",editText_day_1.getText().toString());
                    saveDisc.put("hour_1",textView_day_1.getText().toString());

                    saveDisc.put("day_2",editText_day_2.getText().toString());
                    saveDisc.put("hour_2", textView_day_2.getText().toString());


                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Course");
                    query.whereEqualTo("name", name_course);
                    query.whereEqualTo("owner", ParseUser.getCurrentUser());

                    query.findInBackground(new FindCallback<ParseObject>() {
                                               public void done(List<ParseObject> markers, ParseException e) {
                                                   if (e == null) {
                                                       saveDisc.put("owner", ParseUser.getCurrentUser());
                                                       saveDisc.put("course", markers.get(0));
                                                       saveDisc.saveInBackground();

                                                       Intent intent = new Intent(getApplication(), InicialCursos.class);
                                                       startActivity(intent);

                                                       Toast.makeText(getApplicationContext(), "Diciplina salva com sucesso!", Toast.LENGTH_LONG).show();

                                                       finish();
                                                   } else {
                                                       // handle Parse Exception here
                                                       e.getCause();
                                                   }
                                               }
                                           }
                    );

                }else if(textView_day_1.isEnabled()){
                    saveDisc.put("day_1",editText_day_1.getText().toString());
                    saveDisc.put("hour_1",textView_day_1.getText().toString());

                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Course");
                    query.whereEqualTo("name", name_course);
                    query.whereEqualTo("owner", ParseUser.getCurrentUser());

                    query.findInBackground(new FindCallback<ParseObject>() {
                                               public void done(List<ParseObject> markers, ParseException e) {
                                                   if (e == null) {
                                                       saveDisc.put("owner", ParseUser.getCurrentUser());
                                                       saveDisc.put("course", markers.get(0));
                                                       saveDisc.saveInBackground();

                                                       Intent intent = new Intent(getApplication(), InicialCursos.class);
                                                       startActivity(intent);

                                                       Toast.makeText(getApplicationContext(), "Diciplina salva com sucesso!", Toast.LENGTH_LONG).show();

                                                       finish();
                                                   } else {
                                                       // handle Parse Exception here
                                                       e.getCause();
                                                   }
                                               }
                                           }
                    );
                }else{
                    saveDisc.put("day_2",editText_day_2.getText().toString());
                    saveDisc.put("hour_2", textView_day_2.getText().toString());


                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Course");
                    query.whereEqualTo("name", name_course);
                    query.whereEqualTo("owner", ParseUser.getCurrentUser());

                    query.findInBackground(new FindCallback<ParseObject>() {
                                               public void done(List<ParseObject> markers, ParseException e) {
                                                   if (e == null) {
                                                       saveDisc.put("owner", ParseUser.getCurrentUser());
                                                       saveDisc.put("course", markers.get(0));

                                                       saveDisc.saveInBackground();

                                                       Intent intent = new Intent(getApplication(), InicialCursos.class);
                                                       startActivity(intent);

                                                       Toast.makeText(getApplicationContext(), "Diciplina salva com sucesso!", Toast.LENGTH_LONG).show();

                                                       finish();
                                                   } else {
                                                       // handle Parse Exception here
                                                       e.getCause();
                                                   }
                                               }
                                           }
                    );
                }
            }
        });




//        switch_seg = (Switch) findViewById(R.id.switch_seg);
//        switch_seg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    textView_seg_start.setEnabled(true);
//                    switch_seg.setChecked(true);
//                }else{
//                    textView_seg_start.setEnabled(false);
//                    switch_seg.setChecked(false);
//                }
//            }
//        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID)
//            return new DatePickerDialog(this, dpickerListener,year_x, month_x, day_x);
            return new TimePickerDialog(this,R.style.DialogTheme,tpickerListener_day_1,hour_x,min_x,true);

        if(id == DIALOG_ID_2)
            return new TimePickerDialog(this,R.style.DialogTheme,tpickerListener_day_2,hour_x,min_x,true);

        return null;
    }

    private TimePickerDialog.OnTimeSetListener tpickerListener_day_1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            String h,m;

            hour_x = i;
            min_x = i1;

            if(hour_x<10)
                h = "0"+hour_x;
            else
                h = ""+hour_x;

            if(min_x < 10)
                m = "0"+min_x;
            else
                m = ""+min_x;

            textView_day_1.setText(h+":"+m);
        }
    };

    private TimePickerDialog.OnTimeSetListener tpickerListener_day_2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            String h,m;

            hour_x = i;
            min_x = i1;

            if(hour_x<10)
                h = "0"+hour_x;
            else
                h = ""+hour_x;

            if(min_x < 10)
                m = "0"+min_x;
            else
                m = ""+min_x;

            textView_day_2.setText(h+":"+m);
        }
    };

//    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//            year_x = i;
//            month_x = i1 + 1;
//            day_x = i2;
//            Toast.makeText(DiscRegisterActivity.this, year_x+"/"+month_x+"/"+day_x, Toast.LENGTH_LONG).show();
//        }
//    };
//
//   public void showDialogOnButtomClick (){
////       btn = (Button) findViewById(R.id.button_timer);
//
//       btn.setOnClickListener(
//               new View.OnClickListener(){
//                   @Override
//                   public void onClick(View v){
//                        showDialog(DIALOG_ID);
//                   }
//               }
//       );
//   }

    public void showDialogOnTextViewClick (){
        textView_day_1 = (TextView) findViewById(R.id.textView_day_1);
        textView_day_2 = (TextView) findViewById(R.id.textView_day_2);

        textView_day_1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );

        textView_day_2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID_2);
                    }
                }
        );
    }
}
