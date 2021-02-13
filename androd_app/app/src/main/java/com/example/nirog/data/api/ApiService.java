package com.example.nirog.data.api;

import com.example.nirog.data.model.ResponseDocDetails;
import com.example.nirog.data.model.ResponseDoctor;
import com.example.nirog.data.model.ResponseHosDetails;
import com.example.nirog.data.model.ResponseHospital;
import com.example.nirog.data.model.ResponseVaccine;
import com.example.nirog.data.model.ResponseVaccineDetails;

import retrofit2.Call;
import retrofit2.http.GET;
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
}