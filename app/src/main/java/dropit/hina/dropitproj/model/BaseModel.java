package dropit.hina.dropitproj.model;


import com.google.gson.annotations.SerializedName;

public class BaseModel {
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("status")
    boolean status;

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    @SerializedName("has_more")
    Boolean hasMore;
    @SerializedName("message")
    String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @SerializedName("error")
    String error;
}
