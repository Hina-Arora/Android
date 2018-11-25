package dropit.hina.dropitproj.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageViewHolder imageViewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
            imageViewHolder = new ImageViewHolder();
            imageViewHolder.imageView = convertView.findViewById(R.id.item_iv);

            convertView.setTag(imageViewHolder);
        } else {
            imageViewHolder = (ImageViewHolder) convertView.getTag();
        }

        Glide.with(context)
                    .load(Uri.parse(item.get(position)))
                    .into(imageViewHolder.imageView);
        return convertView;

    }

    public class ImageViewHolder {
        ImageView imageView;
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


