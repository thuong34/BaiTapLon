package com.example.thuoc.UserManage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thuoc.Database.CreateDatabase;
import com.example.thuoc.MainActivity;
import com.example.thuoc.R;

public class LoginActivity extends AppCompatActivity {
    EditText phone, pass;
    Button login;
    LinearLayout register;
    CreateDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText) findViewById(R.id.edPhone);
        pass = (EditText) findViewById(R.id.edPass);
        login = (Button) findViewById(R.id.btnLogin);
        register = findViewById(R.id.layout_register);

        mydb = new CreateDatabase(this);

        setupListeners();
    }
    private void setupListeners(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String ph = phone.getText().toString();
                String p = pass.getText().toString();
                if(checkUser()==true){
                    Boolean rs = mydb.checkPhone(ph);
                    if(rs == true){
                        Boolean result = mydb.checkPhonePass(ph,p);
                        if(result == true){
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(LoginActivity.this,"This phone number does not exist",Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    public boolean checkUser(){

        if(isEmpty(phone)){
            phone.setError("You must enter username to login");
            return  false;
        }

        if(isEmpty(pass)){
            pass.setError("You must enter password to login");
            return   false;
        }

        return true;

    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}