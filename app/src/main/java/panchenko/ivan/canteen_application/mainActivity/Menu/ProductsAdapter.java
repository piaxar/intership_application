package panchenko.ivan.canteen_application.mainActivity.Menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import panchenko.ivan.canteen_application.R;
import panchenko.ivan.canteen_application.mainActivity.Main.Product;

/**
 * Created by piaxar on 07.07.16.
 */
public class ProductsAdapter extends CustomAdapter<Product> {

    ProductsAdapter(Context context, ArrayList<Product> arrayList) {
        super(context, arrayList);
    }

    public void setNewArray(ArrayList<Product> products) {
        arrayList = products;
    }

    public ArrayList<Product> getMenu() {
        return arrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.available_product_menu, parent, false);
        }

        Product product = arrayList.get(position);

        ((TextView) view.findViewById(R.id.text_view_dish_name)).setText(product.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuGeneration.holder.deleteFromMenu(position);
            }
        });
        return view;
    }

}