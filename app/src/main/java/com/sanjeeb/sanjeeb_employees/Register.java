package com.sanjeeb.sanjeeb_employees;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sanjeeb.sanjeeb_employees.database.DBHelper;
import com.sanjeeb.sanjeeb_employees.model.EmployeeS;

public class Register extends AppCompatActivity {
    EditText etName, etSalary, etage;
    Button btnRegister;
    DBHelper dbh = new DBHelper(this);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.et_name);
        etage = findViewById(R.id.et_age);
        etSalary = findViewById(R.id.et_salary);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
store();
            }
        });
    }

    void store() {
        if (!TextUtils.isEmpty(etName.getText().toString())) {
            if (!TextUtils.isEmpty(etSalary.getText().toString())) {
                if (!TextUtils.isEmpty(etage.getText().toString())) {
                    String name = etName.getText().toString();
                    int age = Integer.parseInt(etage.getText().toString());
                    int salary = Integer.parseInt(etSalary.getText().toString());
                    EmployeeS es= new EmployeeS();
                    es.setAge(age);
                    es.setSalary(salary);
                    es.setName(name);
                    if (dbh.adduser(es) ) {
                        etName.setText("");
                        etSalary.setText("");
                        etage.setText("");
                        Toast msg = Toast.makeText(this,"SignUp  success.",Toast.LENGTH_SHORT);
                        msg.show();
                    }else {
                        Toast msg = Toast.makeText(this,"Error signing up! Please try again later.",Toast.LENGTH_SHORT);
                        msg.show();
                    }

                } else {
                    etage.setError("enter age");
                }

            } else {
                etSalary.setError("enter salary");
            }
        } else {
            etName.setError("enter name");
        }
    }
}
