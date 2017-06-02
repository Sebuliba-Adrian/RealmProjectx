package com.afrojazz.myrealmproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afrojazz.myrealmproject.models.Customer;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Button add, view, update, delete;
    EditText cust_no, name;
    TextView text;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.add);
        view = (Button) findViewById(R.id.view);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        cust_no = (EditText) findViewById(R.id.cust_no);
        name = (EditText) findViewById(R.id.cust_name);
        text = (TextView) findViewById(R.id.text);


        Realm.init(this);
        realm = Realm.getDefaultInstance();

    }

    public void clickAction(View view) {
        switch (view.getId()) {
            case R.id.add:
                addRecord();
                break;
            case R.id.view:
                viewRecord();
                break;
            case R.id.update:
                updateRecord();
                break;
            case R.id.delete:
                deleteRecord();
        }
    }

    private void deleteRecord() {
        RealmResults<Customer> results = realm.where(Customer.class).equalTo("customer_number", Integer.parseInt(cust_no.getText().toString())).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    private void updateRecord() {
        RealmResults<Customer> results = realm.where(Customer.class).equalTo("customer_number", Integer.parseInt(cust_no.getText().toString())).findAll();
        realm.beginTransaction();
        for (Customer customer : results) {

            customer.setCustomer_name(name.getText().toString());
        }
        realm.commitTransaction();

    }

    private void viewRecord() {
        RealmResults<Customer> results = realm.where(Customer.class).findAll();
        text.setText("");
        for (Customer customer : results) {

            text.append(customer.getCustomer_number() + " " + customer.getCustomer_name() + "\n");
        }
    }

    private void addRecord() {
        realm.beginTransaction();
        Customer customer = realm.createObject(Customer.class);
        customer.setCustomer_number(Integer.parseInt(cust_no.getText().toString()));
        customer.setCustomer_name(name.getText().toString());
        realm.commitTransaction();

    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}