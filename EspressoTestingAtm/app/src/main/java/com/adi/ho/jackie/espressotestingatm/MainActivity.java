package com.adi.ho.jackie.espressotestingatm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private float balanceAmount;
    private TextView balance;
    private EditText transactionEdit;
    private ListView transactionList;
    private ArrayList<String> transactionArrayList;
    private ArrayAdapter<String> transactionListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        balance = (TextView)findViewById(R.id.balance_textview);
        transactionEdit = (EditText)findViewById(R.id.input_balanceedittext);
        transactionList = (ListView)findViewById(R.id.transaction_listview);

        transactionArrayList = new ArrayList<>();
        transactionListAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,transactionArrayList);
        transactionList.setAdapter(transactionListAdapter);
    }

    public void withdraw(View v){
        if (balance.getText().toString() != null && balance.getText().toString() != "") {
            String currentBalance = balance.getText().toString().substring(1);
            float newBalance = Float.parseFloat(currentBalance) - Float.parseFloat(transactionEdit.getText().toString());
            if (newBalance >= 0) {
                currentBalance = DecimalFormat.getCurrencyInstance().format(newBalance);
                balance.setText(currentBalance);
                transactionArrayList.add(0, "Withdraw: " + transactionEdit.getText().toString());
                transactionListAdapter.notifyDataSetChanged();
                transactionEdit.setText("");
            } else {

                transactionEdit.setText("");
                Toast.makeText(MainActivity.this, "No More Money, transaction canceled.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void deposit(View v) {
        if (balance.getText().toString() != null && balance.getText().toString() != "") {
            String currentBalance = balance.getText().toString().substring(1);
            float newBalance = Float.parseFloat(currentBalance) + Float.parseFloat(transactionEdit.getText().toString());
            currentBalance = DecimalFormat.getCurrencyInstance().format(newBalance);
            balance.setText(currentBalance);
            transactionArrayList.add(0, "Deposit: " + transactionEdit.getText().toString());
            transactionListAdapter.notifyDataSetChanged();
            transactionEdit.setText("");
        }
    }

    public void goToAccountInfo(View v){
        Intent intent = new Intent(MainActivity.this,AccountInfoActivity.class);
        startActivity(intent);
    }
}
