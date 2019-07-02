package com.ganesh.SQLite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ganesh.SQLite.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        EditText Employee_name,Employee_salary;

        public List<Employee> list=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        TextView addemployee=findViewById(R.id.tv_view_employee);
        Employee_name=findViewById(R.id.Ed_Employee_Name);
        Employee_salary=findViewById(R.id.Ed_Employee_Salary);
        final String dept=spinner.getSelectedItem().toString();
        Button btn=findViewById(R.id.btn_employee);
        addemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Viewemployee.class);
                startActivity(intent);



            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateEmployee(Employee_name.getText().toString(),spinner.getSelectedItem().toString(),Employee_salary.getText().toString());

            }
        });
    }
    public void CreateEmployee(String Employee_name,String Employee_Dept,String Employee_Salary) {
        // inserting note in db and getting
        // newly inserted note id
        DatabaseHelper db=new DatabaseHelper(this);
        long id = db.insert(Employee_name,Employee_Dept,Employee_Salary);

        // get the newly inserted note from db
        Employee n = db.getEmployee(id);

        if (n != null) {
            // adding new note to array list at 0 position
            list.add(0, n);

            // refreshing the list



        }
    }
}

