package com.example.sai.avinash;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class SignUpPage extends AppCompatActivity implements View.OnClickListener{
    Button myDatePickerBtn,mySubmitBtn;
    EditText myTxtDateEditText,myLoginIdEditText,myPasswordEditText,myFirstNameEditText,myLastNameEditText,myEmailIdEditText,myMobileNumberEditText,myAddressEditText,myTotalMeter;
    private int myYear, myMonth, myDay;
    SQLiteDatabase myUserDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        myLoginIdEditText=(EditText)findViewById(R.id.myLoginIdEditText);
        myPasswordEditText=(EditText)findViewById(R.id.myPasswordFieldEditText);
        myFirstNameEditText=(EditText)findViewById(R.id.myFirstNameEditText);
        myLastNameEditText=(EditText)findViewById(R.id.myLastNameEditText);
        myEmailIdEditText=(EditText)findViewById(R.id.myEmailIdEditText);
        myMobileNumberEditText=(EditText)findViewById(R.id.myPhoneNumberEditText);
        myAddressEditText=(EditText)findViewById(R.id.myAddresseEditText);
        myDatePickerBtn=(Button)findViewById(R.id.myDateBtn);
        myTxtDateEditText=(EditText)findViewById(R.id.myIn_DateEditText);
        myTotalMeter=(EditText)findViewById(R.id.myTotalMeter);


        myUserDatabase=openOrCreateDatabase("UserInfo",MODE_PRIVATE,null);
        myUserDatabase.execSQL("create table if not exists details(id varchar,password varchar,noofmeter varchar,firstname varchar,lastname varchar,email varchar,dob varchar,phnnumber varchar, address varchar)");



        mySubmitBtn=(Button)findViewById(R.id.mySubmitBtn);
        mySubmitBtn.setOnClickListener(this);
        myDatePickerBtn.setOnClickListener(this);
    }


    //Getting Date from Calender
    @Override
    public void onClick(View view) {
        if (view == myDatePickerBtn) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            myYear = c.get(Calendar.YEAR);
            myMonth = c.get(Calendar.MONTH);
            myDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            myTxtDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, myYear, myMonth, myDay);
            datePickerDialog.show();
        }
        else if(view==mySubmitBtn)
        {

            myUserDatabase.execSQL("insert into details values('"+myLoginIdEditText.getText().toString()+"','"+myPasswordEditText.getText().toString()+"','"+myTotalMeter.getText().toString()+"','"+myFirstNameEditText.getText().toString()+"','"+myLastNameEditText.getText().toString()+"','"+myEmailIdEditText.getText().toString()+"','"+myTxtDateEditText.getText().toString()+"','"+myMobileNumberEditText.getText().toString()+"','"+myAddressEditText.getText().toString()+"')");
            Toast.makeText(this, "Your data is registered successfully.", Toast.LENGTH_LONG).show();

            myLoginIdEditText.setText("");
            myPasswordEditText.setText("");
            myTotalMeter.setText("");
            myFirstNameEditText.setText("");
            myLastNameEditText.setText("");
            myEmailIdEditText.setText("");
            myMobileNumberEditText.setText("");
            myAddressEditText.setText("");
            myTxtDateEditText.setText("");


            Intent previouspage=new Intent(getApplicationContext(),LoginPage.class);
            startActivity(previouspage);
        }
    }
}