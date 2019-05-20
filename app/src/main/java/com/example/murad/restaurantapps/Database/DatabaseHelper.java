package com.example.murad.restaurantapps.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.murad.restaurantapps.Model.Order_item;
import com.example.murad.restaurantapps.Model.RestaurantItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Order.db";
    public static final String TABLE_NAME="Order_item";
    public static final String ITEM_NAME="Item_name";
    public static final String PRICE="Price";
    public static final String QUANTITY="Quantity";
    public static final String TABLE_NO="Table_no";
    public static final int VERSION_NUMBER=3;

    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+
            TABLE_NO+" INTEGER , "+
            ITEM_NAME+" VARCHAR(20),"+
            PRICE+" VARCHAR(20),"+
            QUANTITY+" VARCHAR(20));";


    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;
    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context,"Oncreate is called",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"Exception "+e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context,"Onupgrade is called",Toast.LENGTH_LONG).show();
           sqLiteDatabase.execSQL(DROP_TABLE);
           onCreate(sqLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context,"Exception "+e,Toast.LENGTH_LONG).show();
        }
    }

    public long inserdata(String table_no,String item,String price,String quantity){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TABLE_NO,table_no);
        contentValues.put(ITEM_NAME,item);
        contentValues.put(PRICE,price);
        contentValues.put(QUANTITY,quantity);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return result;
    }

    public ArrayList<Order_item> showdata(){

        ArrayList<Order_item> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(cursor.moveToFirst()){
            do {
                String item_name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.ITEM_NAME));
                String qty=cursor.getString(cursor.getColumnIndex(DatabaseHelper.QUANTITY));
                String prize=cursor.getString(cursor.getColumnIndex(DatabaseHelper.PRICE));
                Order_item order_item=new Order_item(item_name,qty,prize);
                list.add(order_item);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public Cursor Showitem(DatabaseHelper databaseHelper)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
}
