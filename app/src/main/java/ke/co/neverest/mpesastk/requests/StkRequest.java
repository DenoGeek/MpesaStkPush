package ke.co.neverest.mpesastk.requests;

import android.util.Base64;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StkRequest {

    private String passkey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";


    public StkRequest(String phoneNumber,String ref,Float amount){

        this.PartyA=phoneNumber;
        this.PhoneNumber=phoneNumber;
        this.BusinessShortCode="174379";
        this.AccountReference=ref;
        this.TransactionType="CustomerBuyGoodsOnline";
        this.TransactionDesc="Purchase of goods";
        this.PartyB=this.BusinessShortCode;
        this.Amount=amount;
        this.CallBackURL="http://google.com";


        generateTimestamp();

    }


    public void generateTimestamp(){

        this.Timestamp= new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        String passwordSalt = this.BusinessShortCode+passkey+this.Timestamp;
        this.Password = Base64.encodeToString(passwordSalt.getBytes(), Base64.NO_WRAP);

    }

    @SerializedName("BusinessShortCode")
    public String BusinessShortCode;

    @SerializedName("Password")
    public String Password;

    @SerializedName("Timestamp")
    public String Timestamp;

    @SerializedName("TransactionType")
    public String TransactionType;

    @SerializedName("Amount")
    public Float Amount;

    @SerializedName("PartyA")
    public String PartyA;

    @SerializedName("PartyB")
    public String PartyB;


    @SerializedName("PhoneNumber")
    public String PhoneNumber;

    @SerializedName("CallBackURL")
    public String CallBackURL;

    @SerializedName("AccountReference")
    public String AccountReference;

    @SerializedName("TransactionDesc")
    public String TransactionDesc;





}
