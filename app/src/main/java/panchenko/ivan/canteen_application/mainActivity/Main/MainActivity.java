package panchenko.ivan.canteen_application.mainActivity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import panchenko.ivan.canteen_application.R;
import panchenko.ivan.canteen_application.mainActivity.Menu.MenuGeneration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static TextView totalCheckSummView;
    static Cheque productsInCheque;
    static ItemAdapter itemAdapter;


    GridView todayMenuGridView;
    ListView cheque;
    Button cancelCheckButton, acceptCheckButton;
    FloatingActionButton editButton;
    ArrayList<Product> products;
    ButtonAdapter buttonAdapter;

    int REQUEST_CODE = 1;

    static void updateSumm() {
        int summ = productsInCheque.getSumm();
        totalCheckSummView.setText(Integer.toString(summ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Bundle extras = data.getExtras();
            ArrayList<Product> menu = extras.getParcelableArrayList("menu");
            buttonAdapter.setProducts(menu);
            buttonAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCheckSummView = (TextView) findViewById(R.id.text_view_cheque_sum);
        cancelCheckButton = (Button) findViewById(R.id.button_cancel_cheque);
        acceptCheckButton = (Button) findViewById(R.id.button_accept_cheque);
        editButton = (FloatingActionButton) findViewById(R.id.button_edit);

        editButton.setOnClickListener(this);

        todayMenuGridView = (GridView) findViewById(R.id.grid_view_products);
        cheque = (ListView) findViewById(R.id.list_view_cheque_items);

        products = new ArrayList<>();
        productsInCheque = new Cheque();
        buttonAdapter = new ButtonAdapter(this, products);
        itemAdapter = new ItemAdapter(this, productsInCheque);

        // TODO generate array from DB
        for (int i = 1; i < 100; i++) {
            Product prod = new Product(Integer.toString(i));
            products.add(prod);
        }

        cheque.setAdapter(itemAdapter);
        todayMenuGridView.setAdapter(buttonAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_edit:
                // TODO get new menu from request
                Intent intent = new Intent(this, MenuGeneration.class);
                startActivityForResult(intent, REQUEST_CODE);
        }
    }


}
