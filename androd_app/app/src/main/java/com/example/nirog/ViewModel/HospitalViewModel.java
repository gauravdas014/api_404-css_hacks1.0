package com.example.nirog.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.nirog.data.api.ApiHelper;
import com.example.nirog.data.model.ResponseHosDetails;
import com.example.nirog.data.model.ResponseHospital;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalViewModel extends AndroidViewModel {


    private ApiHelper apiHelper;
    private MutableLiveData<ResponseHosDetails> hosDetailsRes;
    private MutableLiveData<ResponseHospital>  HospitalResponse;




    public HospitalViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        hosDetailsRes = new MutableLiveData<ResponseHosDetails>();
        HospitalResponse = new MutableLiveData<ResponseHospital>();
    }

    public MutableLiveData<ResponseHosDetails> getAllHosDetailsRes()
    {
        return hosDetailsRes;
    }

    public MutableLiveData<ResponseHospital> getHospitalResponse()
    {
        return HospitalResponse;
    }


    public void getAllHospitals()
    {
        apiHelper.GetAllHospitals().enqueue(new Callback<ResponseHosDetails>() {
            @Override
            public void onResponse(Call<ResponseHosDetails> call, Response<ResponseHosDetails> response) {

                if(response.code()<300){
                    hosDetailsRes.postValue(response.body());
                }else if(response.code()>400){
                    hosDetailsRes.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseHosDetails> call, Throwable t) {
                hosDetailsRes.postValue(null);
            }
        });
    }

    public void getHospital(String id)
    {
        apiHelper.GetHospital(id).enqueue(new Callback<ResponseHospital>() {
            @Override
            public void onResponse(Call<ResponseHospital> call, Response<ResponseHospital> response) {
                if(response.code()<300)
                    HospitalResponse.postValue(response.body());
                else
                    HospitalResponse.postValue(null);
            }

            @Override
            public void onFailure(Call<ResponseHospital> call, Throwable t) {
                HospitalResponse.postValue(null);
            }
        });
    }
}
