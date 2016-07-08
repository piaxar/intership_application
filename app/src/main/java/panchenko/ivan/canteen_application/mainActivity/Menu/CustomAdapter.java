package panchenko.ivan.canteen_application.mainActivity.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by piaxar on 07.07.16.
 */
public class CustomAdapter<L> extends BaseAdapter {

    ArrayList<L> arrayList;
    Context context;
    LayoutInflater layoutInflater;

    CustomAdapter(Context context, ArrayList<L> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
