package com.ganesh.SQLite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.ganesh.SQLite.Employee;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int Database_version=1;
    private static final String Database_Name="Employee_Database";

    public DatabaseHelper(Context context){
        super(context,Database_Name,null,Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Employee.CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Employee.Table_Name);
        onCreate(db);
    }

    public long insert(String Employee_Name,String EmployeeDept,String Employeesalary){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Employee.Column_EmployeeName,Employee_Name);
        values.put(Employee.Column_EmployeeDept,EmployeeDept);
        values.put(Employee.Column_EmployeeSalary,Employeesalary);

        long id=db.insert(Employee.Table_Name,null,values);
        db.close();
        return id;

    }

    public Employee getEmployee(long id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Employee.Table_Name,new String[]{Employee.Column_id,Employee.Column_EmployeeName,Employee.Column_EmployeeDept,Employee.Column_EmployeeSalary},
                Employee.Column_id+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Employee employee=new Employee(cursor.getInt(cursor.getColumnIndex(Employee.Column_id)),cursor.getString(cursor.getColumnIndex(Employee.Column_EmployeeName)),
                cursor.getString(cursor.getColumnIndex(Employee.Column_EmployeeDept)),cursor.getString(cursor.getColumnIndex(Employee.Column_EmployeeSalary)));
        db.close();
        return employee;
    }

    public List<Employee> getallEmployee(){
        List<Employee> list=new ArrayList<>();
        String SelectQuery="SELECT * FROM "+Employee.Table_Name;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(SelectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Employee employee=new Employee();
                employee.setId(cursor.getInt(cursor.getColumnIndex(Employee.Column_id)));
                employee.setEmployeename(cursor.getString(cursor.getColumnIndex(Employee.Column_EmployeeName)));
                employee.setEmployeeDept(cursor.getString(cursor.getColumnIndex(Employee.Column_EmployeeDept)));
                employee.setEmmployeeSalary(cursor.getString(cursor.getColumnIndex(Employee.Column_EmployeeSalary)));
                list.add(employee);
            }while (cursor.moveToNext());
            db.close();
        }
        return list;
    }

    public int cursorcount(){
        SQLiteDatabase db=this.getReadableDatabase();
        String countQuery="SELECT * FROM "+Employee.Table_Name;
        Cursor cursor=db.rawQuery(countQuery,null);
        int count=cursor.getCount();
        return count;
    }


    public void deleteNote(Employee Employee){
            SQLiteDatabase db=this.getWritableDatabase();
            db.delete(Employee.Table_Name,Employee.Column_id+" = ? ",new String[]{String.valueOf(Employee.getId())});
            close();

    }

}
