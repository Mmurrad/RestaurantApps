package com.example.murad.restaurantapps.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.murad.restaurantapps.Database.MyDatabaseHelper;
import com.example.murad.restaurantapps.R;

public class Add_Item_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText item_name,price;
    Button add_item_button;
    Spinner active;
    String[] activelist={"   Yes","   No"};
    ArrayAdapter<String> activeadapter;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item_);
        myDatabaseHelper=new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();

        item_name=(EditText) findViewById(R.id.item_name_id);
        price=(EditText) findViewById(R.id.price_id);
        active=(Spinner)findViewById(R.id.spinnerid);
        add_item_button=(Button)findViewById(R.id.add_item_button);

        activeadapter=new  ArrayAdapter<String>(this,R.layout.sample_view,R.id.textviewid,activelist);
        active.setAdapter(activeadapter);

        add_item_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String item=item_name.getText().toString();
        String item_price=price.getText().toString();
        String active_item=active.getSelectedItem().toString();

        if(view.getId()==R.id.add_item_button){
           long result= myDatabaseHelper.inserdata(item,item_price,active_item);
           if(result==-1){
               Toast.makeText(getApplicationContext(),"Item is not inserted",Toast.LENGTH_LONG).show();
           }else {
               Toast.makeText(getApplicationContext(),"Item inserted",Toast.LENGTH_LONG).show();
           }
        }
    }
}
