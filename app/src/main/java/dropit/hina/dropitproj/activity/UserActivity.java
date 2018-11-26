package dropit.hina.dropitproj.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import dropit.hina.dropitproj.R;
import dropit.hina.dropitproj.adapter.UserListAdapter;
import dropit.hina.dropitproj.app.DropItApp;
import dropit.hina.dropitproj.interfaces.UserDetail;
import dropit.hina.dropitproj.listener.PaginationScrollListener;
import dropit.hina.dropitproj.model.UserData;
import dropit.hina.dropitproj.model.UserModel;
import dropit.hina.dropitproj.model.UserResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserActivity extends BaseActivity implements UserDetail {

    @BindView(R.id.user_data_rv)
    RecyclerView userDataRV;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    ArrayList<UserModel> userModel = new ArrayList<>();
    UserData userData = new UserData();
    UserListAdapter adapter = null;
    Boolean hasMore = DropItApp.getInstance().getHasMore();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        userDataRV.setItemAnimator(new DefaultItemAnimator());
        userDataRV.setLayoutManager(layoutManager);
        progressBar.setVisibility(View.GONE);
        adapter  = new UserListAdapter(this,userModel,this);
        userDataRV.setAdapter(adapter);
        getUserDetail(0);

    }

    @Override
    public void hitToServer(int offset) {
        hasMore = DropItApp.getInstance().getHasMore();
        if(hasMore == true)
            getUserDetail(offset);
        else
            showToast("No more data to load!!");
    }

    private void getUserDetail(int offset) {
        if (!connectivityCheckUtility.isConnected()) {
            showAlert("No Internet", "Please check your internet setting");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        Call<UserData> call = DropItApp.getInstance().getEndPoints().getUserDetail(10,offset);
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    if(response.body().getUserResult() != null && response.body().getUserResult().getUserModel() != null) {
                        hasMore = response.body().getUserResult().getHasMore();
                        DropItApp.getInstance().setHasMore(hasMore);
                        ArrayList<UserModel> model = new ArrayList<>();
                        model = response.body().getUserResult().getUserModel();
                        userModel.addAll(model);
                        adapter.notifyDataSetChanged();

                    }

                } else {
                    handleError(response);
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                showToast("Server error");
            }

        });
    }

}
