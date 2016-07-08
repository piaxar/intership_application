package panchenko.ivan.canteen_application.mainActivity.Main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import panchenko.ivan.canteen_application.R;

/**
 * Created by piaxar on 16.06.16.
 */
public class ButtonAdapter extends BaseAdapter {
    private ArrayList<Product> products;
    private Context context;

    public ButtonAdapter(Context context, ArrayList<Product> products) {
        this.products = products;
        this.context = context;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = new Button(context);
            GridView.LayoutParams params = new GridView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setPadding(8, 8, 8, 8);
        } else {
            button = (Button) convertView;
        }
        button.setText(products.get(position).getName());
        button.setTextColor(ContextCompat.getColor(context, R.color.button_product_text));
        // TODO: 19.06.16 add drawable background
        button.setBackgroundColor(ContextCompat.getColor(context, R.color.button_product_background));
        button.setId(position);
        button.setOnClickListener(new ProductOnClickListener(
                products.get(position), context));
        return button;
    }
}
