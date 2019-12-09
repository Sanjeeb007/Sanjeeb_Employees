package com.sanjeeb.sanjeeb_employees;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sanjeeb.sanjeeb_employees.database.DBHelper;
import com.sanjeeb.sanjeeb_employees.model.EmployeeS;

public class UpdateEdit extends AppCompatActivity {
    Button btn_search, btn_edit,btn_dele;
    EditText et_name, et_salary, et_age, et_search;
    DBHelper dbh = new DBHelper(this);
    String name;
    int age, salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_edit);
        btn_search = findViewById(R.id.btn_se_update);
        btn_edit = findViewById(R.id.btn_edit);
        btn_dele = findViewById(R.id.btn_delete);
        et_search = findViewById(R.id.txtsearch);
        et_name = findViewById(R.id.up_name);
        et_age = findViewById(R.id.up_age);
        et_salary = findViewById(R.id.up_salary);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = dbh.searchEmployess(Integer.parseInt(et_search.getText().toString()));
                if (cursor.getCount() == 0) {
                    // show message
                    Toast.makeText(UpdateEdit.this, "No found", Toast.LENGTH_SHORT).show();
                    et_name.setText("not found");
                    et_name.setEnabled(false);
                    et_age.setText("null");
                    et_age.setEnabled(false);
                    et_salary.setText("null");
                    et_salary.setEnabled(false);
                }
                while (cursor.moveToNext()) {
                    name = cursor.getString(1);
                    age = cursor.getInt(3);
                    salary = cursor.getInt(2);
                    et_name.setText(name);
                    et_name.setEnabled(true);
                    et_age.setText(String.valueOf(age));
                    et_age.setEnabled(true);
                    et_salary.setText(String.valueOf(salary));
                    et_salary.setEnabled(true);
                }

            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_name.getText().toString())) {
                    if (!TextUtils.isEmpty(et_salary.getText().toString())) {
                        if (!TextUtils.isEmpty(et_age.getText().toString())) {
                            String name = et_name.getText().toString();
                            int age = Integer.parseInt(et_age.getText().toString());
                            int salary = Integer.parseInt(et_salary.getText().toString());
                            EmployeeS es = new EmployeeS();
                            es.setId(Integer.parseInt(et_search.getText().toString()));
                            es.setAge(age);
                            es.setSalary(salary);
                            es.setName(name);
                            if (dbh.Update(es)) {
                                et_name.setText("");
                                et_salary.setText("");
                                et_age.setText("");
                                Toast msg = Toast.makeText(UpdateEdit.this, "update success.", Toast.LENGTH_SHORT);
                                msg.show();
                            } else {
                                Toast msg = Toast.makeText(UpdateEdit.this, "Error Update! Please try again later.", Toast.LENGTH_SHORT);
                                msg.show();
                            }

                        } else {
                            et_age.setError("enter age");
                        }

                    } else {
                        et_salary.setError("enter salary");
                    }
                } else {
                    et_name.setError("enter name");
                }
            }
        });
        btn_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_search.getText().toString())) {
                    int id=Integer.parseInt(et_search.getText().toString());
                    if (dbh.Delete(id)) {
                        Toast msg = Toast.makeText(UpdateEdit.this, "delete success.", Toast.LENGTH_SHORT);
                        msg.show();
                    }else{
                        Toast msg = Toast.makeText(UpdateEdit.this, "delete un success.", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                }else{
                    et_search.setError("enter id first");
                }


            }
        });
    }

}
