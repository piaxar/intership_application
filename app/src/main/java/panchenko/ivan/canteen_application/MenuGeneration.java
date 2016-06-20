package panchenko.ivan.canteen_application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;

/**
 * Created by piaxar on 20.06.16.
 */
public class MenuGeneration extends Activity {

    ListView allMenues, menuItems, allItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        allMenues = (ListView) findViewById(R.id.list_view_all_menues);
        menuItems = (ListView) findViewById(R.id.list_view_menu_items);
        allItems = (ListView) findViewById(R.id.list_view_items_available);

    }

}
