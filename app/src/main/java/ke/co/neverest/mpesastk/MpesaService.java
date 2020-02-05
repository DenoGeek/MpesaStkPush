package ke.co.neverest.mpesastk;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import ke.co.neverest.mpesastk.requests.StkRequest;
import ke.co.neverest.mpesastk.responses.MpesaToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MpesaService {

    @GET("oauth/v1/generate?grant_type=client_credentials")
    @Headers({
            "cache-control: no-cache",
    })
    Call<MpesaToken> getToken(@Header("Authorization") String authorization);

    @POST("mpesa/stkpush/v1/processrequest")
    Call<JSONObject> performStk(@Header("Authorization") String authorization, @Body StkRequest request);
}
