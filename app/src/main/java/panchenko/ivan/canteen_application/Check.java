package panchenko.ivan.canteen_application;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by piaxar on 19.06.16.
 */
public class Check {
    private ArrayList<Product> products;
    private ArrayList<Integer> amount;
    private int summ;

    Check() {
        products = new ArrayList<>();
        amount = new ArrayList<>();
    }

    public void addProduct(Product product) {
        int id = product.getId();
        boolean alreadyAdded = false;
        int index = 0;

        for (int i = 0; i < products.size(); i++) {
            Product prod = products.get(i);
            if (prod.getId() == id) {
                alreadyAdded = true;
                index = i;
            }
        }

        if (alreadyAdded) {
            int current = amount.get(index);
            current = current + 1;
            amount.set(index, current);
        } else {
            products.add(product);
            amount.add(1);
        }

        summ += product.getPrice();
    }

    public Product getItemAt(int position) {
        return products.get(position);
    }

    public int getAmountAt(int position) {
        Log.d("CHECK", Integer.toString(amount.get(position)));
        return amount.get(position);

    }

    public int size() {
        return products.size();
    }

    public int getSumm() {
        return summ;
    }
    // TODO add product deletion
}
