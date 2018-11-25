package dropit.hina.dropitproj.adapter;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dropit.hina.dropitproj.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<String> items = new ArrayList<>();
    Context context;


    public RecyclerViewAdapter(Context context, ArrayList<String> items) {

        this.items = items;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public ViewHolder(View v) {

            super(v);
            imageView = (ImageView) v.findViewById(R.id.item_iv);

        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Glide.with(context)
                .load(Uri.parse(items.get(position)))
                .into(Vholder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
