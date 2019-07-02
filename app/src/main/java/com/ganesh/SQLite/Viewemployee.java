package com.ganesh.SQLite;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.ganesh.SQLite.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Viewemployee extends AppCompatActivity {

    RecyclerView recyclerView;
    Myadapter myadapter;
   public List<Employee> list=new ArrayList<>();
    DatabaseHelper db;
    TextView nonotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewemployee);
        recyclerView=findViewById(R.id.recycler_view);
        nonotes=findViewById(R.id.empty_notes_view);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        getdata();
        myadapter= new Myadapter(this,list);
        new ItemTouchHelper(itemtouchhelper).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(myadapter);
        Toggledetail();


    }
    public void  getdata(){
        DatabaseHelper db=new DatabaseHelper(this);

        list.addAll(db.getallEmployee());
    }



    public void Toggledetail(){
        DatabaseHelper db=new DatabaseHelper(this);
        if(db.cursorcount()>0){
            nonotes.setVisibility(View.GONE);
        }else {
            nonotes.setVisibility(View.VISIBLE);
        }
    }
    ItemTouchHelper.SimpleCallback itemtouchhelper=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
          deleteEmployee(viewHolder.getAdapterPosition());

        }
        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            if(viewHolder!=null){
                final View foregroundView=((Myadapter.MyHolder)viewHolder).foreground;
                getDefaultUIUtil().onSelected(foregroundView);
            }
        }
        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            final View foregroundView=((Myadapter.MyHolder)viewHolder).foreground;
            getDefaultUIUtil().clearView(foregroundView);
        }

        @Override
        public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            final View foregroundView=((Myadapter.MyHolder)viewHolder).foreground;
            getDefaultUIUtil().onDrawOver(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            final View foregroundView=((Myadapter.MyHolder)viewHolder).foreground;
            getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
        }


    };

    public void deleteEmployee(int index){
        DatabaseHelper db=new DatabaseHelper(this);
        db.deleteNote(list.get(index));
        list.remove(index);
        myadapter.notifyDataSetChanged();
    }


}
