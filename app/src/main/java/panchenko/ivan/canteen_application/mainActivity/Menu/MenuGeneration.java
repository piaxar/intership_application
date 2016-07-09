package panchenko.ivan.canteen_application.mainActivity.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import panchenko.ivan.canteen_application.R;
import panchenko.ivan.canteen_application.mainActivity.Main.Product;

/**
 * Created by piaxar on 06.07.16.
 */
public class MenuGeneration extends AppCompatActivity {

    static Holder holder;
    ListView firstListView, secondListView, thirdListView;
    ArrayList<Product> inMenue, notInMenu, allProducts;
    ArrayList<Menu> menus;
    MenuAdapter menuAdapter;
    ProductsAdapter productsAdapter;
    AllProductsAdapter allProductsAdapter;
    ImageButton chooseMenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        firstListView = (ListView) findViewById(R.id.list_view_all_menus);
        secondListView = (ListView) findViewById(R.id.list_view_menu_items);
        thirdListView = (ListView) findViewById(R.id.list_view_items_available);
        chooseMenu = (ImageButton) findViewById(R.id.button_apply_menu);

        menus = new ArrayList<>();
        inMenue = new ArrayList<>();
        notInMenu = new ArrayList<>();
        allProducts = new ArrayList<>();

        holder = new Holder();

        for (int i = 0; i < 5; i++) {
            Menu menu = new Menu();
            menu.name = Integer.toString(i);
            ArrayList<Product> products = new ArrayList<>();
            for (int j = i; j < (i + 3); j++) {
                Product prod = new Product(Integer.toString(j));
                products.add(prod);
                allProducts.add(prod);

            }
            menu.products = products;
            menus.add(menu);
        }

        menuAdapter = new MenuAdapter(getApplicationContext(), menus);
        productsAdapter = new ProductsAdapter(getApplicationContext(), inMenue);
        allProductsAdapter = new AllProductsAdapter(getApplicationContext(), notInMenu);


        firstListView.setAdapter(menuAdapter);
        secondListView.setAdapter(productsAdapter);
        thirdListView.setAdapter(allProductsAdapter);
        chooseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle b = new Bundle();
                b.putParcelableArrayList("menu", (ArrayList<? extends Parcelable>) productsAdapter.getMenu());
                intent.putExtras(b);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public class Holder {
        int currentMenu;

        public void showMenu(int position) {
            currentMenu = position;

            ArrayList<Product> prods = menus.get(currentMenu).products;

            productsAdapter.setNewArray(prods);
            productsAdapter.notifyDataSetChanged();

            ArrayList<Product> notInMenu = findNotInMenu(position);

            allProductsAdapter.setNewArray(notInMenu);
            allProductsAdapter.notifyDataSetChanged();
        }

        public void deleteFromMenu(int position) {
            menus.get(currentMenu).products.remove(position);
            productsAdapter.notifyDataSetChanged();

            ArrayList<Product> notInMenu = findNotInMenu(currentMenu);
            allProductsAdapter.setNewArray(notInMenu);
            allProductsAdapter.notifyDataSetChanged();
        }

        public void addInMenu(int position) {
            menus.get(currentMenu).products.add((Product) allProductsAdapter.getItem(position));
            productsAdapter.notifyDataSetChanged();

            ArrayList<Product> notInMenu = findNotInMenu(currentMenu);
            allProductsAdapter.setNewArray(notInMenu);
            allProductsAdapter.notifyDataSetChanged();
        }

        public ArrayList<Product> findNotInMenu(int menu) {
            ArrayList<Product> notInMenue = new ArrayList<>();
            ArrayList<Product> inMenue = menus.get(menu).products;
            for (Product productAll : allProducts) {
                boolean find = false;
                for (Product productMenu : inMenue) {
                    if (productAll.getId() == productMenu.getId()) {
                        find = true;
                    }
                }
                if (!(find)) {
                    notInMenue.add(productAll);
                }
            }
            return notInMenue;
        }
    }
}
