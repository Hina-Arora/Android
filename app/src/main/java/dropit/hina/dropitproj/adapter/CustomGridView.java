package dropit.hina.dropitproj.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dropit.hina.dropitproj.R;

public class CustomGridView extends BaseAdapter{
    private Context context;
    private ArrayList<String> item;

    public CustomGridView(Context context,ArrayList<String> item ) {
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.recycler_view_item, null); // inflate the layout
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_iv); // get the reference of ImageView
        Glide.with(context)
                    .load(Uri.parse(item.get(position)))
                    .into(imageView);
        return convertView;

    }

    @Override
    public int getViewTypeCount() {
        return item.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}