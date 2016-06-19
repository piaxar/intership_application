package panchenko.ivan.canteen_application;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by piaxar on 19.06.16.
 */
public class ItemAdapter extends BaseAdapter {
    Check check;
    Context context;
    LayoutInflater layoutInflater;

    ItemAdapter(Context context, Check check) {
        this.context = context;
        this.check = check;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return check.size();
    }

    @Override
    public Object getItem(int position) {
        return check.getItemAt(position);
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

        Product product = check.getItemAt(position);
        int productAmount = check.getAmountAt(position);
        int summPerProduct = productAmount * product.getPrice();

        ((TextView) view.findViewById(R.id.name_field)).setText(product.getName());
        ((TextView) view.findViewById(R.id.price_field)).setText(Integer.toString(product.getPrice()));
        ((TextView) view.findViewById(R.id.amount)).setText(Integer.toString(productAmount));
        ((TextView) view.findViewById(R.id.total_summ_per_product)).setText(Integer.toString(summPerProduct));

        return view;
    }
}
