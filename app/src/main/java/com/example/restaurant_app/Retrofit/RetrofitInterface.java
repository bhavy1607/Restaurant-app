package com.example.restaurant_app.Retrofit;

import com.example.restaurant_app.ForgotResult;
import com.example.restaurant_app.LoginResult;
import com.example.restaurant_app.Menu;
import com.example.restaurant_app.model.Avaragerating;
import com.example.restaurant_app.model.Cookdetails;
import com.example.restaurant_app.model.Feedbackdetails;
import com.example.restaurant_app.model.Menudetails;
import com.example.restaurant_app.model.Orderdetails;
import com.example.restaurant_app.model.Waiterdetails;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitInterface {

    //Admin Login
    @POST("/admin/login")
    Call<LoginResult> executeAdminLogin(@Body HashMap<String,String>map);

    //User register & login
    @PUT("/auth/register")
    Call<Void> executeRegister(@Body HashMap<String, String> map);

    @POST("/auth/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    //Waiter register & login
    @PUT("/waiter/addwaiter")
    Call<Void> executeWaiterRegister(@Body HashMap<String, String> map);

    @POST("/waiter/login")
    Call<LoginResult> executeWaiterLogin(@Body HashMap<String, String> map);

    //Cook register & login
    @PUT("/cook/addcook")
    Call<Void> executeCookRegister(@Body HashMap<String, String> map);

    @POST("/cook/login")
    Call<LoginResult> executeCookLogin(@Body HashMap<String,String> map);

    //Manager register & login
    @PUT("/manage/addmanager")
    Call<Void> executeAddManagerRegister(@Body HashMap<String,String>map);

    @POST("/manage/login")
    Call<LoginResult> executeManagerLogin(@Body HashMap<String,String>map);

    //User Forgot password
    @POST("/auth/forgot")
    Call<ForgotResult> executeforgotpass(@Body HashMap<String,String>map);

    //Menu
    @GET("/feed/menu")
    Call<Menu> executeMenu(@Body HashMap<String,String>map);

    //view cook
    @GET("/cook/getcooks")
    Call<Cookdetails> Getcook();

    //view waiter
    @GET("/waiter/getwaiters")
    Call<Waiterdetails> Getwaiter();

    //view order
    @GET("/order/getorders")
    Call<Orderdetails> Getorder();

    //view feedback
    @GET("/feedback/feedbacks")
    Call<Feedbackdetails> Getfeedback();

    //view avaragerating
    @GET("/feedback/average")
    Call<Avaragerating> Getavaragerating();

    @GET("/feed/getposts")
    Call<Menudetails> Getshowmenu();

}
