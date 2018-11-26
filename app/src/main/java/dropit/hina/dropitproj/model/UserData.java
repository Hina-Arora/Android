package dropit.hina.dropitproj.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserData extends BaseModel{
    @SerializedName("data")
    private UserResult userResult;

    public UserResult getUserResult() {
        return userResult;
    }

    public void setUserResult(UserResult userModel) {
        this.userResult = userModel;
    }

}
