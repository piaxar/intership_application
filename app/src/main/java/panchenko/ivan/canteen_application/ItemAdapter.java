package panchenko.ivan.canteen_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by piaxar on 19.06.16.
 */
public class ItemAdapter extends BaseAdapter {
    Cheque cheque;
    Context context;
    LayoutInflater layoutInflater;

    ItemAdapter(Context context, Cheque cheque) {
        this.context = context;
        this.cheque = cheque;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cheque.size();
    }

    @Override
    public Object getItem(int position) {
        return cheque.getItemAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item, parent, false);
        }

        Product product = cheque.getItemAt(position);
        int productAmount = cheque.getAmountAt(position);
        int summPerProduct = productAmount * product.getPrice();

        ((TextView) view.findViewById(R.id.text_view_dish_name)).setText(product.getName());
        ((TextView) view.findViewById(R.id.text_view_one_dish_price)).setText(
                Integer.toString(product.getPrice()) + " * " + Integer.toString(productAmount));
        ((TextView) view.findViewById(R.id.text_view_total_dish_price)).setText(Integer.toString(summPerProduct));

        return view;
    }
}
