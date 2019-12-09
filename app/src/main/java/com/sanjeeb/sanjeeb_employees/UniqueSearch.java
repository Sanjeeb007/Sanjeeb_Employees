package com.sanjeeb.sanjeeb_employees;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeeb.sanjeeb_employees.adapter.AdapterRecycleView;
import com.sanjeeb.sanjeeb_employees.database.DBHelper;
import com.sanjeeb.sanjeeb_employees.model.EmployeeS;

import java.util.ArrayList;
import java.util.List;

public class UniqueSearch extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recyclerView;
    String name;
    int age, salary, id;
    DBHelper dbh = new DBHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_search);
        searchView = findViewById(R.id.txt_search);
        recyclerView = findViewById(R.id.RVunique);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Cursor cursor = dbh.searchEmployess(Integer.parseInt(query));
                if (cursor.getCount() == 0) {
                    // show message
                    Toast.makeText(UniqueSearch.this, "No found", Toast.LENGTH_SHORT).show();
                    return false;
                }
                while (cursor.moveToNext()) {
                    List<EmployeeS> employeeSList = new ArrayList<>();
                    id = cursor.getInt(0);
                    name = cursor.getString(1);
                    age = cursor.getInt(3);
                    salary = cursor.getInt(2);
                    employeeSList.add(new EmployeeS(id,name,salary,age));
                    AdapterRecycleView adapterRecycleView = new AdapterRecycleView(getApplicationContext(), employeeSList);

                    recyclerView.setAdapter(adapterRecycleView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(UniqueSearch.this));

                }



                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // public void search(List<EmployeeS> show_employees) {
        //
    }
}
