package ke.co.neverest.mpesastk.responses;

import com.google.gson.annotations.SerializedName;

public class MpesaToken {

    @SerializedName("access_token")
    public String access_token;

    @SerializedName("expires_in")
    public String expires_in;
}
