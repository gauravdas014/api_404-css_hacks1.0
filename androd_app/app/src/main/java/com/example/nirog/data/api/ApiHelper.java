package com.example.nirog.data.api;

import android.content.Context;

import com.example.nirog.Retrofit.RetrofitProvider;
import com.example.nirog.data.model.ResponseDocDetails;
import com.example.nirog.data.model.ResponseDoctor;
import com.example.nirog.data.model.ResponseHosDetails;
import com.example.nirog.data.model.ResponseHospital;

import retrofit2.Call;

public class ApiHelper implements ApiService{

    private static ApiHelper instance;
    private ApiService api;
    public ApiHelper(Context context) {
        api = RetrofitProvider.getInstance(context).create(ApiService.class);
    }

    public static ApiHelper getInstance(Context context){
        if(instance == null){
            synchronized (ApiHelper.class){
                if(instance == null){
                    instance = new ApiHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public Call<ResponseHosDetails> GetAllHospitals() {
        return api.GetAllHospitals();
    }

    @Override
    public Call<ResponseHospital> GetHospital(String id) {
        return api.GetHospital(id);
    }

    @Override
    public Call<ResponseDoctor> GetDoctor(String doctorId) {
        return GetDoctor(doctorId);
    }

    @Override
    public Call<ResponseDocDetails> GetAllDoctors(String hospitalId) {
        return GetAllDoctors(hospitalId);
    }
}
