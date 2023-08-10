package com.example.ex1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.time.LocalDate;


public class MainActivity extends AppCompatActivity {
    EditText nameInput;
    EditText destinationInput;
    EditText descriptionInput;
    Button submitBtn;
    TextView dotControl;
    String radioButton;
    RadioButton yes, no;
    Toolbar myToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding Toolbar
        myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);

        // Adding behavior to the date input
        dotControl = findViewById(R.id.dot_input);
        dotControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        nameInput = findViewById(R.id.name_input);
        destinationInput = findViewById(R.id.destination_input);
        descriptionInput = findViewById(R.id.description_input);
        yes = (RadioButton) findViewById(R.id.rb_option_yes);
        no = (RadioButton) findViewById(R.id.rb_option_no);
        submitBtn = (Button) findViewById(R.id.add_btn);
        // Adding behavior to the button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yes.isChecked()){
                    radioButton = yes.getText().toString();
                }else if(no.isChecked()){
                    radioButton = no.getText().toString();
                }else{
                    radioButton = "null";
                }

                if(TextUtils.isEmpty(nameInput.getText().toString())
                        || TextUtils.isEmpty(destinationInput.getText().toString())
                        || TextUtils.isEmpty(descriptionInput.getText().toString())
                        || TextUtils.isEmpty(dotControl.getText().toString())
                        || radioButton == "null"
                ){
                    startActivity(new Intent(MainActivity.this, SecondHandleErrorActivity.class));
                }else{
                    Intent sendData = new Intent(MainActivity.this, DisplayInformationActivity.class);
                    sendData.putExtra("getName", nameInput.getText().toString());
                    sendData.putExtra("getDestination", destinationInput.getText().toString());
                    sendData.putExtra("getDescription", descriptionInput.getText().toString());
                    sendData.putExtra("getDot", dotControl.getText().toString());
                    sendData.putExtra("getRequireRisk",radioButton);
                    startActivity(sendData);
                }
            }
        });
    }


    // DatePicker Fragment inside MainActivity
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
            LocalDate d = LocalDate.now();
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);
        }
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            /*        LocalDate dot = LocalDate.of(year, ++month, day);*/
            ((MainActivity) requireActivity()).updateDOT(year, ++month, day);
        }
    }

    // Update the selected date back to the modal
    public void updateDOT(int year, int month, int day){
        TextView dotControl = findViewById(R.id.dot_input);

        String extraDay, extraMonth;
        if(day <10){
            extraDay = "0";
        }else {
            extraDay = "";
        }

        if(month <10){
            extraMonth = "0";
        }else {
            extraMonth = "";
        }

        dotControl.setText(extraDay+ day + "/" + extraMonth + month + "/" + year);
    }

}