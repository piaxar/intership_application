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
    GridView todayMenuGridView;
    ListView check;
    Button cancelCheckButton, acceptCheckButton;
    ArrayList<Product> products;
    static Check productsInCheck;
    static ItemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        products = new ArrayList<>();
        productsInCheck = new Check();

        totalCheckSummView = (TextView) findViewById(R.id.total_summ_text_view);
        cancelCheckButton = (Button) findViewById(R.id.cancel_check_button);
        acceptCheckButton = (Button) findViewById(R.id.accept_check_button);
        todayMenuGridView = (GridView) findViewById(R.id.grid_view);
        check = (ListView) findViewById(R.id.check_list_view);

        for (int i = 1; i < 15; i++) {
            Product prod = new Product(Integer.toString(i));
            products.add(prod);
        }
        ButtonAdapter buttonAdapter = new ButtonAdapter(this, products);
        itemAdapter = new ItemAdapter(this, productsInCheck);

        check.setAdapter(itemAdapter);
        todayMenuGridView.setAdapter(buttonAdapter);
    }

    static void updateSumm() {
        int summ = productsInCheck.getSumm();
        totalCheckSummView.setText(Integer.toString(summ));
    }
}
