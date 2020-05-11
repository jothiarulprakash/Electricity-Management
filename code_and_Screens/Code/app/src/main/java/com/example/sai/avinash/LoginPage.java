package com.example.sai.avinash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
EditText user,password;
Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        user=(EditText)findViewById(R.id.UserNameEditText);
        password=(EditText)findViewById(R.id.PasswordEditText);
        login=(Button)findViewById(R.id.Loginbutton);
        signup=(Button)findViewById(R.id.SignUpbutton2);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String s1,s2;
        s1=user.getText().toString();
        s2=password.getText().toString();
        if(view==login)
        {
            if(s1.equals("admin")&&s2.equals("admin"))
            {
                Intent newpage=new Intent(getApplicationContext(),TabPage.class);
                startActivity(newpage);
                finish();

            }
            else
            {
                Toast.makeText(this,"Incorrect password",Toast.LENGTH_LONG).show();

            }
            user.setText("");
            password.setText("");
        }

        else if(view==signup)
        {
            Intent signuppage=new Intent(getApplicationContext(),SignUpPage.class);
            startActivity(signuppage);
            user.setText("");
            password.setText("");
        }
    }
}
