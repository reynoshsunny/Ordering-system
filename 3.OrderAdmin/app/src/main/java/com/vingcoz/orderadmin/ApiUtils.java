package com.vingcoz.orderadmin;



import com.vingcoz.orderadmin.model.Customers;
import com.vingcoz.orderadmin.model.PinCodes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  ApiUtils {

    String MainUrl = "http://vingcoz.com/app/orderdb/api/v1/index.php/ApiAdmins/";
    String CheckAdminPhone = "IsAdminSignUp";
    String CheckLogin ="AdminLogin";
    String CheckAdminPasswordUpdate = "AdminUpdatePassword";
    String CheckAdminView = "Admin_customer_view";

    @GET("GetCustomersList")
    Call<List<Customers>> getUsers();

    @GET("GetPinCodeList")
    Call<List<PinCodes>> getPinCodes();

}
