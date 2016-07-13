package panchenko.ivan.canteen_application.mainActivity.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    ArrayList<Product> inMenu, notInMenu, allProducts;
    ArrayList<Menu> menus;
    MenuAdapter menuAdapter;
    ProductsAdapter productsAdapter;
    AllProductsAdapter allProductsAdapter;
    Button chooseMenu, addProduct;
    ImageButton addMenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        firstListView = (ListView) findViewById(R.id.list_view_all_menus);
        secondListView = (ListView) findViewById(R.id.list_view_menu_items);
        thirdListView = (ListView) findViewById(R.id.list_view_items_available);
        chooseMenu = (Button) findViewById(R.id.button_apply_menu);
        addMenu = (ImageButton) findViewById(R.id.button_add_menu);
        addProduct = (Button) findViewById(R.id.button_add_product);

        menus = new ArrayList<>();
        inMenu = new ArrayList<>();
        notInMenu = new ArrayList<>();
        allProducts = new ArrayList<>();

        holder = new Holder();

        menus = getMenus();

        menuAdapter = new MenuAdapter(getApplicationContext(), menus);
        productsAdapter = new ProductsAdapter(getApplicationContext(), inMenu);
        allProductsAdapter = new AllProductsAdapter(getApplicationContext(), notInMenu);


        firstListView.setAdapter(menuAdapter);
        secondListView.setAdapter(productsAdapter);
        // TODO when menu becomes empty, maby we need to delete it?
        // if yes, then you need to check is secondListView adapter is empty

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
        addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add new menu addition
            }
        });
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add product addition
                //also make this button unavailable until
                //one of the menus will be selected
            }
        });
    }

    @Override
    protected void onStop() {
        //TODO sync with server
        super.onStop();
    }

    /**
     * This method must return all available menus from server
     * also in this method I set up allProducts array,
     * but you need to split it into new method
     *
     * @return array list of menus
     */
    private ArrayList<Menu> getMenus() {
        ArrayList<Menu> allMenus = new ArrayList<Menu>();
        // TODO fill getting menus from server
        for (int i = 0; i < 30; i++) {
            Menu menu = new Menu();
            menu.name = Integer.toString(i);
            ArrayList<Product> products = new ArrayList<>();
            for (int j = i; j < (i + 3); j++) {
                Product prod = new Product(Integer.toString(j));
                products.add(prod);
                allProducts.add(prod);

            }
            menu.products = products;
            allMenus.add(menu);
        }
        return allMenus;
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
