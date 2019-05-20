package com.example.murad.restaurantapps.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.murad.restaurantapps.Model.RestaurantItem;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Restaurant.dp";
    public static final String TABLE_NAME="Restaurant_table";
    public static final String ITEM_NAME="Item_name";
    public static final String PRICE="Price";
    public static final String ACTIVE="Active";
    public static final String TABLE_NO="Table_no";
    public static final int VERSION_NUMBER=2;

    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+ITEM_NAME+" VARCHAR(20),"+PRICE+" VARCHAR(20),"+ACTIVE+" VARCHAR(20),"+TABLE_NO+" VARCHAR(20));";
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;

    private Context context;
    public MyDatabaseHelper( Context context) {
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

    public long inserdata(String item,String price,String active){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ITEM_NAME,item);
        contentValues.put(PRICE,price);
        contentValues.put(ACTIVE,active);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return result;
    }

    public List<RestaurantItem> showdata(){
        ArrayList<RestaurantItem> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(cursor.moveToFirst()){
            do {
                String item_name=cursor.getString(0);
                String price=cursor.getString(1);
                String active=cursor.getString(2);
                RestaurantItem restaurantItem=new RestaurantItem(item_name,price,active);
                list.add(restaurantItem);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public Cursor Showitem(MyDatabaseHelper myDatabaseHelper)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
}
