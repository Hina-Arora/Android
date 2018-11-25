package dropit.hina.dropitproj.utility;


import java.io.IOException;
import java.lang.annotation.Annotation;

import dropit.hina.dropitproj.BuildConfig;
import dropit.hina.dropitproj.model.BaseModel;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ErrorHandlingUtility {

    public static BaseModel parseError(Response<?> response) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Converter<ResponseBody, BaseModel> converter = retrofit.responseBodyConverter(BaseModel.class, new Annotation[0]);

        BaseModel errorModel = null;

        try {
            if(response.errorBody() != null){
                errorModel = converter.convert(response.errorBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return errorModel;
    }
}
