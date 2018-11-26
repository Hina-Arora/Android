package dropit.hina.dropitproj.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import dropit.hina.dropitproj.R;
import dropit.hina.dropitproj.interfaces.UserDetail;
import dropit.hina.dropitproj.model.UserData;
import dropit.hina.dropitproj.model.UserModel;
import dropit.hina.dropitproj.model.UserResult;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private ArrayList<UserModel> userModel;
    private Context context;
    private ArrayList<String> localImages = new ArrayList<>();
    UserDetail userDetail ;

    public UserListAdapter(Context context, ArrayList<UserModel> userModel,UserDetail userDetail) {
        this.context = context;
        this.userModel = userModel;
        this.userDetail = userDetail;

    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        UserViewHolder vh = new UserViewHolder(v); // pass the view to View Holder
        return vh;
    }
    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        // set the data in items
        holder.name.setText(userModel.get(position).getName());
        Glide.with(context)
                .load(Uri.parse(userModel.get(position).getImage()))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.userImage);

        if(userModel.get(position).getItems() != null){
//            CustomGridView adapter = new CustomGridView(context, userModel.get(position).getItems());
//            holder.gridView.setAdapter(adapter);
            localImages.clear();
            Log.e("UserModel","items size "+userModel.get(position).getItems().size());
            if(userModel.get(position).getItems().size() % 2 != 0 ){
                DisplayMetrics dm = new DisplayMetrics();
                ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
                int width=dm.widthPixels;

                holder.imageView.getLayoutParams().height = (int)width;
                holder.imageView.getLayoutParams().width = (int)width;

                holder.imageView.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(Uri.parse(userModel.get(position).getItems().get(0)))
                        .into(holder.imageView);
                for(int i =1; i < userModel.get(position).getItems().size() ; i++){
                    localImages.add(0,userModel.get(position).getItems().get(i));
                }

            }else{
                holder.imageView.setVisibility(View.GONE);
                localImages.addAll(userModel.get(position).getItems());
            }
            GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
            holder.recyclerView.setLayoutManager(manager);
//            holder.recyclerView.addItemDecoration(new GridInsetDecoration(context));
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, localImages);
            holder.recyclerView.setAdapter(adapter);

            if (position==userModel.size()-1){
                if(userModel.size()%10==0)
                    userDetail.hitToServer(position+1);
            }
        }

    }
    @Override
    public int getItemCount() {
        return userModel == null ? 0 : userModel.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        ImageView userImage;
        RecyclerView recyclerView;
        ImageView imageView;
        private UserViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.user_name_tv);
            userImage = (ImageView) itemView.findViewById(R.id.user_iv);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.items_gv);
            imageView = (ImageView) itemView.findViewById(R.id.image_1);
        }
    }
    public void add(UserModel um) {
        userModel.add(um);
        notifyItemInserted(userModel.size() - 1);
    }

    public void addAll(ArrayList<UserModel> umList) {
        for (UserModel um : umList) {
            add(um);
        }
    }

    public void remove(UserModel city) {
        int position = userModel.indexOf(city);
        if (position > -1) {
            userModel.remove(position);
            notifyItemRemoved(position);
        }
    }


    public UserModel getItem(int position) {
        return userModel.get(position);
    }



}