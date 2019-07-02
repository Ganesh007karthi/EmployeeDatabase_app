package com.ganesh.SQLite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyHolder> {
    public Context context;
    public List<Employee> list=new ArrayList<>();

    public Myadapter(Context context,List<Employee> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowelement,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Employee employee=list.get(i);
        myHolder.employee_name.setText(employee.getEmployeename());
        myHolder.employee_department.setText(employee.getEmployeeDept());
        myHolder.employee_salary.setText(employee.getEmmployeeSalary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
            public TextView employee_name;
            public TextView employee_department;
            public TextView employee_salary;
            public RelativeLayout foreground,background;
            public MyHolder(View view){
                super(view);
                employee_name=view.findViewById(R.id.Employee_name);
                employee_department=view.findViewById(R.id.Employee_department);
                employee_salary=view.findViewById(R.id.Employee_salary);
                foreground=view.findViewById(R.id.view_foreground);
                background=view.findViewById(R.id.view_background);
            }

    }
}
