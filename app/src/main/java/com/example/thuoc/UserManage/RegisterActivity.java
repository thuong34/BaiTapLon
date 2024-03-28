package com.example.thuoc.UserManage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thuoc.Database.CreateDatabase;
import com.example.thuoc.R;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    EditText name, address, phone, pass;
    Button register;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
    CreateDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        CreateDatabase createDatabase = new CreateDatabase(this);

        name = (EditText) findViewById(R.id.edName);
        address = (EditText) findViewById(R.id.edAdress);
        phone = (EditText) findViewById(R.id.edPhone);
        pass = (EditText) findViewById(R.id.edPass);
        register= (Button) findViewById(R.id.btnRegister);

        mydb = new CreateDatabase(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String a = address.getText().toString();
                String ph = phone.getText().toString();
                String p = pass.getText().toString();

                if (n.equals("")||  a.equals("")|| ph.equals("")|| p.equals("") ){
                    Toast.makeText(RegisterActivity.this,"Fill all the fieds",Toast.LENGTH_SHORT).show();
                }else{
                    if(checkDataEntered()== true){
                        Boolean reResult = mydb.insertData(n,a,ph,p);
                        if(reResult== true ){
                            Toast.makeText(RegisterActivity.this,"Register Successful",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    public Boolean checkDataEntered(){

        String s = name.getText().toString();
        String p1 = phone.getText().toString();
        String s1 = pass.getText().toString();
        if(s.length()<5 ){
            name.setError("Phải ít nhất 5 kí tự");
            return false;
        }
        if(p1.length()<10){
            phone.setError("Số điện thoại phải có 10 số");
            return false;
        }
        if(s1.length()<5 ){
            pass.setError("Mật khẩu phải ít nhất 5 kí tự");
            return false;
        }
        return true;
    }



}