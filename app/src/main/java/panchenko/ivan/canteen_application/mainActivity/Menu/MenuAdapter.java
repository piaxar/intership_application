package panchenko.ivan.canteen_application.mainActivity.Menu;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import panchenko.ivan.canteen_application.R;

/**
 * Created by piaxar on 07.07.16.
 */
public class MenuAdapter extends CustomAdapter<Menu> {

    View lastView;

    MenuAdapter(Context context, ArrayList<Menu> arrayList) {
        super(context, arrayList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.menus_list, parent, false);
        }

        Menu menu = arrayList.get(position);

        ((TextView) view.findViewById(R.id.text_view_menu_name)).setText(menu.name);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (lastView != null) {

                        lastView.setBackground(context.getDrawable(R.drawable.menu_item_usual));
                    }
                    v.setBackground(context.getDrawable(R.drawable.menu_item_selector));
                    lastView = v;
                }
                MenuGeneration.holder.showMenu(position);
            }
        });
        return view;
    }

}
