package com.example.administrator.hmxfirstapp.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.hmxfirstapp.Menu.MenuContent;
import com.example.administrator.hmxfirstapp.R;
import java.lang.Object;import java.lang.Override;

/**
 * Created by Stas Melnychenko on 23.07.2015.
 */
public class MenuAdapter extends BaseAdapter {
    private Context context;

    public MenuAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MenuContent.ITEMS.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            row = LayoutInflater.from(context).inflate(R.layout.fragment_menu_item, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.image.setImageResource(MenuContent.ITEMS.get(position).iconId);
        holder.name.setText(MenuContent.ITEMS.get(position).textId);
        return row;
    }

    static class ViewHolder {
        public final ImageView image;
        public final TextView name;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.fragment_menu_icon);
            name = (TextView) view.findViewById(R.id.fragment_menu_text);
        }
    }
}
