package com.example.murad.restaurantapps.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.murad.restaurantapps.Database.MyDatabaseHelper;
import com.example.murad.restaurantapps.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView add_item,search_item,new_order,edit_order,item_review,report;

    AlertDialog.Builder alertdialogbuilder;

    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper=new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase= myDatabaseHelper.getWritableDatabase();
        add_item=(CardView)findViewById(R.id.additem);
        search_item=(CardView)findViewById(R.id.searchitem);
        new_order=(CardView)findViewById(R.id.neworder);
        edit_order=(CardView)findViewById(R.id.editorder);
        item_review=(CardView)findViewById(R.id.itermreview);
        report=(CardView)findViewById(R.id.report);

        add_item.setOnClickListener(this);
        search_item.setOnClickListener(this);
        new_order.setOnClickListener(this);
        edit_order.setOnClickListener(this);
        item_review.setOnClickListener(this);
        report.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.additem){
            Intent intent=new Intent(MainActivity.this,Add_Item_Activity.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.neworder){
            alertdialogbuilder=new AlertDialog.Builder(MainActivity.this);

            alertdialogbuilder.setMessage("Select Type of Service");
            alertdialogbuilder.setCancelable(false);
            alertdialogbuilder.setPositiveButton("PARCEL",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent=new Intent(MainActivity.this,Parcel_Activity.class);
                    startActivity(intent);
                }
            });

            alertdialogbuilder.setNegativeButton("SERVICE",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=new Intent(MainActivity.this,Service_Activity.class);
                            startActivity(intent);
                        }
                    });

            alertdialogbuilder.setNeutralButton("HOMEDELIVERY",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=new Intent(MainActivity.this,Homedelivery_Activity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog alertDialog=alertdialogbuilder.create();
            alertDialog.show();
        }
    }
}
