package dropit.hina.dropitproj.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResult {
    @SerializedName("users")
    private ArrayList<UserModel> userModel;

    public ArrayList<UserModel> getUserModel() {
        return userModel;
    }

    public void setUserModel(ArrayList<UserModel> userModel) {
        this.userModel = userModel;
    }

}
