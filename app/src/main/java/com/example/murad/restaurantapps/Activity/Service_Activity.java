package com.example.murad.restaurantapps.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.murad.restaurantapps.Model.Account_Class_Activity;
import com.example.murad.restaurantapps.Database.DatabaseHelper;
import com.example.murad.restaurantapps.Database.MyDatabaseHelper;
import com.example.murad.restaurantapps.Model.CustomAdapter;
import com.example.murad.restaurantapps.Model.Order_item;
import com.example.murad.restaurantapps.R;

import java.util.ArrayList;

public class Service_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText item_name,quantity,other_charge,discount_charge;
    RadioButton cash,card,other;
    int price=0,quant=0, each_price=0 ,total=0;

    Spinner table_no;
    String per_price = null;
    String[] table_list={"   1","   2","   3","   4","   5","   6","   7","   8","   9","   10","   11","   12","   13","   14","   15"};
    ArrayAdapter<String> arrayAdapter;
    TextView add_amount,total_amount,final_amount,grand_amount;
    Button next_button,submit_button;
    ListView listView;
    MyDatabaseHelper myDatabaseHelper;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_);
        item_name=(EditText)findViewById(R.id.service_item_id);
        quantity=(EditText)findViewById(R.id.service_quantity_id);
        other_charge=(EditText)findViewById(R.id.other_charge_id);
        discount_charge=(EditText)findViewById(R.id.discount_id);

        add_amount=(TextView)findViewById(R.id.per_price_id);
        total_amount=(TextView)findViewById(R.id.total_price_id);
        final_amount=(TextView)findViewById(R.id.total_id);
        grand_amount=(TextView)findViewById(R.id.grand_id);

        next_button=(Button) findViewById(R.id.next_id);
        submit_button=(Button) findViewById(R.id.submit_id);

        table_no=(Spinner)findViewById(R.id.tableno_id);

        listView=(ListView)findViewById(R.id.orderlist);

        myDatabaseHelper=new MyDatabaseHelper(this);
        databaseHelper=new DatabaseHelper(this);

        arrayAdapter=new ArrayAdapter<String>(this,R.layout.sample_view,R.id.textviewid,table_list);
        table_no.setAdapter(arrayAdapter);

        next_button.setOnClickListener(this);
        submit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Cursor cursor=(Cursor) myDatabaseHelper.Showitem(myDatabaseHelper);
        final String item=item_name.getText().toString();
        String item_quantuity=quantity.getText().toString();
        String table_number=table_no.getSelectedItem().toString();
       if(view.getId()==R.id.next_id){
            if(item.equals("")||item_quantuity.equals("")){
                Toast.makeText(getApplicationContext(),"Please type your order",Toast.LENGTH_LONG).show();
            }
            else {
                if (cursor.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Item is not available", Toast.LENGTH_LONG).show();
                } else {
                    while (cursor.moveToNext()) {
                        if (cursor.getString(0).equals(item)) {
                            CustomAdapter customAdapter=new CustomAdapter(this,databaseHelper.showdata());
                            listView.setAdapter(customAdapter);

                            price = Integer.parseInt(cursor.getString(1));
                            quant = Integer.parseInt(item_quantuity);
                            each_price = quant * price;

                            per_price = String.valueOf(each_price);
                            add_amount.setText(per_price);
                            Toast.makeText(getApplicationContext(), "The value is " + quant * price, Toast.LENGTH_LONG).show();

                            total = (total + (quant * price));
                            String total_price = String.valueOf(total);
                            total_amount.setText(total_price);
                            //arraylist();
                        }
                    }
                }
            }
            loaddata();
       }

       if(view.getId()==R.id.submit_id){

           AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(Service_Activity.this);
           alertdialogbuilder.setTitle("Do You Want To Print?");
           alertdialogbuilder.setMessage("Payment mode : ");
           LayoutInflater layoutInflater=getLayoutInflater();
           View view1=layoutInflater.inflate(R.layout.radio_button_sample,null);
           alertdialogbuilder.setView(view1);
           alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   Intent intent = new Intent(Service_Activity.this, Homedelivery_Activity.class);
                   startActivity(intent);
               }
           });
           alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   showaccount();
               }

               private void showaccount() {

                   Account_Class_Activity account_class_activity=new Account_Class_Activity();
                   account_class_activity.show(getSupportFragmentManager(),"Account Class Activity");
               }
           });
           cash=view.findViewById(R.id.cash_id);
           card=view.findViewById(R.id.card_id);
           other=view.findViewById(R.id.other_id);
           AlertDialog alertDialog=alertdialogbuilder.create();
           alertDialog.show();
       }
    }


    private void loaddata() {
        String item=item_name.getText().toString();
        String item_quantuity=quantity.getText().toString();
        String table_number=table_no.getSelectedItem().toString();
        long result=databaseHelper.inserdata(table_number,item,per_price,item_quantuity);
        if(result==-1){
            Toast.makeText(getApplicationContext(),"Item is not inserted",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Item inserted",Toast.LENGTH_LONG).show();
        }
    }
}
