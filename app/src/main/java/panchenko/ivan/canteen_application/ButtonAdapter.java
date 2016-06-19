package panchenko.ivan.canteen_application;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

import java.util.ArrayList;

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
            GridView.LayoutParams params = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setPadding(8, 8, 8, 8);
        } else {
            button = (Button) convertView;
        }
        button.setText(products.get(position).getName());
        button.setTextColor(Color.WHITE);
        button.setId(position);
        button.setOnClickListener(new ProductOnClickListener(
                products.get(position), context));
        return button;
    }
}
