package dropit.hina.dropitproj.endpoint;

import java.util.HashMap;

import dropit.hina.dropitproj.model.UserData;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Endpoints {

    @GET("api/users")
    Call<UserData> getUserDetail(@Query("limit") int limit, @Query("offset") int offset);

}
