package dropit.hina.dropitproj.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserModel {

    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;
    @SerializedName("items")
    private ArrayList<String> items;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }



}
