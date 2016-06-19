package panchenko.ivan.canteen_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static TextView totalCheckSummView;
    static Cheque productsInCheque;
    static ItemAdapter itemAdapter;

    GridView todayMenuGridView;
    ListView cheque;
    Button cancelCheckButton, acceptCheckButton;
    ArrayList<Product> products;
    ButtonAdapter buttonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCheckSummView = (TextView) findViewById(R.id.text_view_cheque_sum);
        cancelCheckButton = (Button) findViewById(R.id.button_cancel_cheque);
        acceptCheckButton = (Button) findViewById(R.id.button_accept_cheque);
        todayMenuGridView = (GridView) findViewById(R.id.grid_view_products);
        cheque = (ListView) findViewById(R.id.list_view_cheque_items);

        products = new ArrayList<>();
        productsInCheque = new Cheque();
        buttonAdapter = new ButtonAdapter(this, products);
        itemAdapter = new ItemAdapter(this, productsInCheque);

        // TODO generate array from DB
        for (int i = 1; i < 15; i++) {
            Product prod = new Product(Integer.toString(i));
            products.add(prod);
        }

        cheque.setAdapter(itemAdapter);
        todayMenuGridView.setAdapter(buttonAdapter);
    }

    static void updateSumm() {
        int summ = productsInCheque.getSumm();
        totalCheckSummView.setText(Integer.toString(summ));
    }
}
