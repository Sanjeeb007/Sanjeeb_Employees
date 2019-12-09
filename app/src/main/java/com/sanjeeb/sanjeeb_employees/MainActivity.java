package com.sanjeeb.sanjeeb_employees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_add, btn_edit_update, btn_search, btn_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = findViewById(R.id.btn_signUP);
        btn_add.setOnClickListener(this);
        btn_show = findViewById(R.id.btn_showall);
        btn_show.setOnClickListener(this);
        btn_edit_update = findViewById(R.id.btn_edit_upadte);
        btn_edit_update.setOnClickListener(this);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signUP:
                Intent useradd = new Intent(this, Register.class);
                startActivity(useradd);
                break;
            case R.id.btn_showall:
                Intent showall = new Intent(this, ShowEmployees.class);
                startActivity(showall);
                break;
            case R.id.btn_search:
                Intent search = new Intent(this, UniqueSearch.class);
                startActivity(search);
                break;
            case R.id.btn_edit_upadte:
                Intent edit = new Intent(this, UpdateEdit.class);
                startActivity(edit);
                break;

        }
    }
}
