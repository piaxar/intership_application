package panchenko.ivan.canteen_application.mainActivity.Main;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by piaxar on 16.06.16.
 */
public class ProductOnClickListener implements View.OnClickListener {
    private Product product;
    private Context context;

    public ProductOnClickListener(Product product, Context context) {
        this.context = context;
        this.product = product;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Product " + product.getName() + " added", Toast.LENGTH_SHORT).show();
        MainActivity.productsInCheque.addProduct(product);
        MainActivity.itemAdapter.notifyDataSetChanged();
        MainActivity.updateSumm();
    }
}
