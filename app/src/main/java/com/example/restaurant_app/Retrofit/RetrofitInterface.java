package com.example.restaurant_app.Retrofit;

import com.example.restaurant_app.ForgotResult;
import com.example.restaurant_app.LoginResult;
import com.example.restaurant_app.Menu;
import com.example.restaurant_app.modelmanager.AllRegister.Bodyregister;
import com.example.restaurant_app.modelmanager.AllRegister.cook;
import com.example.restaurant_app.modelmanager.Availableitem.Availableitem;
import com.example.restaurant_app.modelmanager.TableWiseOrder.Tablewiseorder;
import com.example.restaurant_app.modelmanager.TableWiseOrderItem.TablewiseorderItem;
import com.example.restaurant_app.modelmanager.booktable.BookTable;
import com.example.restaurant_app.modelmanager.categoriesitem.Categoryitem;
import com.example.restaurant_app.modelmanager.cookdetails.Bodycook;
import com.example.restaurant_app.modelmanager.cookdetails.Cookdetails;
import com.example.restaurant_app.modelmanager.createIngrediants.Createingrediants;
import com.example.restaurant_app.modelmanager.delete.Deletecook;
import com.example.restaurant_app.modelmanager.feedback.Avaragerating;
import com.example.restaurant_app.modelmanager.feedback.Feedbackdetails;
import com.example.restaurant_app.modelmanager.getOrderItem.OrderItem;
import com.example.restaurant_app.modelmanager.getingrediants.Getingredients;
import com.example.restaurant_app.modelmanager.getmenu.Menudetails;
import com.example.restaurant_app.modelmanager.gettingorder.Orderdetails;
import com.example.restaurant_app.modelmanager.managecomplain.Getcomplate;
import com.example.restaurant_app.modelmanager.managerdetails.Bodymanager;
import com.example.restaurant_app.modelmanager.managerdetails.ManagerDetails;
import com.example.restaurant_app.modelmanager.parsal.Parsalorder;
import com.example.restaurant_app.modelmanager.replaycomplain.BodyreplayComplain;
import com.example.restaurant_app.modelmanager.replaycomplain.Replaycomplain;
import com.example.restaurant_app.modelmanager.setdiscount.BodySetDiscount;
import com.example.restaurant_app.modelmanager.setdiscount.Setdiscount;
import com.example.restaurant_app.modelmanager.showCategories.ShowCategories;
import com.example.restaurant_app.modelmanager.showrevenuemodel.Bodyshowrevenue;
import com.example.restaurant_app.modelmanager.showrevenuemodel.Showrevenue;
import com.example.restaurant_app.modelmanager.showrevenuemodel.Sumrevenue;
import com.example.restaurant_app.modelmanager.waiterdetails.Bodywaiter;
import com.example.restaurant_app.modelmanager.waiterdetails.Waiterdetails;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    //Admin Login
    @POST("/admin/login")
    Call<LoginResult> executeAdminLogin(@Body HashMap<String, String> map);

    //User register & login
    @PUT("/auth/register")
    Call<Void> executeRegister(@Body HashMap<String, String> map);

    @POST("/auth/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    //Waiter register & login
    @PUT("/all/register")
    Call<cook> executeWaiterRegister(@Body Bodyregister bodyregister);

    @POST("/waiter/login")
    Call<LoginResult> executeWaiterLogin(@Body HashMap<String, String> map);

    //Cook register
    @PUT("/all/register")
    Call<cook> executeCookRegister(@Body Bodyregister bodyregister);

    //cook login
    @POST("/cook/login")
    Call<LoginResult> executeCookLogin(@Body HashMap<String, String> map);

    //Manager register
    @PUT("/manage/addmanager")
    Call<Void> executeAddManagerRegister(@Body HashMap<String, String> map);

    //manager login
    @POST("/manage/login")
    Call<LoginResult> executeManagerLogin(@Body HashMap<String, String> map);

    //User Forgot password
    @POST("/auth/forgot")
    Call<ForgotResult> executeforgotpass(@Body HashMap<String, String> map);

    //Menu
    @GET("/feed/menu")
    Call<Menu> executeMenu(@Body HashMap<String, String> map);

    //view cook
    @POST("/all/get")
    Call<Cookdetails> Getcook(@Body Bodycook bodycook);

    //deletecook
    @DELETE("/all/delete/{path}")
    Call<Deletecook> cookdelete(@Path(value = "path") String path);

    //view waiter
    @POST("/all/get")
    Call<Waiterdetails> Getwaiter(@Body Bodywaiter bodywaiter);

    //view manager
    @POST("/all/get")
    Call<ManagerDetails> Getmanager(@Body Bodymanager bodymanager);

    //view order
    @GET("/order/getorders")
    Call<Orderdetails> Getorder();

    //view feedback
    @GET("/feedback/feedbacks")
    Call<Feedbackdetails> Getfeedback();

    //view avaragerating
    @GET("/feedback/average")
    Call<Avaragerating> Getavaragerating();

    //show menu
    @GET("/feed/getposts")
    Call<Menudetails> Getshowmenu();

    //item available
    @POST("/feed/getmenu")
    Call<Availableitem> GetitemAvailable();

    //show staff details
//    @GET("/all/geteveryone")
//    Call<Staffdetails> Getshowstaffdetails();

    //show complate
    @GET("/complaint/complaints")
    Call<Getcomplate> GetComplate();

    //view ingrediants
    @GET("/ingredient/getingredients")
    Call<Getingredients> Getingrediants();

    //Get Ingrediants
    @POST("/ingredient/addIngredient")
    Call<Createingrediants> AddIngrediants(@Body com.example.restaurant_app.modelmanager.createIngrediants.Body body);

    //view totalrevenue
    @GET("/revenue/sum")
    Call<Sumrevenue> GetTotalRevenue();

    //view showrevenue
    @POST("/revenue/dates")
    Call<Showrevenue> showRevenue(@Body Bodyshowrevenue bodyshowrevenue);

    //view order item
    @GET("/order/getorder/{path}")
    Call<OrderItem> ViewItem(@Path(value = "path") String path);

    //booktable
    @GET("/table/tables")
    Call<BookTable> showtable();

    //View TableWiseOrder
    @GET("/table/orderlist/{path1}")
    Call<Tablewiseorder> TableOrder(@Path(value = "path1") String path);

    //View TableWiseItem
    @GET("/order/getorder/{path1}")
    Call<TablewiseorderItem> tableitem(@Path(value = "path1") String path);

    //show Categories
    @GET("/category/categories")
    Call<ShowCategories> showCategories();

    //show CategoryItem
    @GET("/feed/menu/{path1}")
    Call<Categoryitem> showCategoryItem(@Path(value = "path1") String path);

    //View Parsal
    @GET("/order/parcelorders")
    Call<Parsalorder> Showparsal();

    //parsal item
    @GET("/order/getorder/{path}")
    Call<OrderItem> parsalitem(@Path(value = "path") String path);

    //replay complain
    @POST("/reply/reply/{path1}")
    Call<Replaycomplain> GetReplay(@Path(value = "path1") String path,
                                   @Body BodyreplayComplain bodyreplayComplain);

    //set Discount
    @PUT("/order/setdiscount/{path1}")
    Call<Setdiscount> SetDiscount(@Path(value = "path1") String path,
                                  @Body BodySetDiscount bodysetdiscount);


}
