package com.example.nirog.data.api;

import com.example.nirog.data.model.ResponseHosDetails;
import com.example.nirog.data.model.ResponseHospital;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/hospital/all")
    Call<ResponseHosDetails> GetAllHospitals();

    @GET("api/hospital/{hospitalId}")
    Call<ResponseHospital> GetHospital(@Path("hospitalId") String id);
}
