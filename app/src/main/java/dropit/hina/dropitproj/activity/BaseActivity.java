package dropit.hina.dropitproj.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;

import dropit.hina.dropitproj.model.BaseModel;
import dropit.hina.dropitproj.utility.ConnectivityCheckUtility;
import dropit.hina.dropitproj.utility.ErrorHandlingUtility;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {

    public AlertDialog.Builder alertDialog;
    public ConnectivityCheckUtility connectivityCheckUtility;

    int userType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectivityCheckUtility = new ConnectivityCheckUtility(getApplicationContext());

    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showAlert(String title, String message) {
        AlertDialog dialog = null;
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = alertDialog.create();

        dialog.show();
    }


    public ProgressDialog progressDialog;

    public void showProgressDialog(String title, String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void handleError(Response<?> response) {
        BaseModel errorModel = ErrorHandlingUtility.parseError(response);
        if (errorModel != null) {
            if (!errorModel.isStatus()) {
                if (errorModel.getError() != null) {
                    showAlert("Oops!", errorModel.getError());
                } else {
                    showAlert("Oops!", "Something went wrong");
                }
            }
        } else {
            showAlert("Oops!", "Something went wrong");
        }
    }


}
