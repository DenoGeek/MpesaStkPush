package ke.co.neverest.mpesastk;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import ke.co.neverest.mpesastk.requests.StkRequest;
import ke.co.neverest.mpesastk.responses.MpesaToken;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static final int CONNECT_TIMEOUT = 60 * 1000;
    public static final int READ_TIMEOUT = 60 * 1000;
    public static final int WRITE_TIMEOUT = 60 * 1000;


    private Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intialize();
        try {
            getToken();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error",e.getMessage());
        }

    }

    public void intialize(){

        Gson gson=new Gson();

        OkHttpClient.Builder client = new OkHttpClient().newBuilder();
        client
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor logger=new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);


        client.addInterceptor(logger);

        retrofit=new Retrofit.Builder()
                .baseUrl("https://sandbox.safaricom.co.ke/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();


    }

    public void getToken() throws Exception{

        /*

         */
        String app_key = "tbIv9jb0Gxn2HspyQEcNIQb6kRldzmnb";
        String app_secret = "UJA18Xp08Wf4y9Lp";
        String appKeySecret = app_key + ":" + app_secret;
        byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
        String auth = "Basic "+Base64.encodeToString(bytes,Base64.NO_WRAP);


        Call<MpesaToken> call = retrofit.create(MpesaService.class).getToken(auth);
        call.enqueue(new Callback<MpesaToken>() {
            @Override
            public void onResponse(Call<MpesaToken> call, Response<MpesaToken> response) {
                Log.d("Mpesa",response.body().access_token);
                performStk(response.body().access_token);
            }

            @Override
            public void onFailure(Call<MpesaToken> call, Throwable t) {
                Log.e("Mpesa",t.getMessage());
            }
        });




    }


    public void performStk(String token){

        StkRequest stkRequest = new StkRequest("254729893875","LOAN38",(float)1);

        String auth = "Bearer "+token;
        Call<JSONObject> call = retrofit.create(MpesaService.class).performStk(auth,stkRequest);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });


    }
}
