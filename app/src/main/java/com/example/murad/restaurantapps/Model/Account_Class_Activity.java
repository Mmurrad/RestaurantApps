package com.example.murad.restaurantapps.Model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.murad.restaurantapps.R;

public class Account_Class_Activity extends AppCompatDialogFragment {
    private EditText other_charge,discount;
 //   private AccountClassListener accountClassListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();

        View view=layoutInflater.inflate(R.layout.account_layout,null);

        builder.setView(view).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"Order is not successfully",Toast.LENGTH_LONG).show();
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String other_char=other_charge.getText().toString();
                String discount_char=discount.getText().toString();
               // accountClassListener.sureorder(other_char,discount_char);
                Toast.makeText(getContext(),"Order is successfully",Toast.LENGTH_LONG).show();
            }
        });
        other_charge=view.findViewById(R.id.other_charge_id);
        discount=view.findViewById(R.id.discount_id);
        return builder.create();
    }
   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            accountClassListener=(AccountClassListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement account class");
        }

    }
    public interface AccountClassListener{
        void sureorder(String other_char,String discount);
    }*/


}
