package com.example.nirog.data.api;

import android.content.Context;

import com.example.nirog.Retrofit.RetrofitProvider;
import com.example.nirog.data.model.Login;
import com.example.nirog.data.model.NEWSIGNUP;
import com.example.nirog.data.model.ResponseDocDetails;
import com.example.nirog.data.model.ResponseDoctor;
import com.example.nirog.data.model.ResponseGet_user;
import com.example.nirog.data.model.ResponseHosDetails;
import com.example.nirog.data.model.ResponseHospital;
import com.example.nirog.data.model.ResponseLogin;
import com.example.nirog.data.model.ResponseVaccine;
import com.example.nirog.data.model.ResponseVaccineDetails;
import com.example.nirog.data.model.Signup;

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
        return api.GetDoctor(doctorId);
    }

    @Override
    public Call<ResponseDocDetails> GetAllDoctors(String hospitalId) {
        return api.GetAllDoctors(hospitalId);
    }

    @Override
    public Call<ResponseVaccine> GetVaccine(String vaccineId) {
        return api.GetVaccine(vaccineId);
    }

    @Override
    public Call<ResponseVaccineDetails> GetAllVaccines() {
        return api.GetAllVaccines();
    }

    @Override
    public Call<ResponseLogin> signUp_User(NEWSIGNUP signup) {
        return api.signUp_User(signup);
    }

    @Override
    public Call<ResponseLogin> Login_user(Login login) {
        return api.Login_user(login);
    }

    @Override
    public Call<ResponseGet_user> GET_USER(String id) {
        return api.GET_USER(id);
    }
}
