package panchenko.ivan.canteen_application.mainActivity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import panchenko.ivan.canteen_application.R;
import panchenko.ivan.canteen_application.mainActivity.Menu.MenuGeneration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static TextView totalCheckSumTextView;
    static Cheque cheque;
    static ItemAdapter itemAdapter;

    GridView todayMenuGridView;
    ListView chequeListView;
    Button cancelCheckButton, acceptCheckButton;
    ImageButton editButton;
    ArrayList<Product> products;
    ButtonAdapter buttonAdapter;

    int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCheckSumTextView = (TextView) findViewById(R.id.text_view_cheque_sum);
        cancelCheckButton = (Button) findViewById(R.id.button_cancel_cheque);
        acceptCheckButton = (Button) findViewById(R.id.button_accept_cheque);
        editButton = (ImageButton) findViewById(R.id.button_edit);
        todayMenuGridView = (GridView) findViewById(R.id.grid_view_products);
        chequeListView = (ListView) findViewById(R.id.list_view_cheque_items);

        cancelCheckButton.setOnClickListener(this);
        acceptCheckButton.setOnClickListener(this);
        editButton.setOnClickListener(this);

        // array of <Product> that contain all products in current menu
        products = new ArrayList<>();
        cheque = new Cheque();
        buttonAdapter = new ButtonAdapter(this, products);
        itemAdapter = new ItemAdapter(this, cheque);

        chequeListView.setAdapter(itemAdapter);
        todayMenuGridView.setAdapter(buttonAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_edit:
                Intent intent = new Intent(this, MenuGeneration.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.button_cancel_cheque:
                cheque.clearCheque();
                itemAdapter.notifyDataSetChanged();
                updateSumm();
                break;
            case R.id.button_accept_cheque:
                // TODO add event to cope with cheque
                break;
        }
    }

    /**
     * Update TextView with sum from cheque
     */
    static void updateSumm() {
        int summ = cheque.getSumm();
        totalCheckSumTextView.setText(Integer.toString(summ));
    }

    /**
     * Method after getting menu from MenuGeneration activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
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
}
