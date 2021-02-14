package com.example.nirog.data.api;

import com.example.nirog.data.model.Babydata;
import com.example.nirog.data.model.Login;
import com.example.nirog.data.model.NEWSIGNUP;
import com.example.nirog.data.model.RespomseBabyData;
import com.example.nirog.data.model.ResponseDocDetails;
import com.example.nirog.data.model.ResponseDoctor;
import com.example.nirog.data.model.ResponseGet_user;
import com.example.nirog.data.model.ResponseHosDetails;
import com.example.nirog.data.model.ResponseHospital;
import com.example.nirog.data.model.ResponseLogin;
import com.example.nirog.data.model.ResponseVaccine;
import com.example.nirog.data.model.ResponseVaccineDetails;
import com.example.nirog.data.model.VTaken;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/hospital/all")
    Call<ResponseHosDetails> GetAllHospitals();

    @GET("api/hospital/{hospitalId}")
    Call<ResponseHospital> GetHospital(@Path("hospitalId") String id);

    @GET("api/doctor/{doctorId}")
    Call<ResponseDoctor> GetDoctor(@Path("doctorId") String doctorId);

    @GET("api/doctor/hospitalwise/{hospitalId}")
    Call<ResponseDocDetails> GetAllDoctors(@Path("hospitalId") String hospitalId);

    @GET("api/vaccine/{vaccineId}")
    Call<ResponseVaccine> GetVaccine(@Path("vaccineId")String vaccineId);

    @GET("api/vaccine")
    Call<ResponseVaccineDetails> GetAllVaccines();

    @POST("api/user/signup")
    Call<ResponseLogin> signUp_User(@Body NEWSIGNUP signup);

    @POST("api/user/login")
    Call<ResponseLogin> Login_user(@Body Login login);

    @GET("api/user/{userId}")
    Call<ResponseGet_user> GET_USER(@Path("userId") String id);

    @POST("api/user/baby/register/{userId}")
    Call<RespomseBabyData> Register_Baby(@Path("userId") String id,@Body Babydata bd);

    @GET("api/user/baby/details/{parentId}")
    Call<RespomseBabyData> RetrieveBabyData(@Path("parentId") String id);

    @POST("api/user/baby/taken/vaccine")
    Call<RespomseBabyData> AddVaccinesTaken(@Body VTaken vTaken);

    @POST("api/user/baby/remove/vaccine")
    Call<RespomseBabyData> RemoveVaccine(@Body VTaken vTaken);

    @GET("api/vaccine/hospitalwise/all")
    Call<ResponseVaccineDetails> GetAllVaccinesHosWise();

}