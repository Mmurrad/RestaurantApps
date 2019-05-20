package com.example.murad.restaurantapps.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.murad.restaurantapps.Database.MyDatabaseHelper;
import com.example.murad.restaurantapps.R;

import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {

    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);
        listView=(ListView)findViewById(R.id.search_list_id);
        arrayList=new ArrayList<>();
        myDatabaseHelper=new MyDatabaseHelper(this);


        Cursor cursor=myDatabaseHelper.Showitem(myDatabaseHelper);
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No item is found",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(0)+"\t\t\t\t"+cursor.getString(1));
            }
            arrayAdapter=new ArrayAdapter(this,R.layout.sample_view,R.id.textviewid,arrayList);
            listView.setAdapter(arrayAdapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        MenuItem menuItem=menu.findItem(R.id.search_id);
        SearchView searchView=(SearchView)MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> list=new ArrayList<>();
                for(String user:arrayList){
                    if(user.toLowerCase().contains(newText.toLowerCase())){
                        list.add(user);
                    }
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(Search_Activity.this,R.layout.sample_view,R.id.textviewid,list);

                listView.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
