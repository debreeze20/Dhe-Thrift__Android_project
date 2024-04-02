package com.example.dhethrift.API;

import com.example.dhethrift.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            @Field("nama") String nama,
            @Field("harga") int harga,
            @Field("kategori") String kategori,
            @Field("merk") String merk,
            @Field("stok") int stok,
            @Field("kondisi") String kondisi
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id") int id,
            @Field("nama") String nama,
            @Field("harga") int harga,
            @Field("kategori") String kategori,
            @Field("merk") String merk,
            @Field("stok") int stok,
            @Field("kondisi") String kondisi
    );
}
