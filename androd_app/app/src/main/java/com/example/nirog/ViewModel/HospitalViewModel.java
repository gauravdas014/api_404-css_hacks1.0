package com.example.nirog.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.nirog.data.api.ApiHelper;
import com.example.nirog.data.model.ResponseDocDetails;
import com.example.nirog.data.model.ResponseDoctor;
import com.example.nirog.data.model.ResponseHosDetails;
import com.example.nirog.data.model.ResponseHospital;
import com.example.nirog.data.model.ResponseVaccine;
import com.example.nirog.data.model.ResponseVaccineDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalViewModel extends AndroidViewModel {


    private ApiHelper apiHelper;
    private MutableLiveData<ResponseHosDetails> hosDetailsRes;
    private MutableLiveData<ResponseHospital>  HospitalResponse;
    private MutableLiveData<ResponseDocDetails> docDetailsRes;
    private MutableLiveData<ResponseDoctor> doctorResponse;
    private MutableLiveData<ResponseVaccine> vaccineResponse;
    private MutableLiveData<ResponseVaccineDetails> vaccineDetailsRes;




    public HospitalViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        hosDetailsRes = new MutableLiveData<ResponseHosDetails>();
        HospitalResponse = new MutableLiveData<ResponseHospital>();
        doctorResponse = new MutableLiveData<ResponseDoctor>();
        docDetailsRes = new MutableLiveData<ResponseDocDetails>();
        vaccineResponse = new MutableLiveData<ResponseVaccine>();
        vaccineDetailsRes = new MutableLiveData<ResponseVaccineDetails>();
    }

    public MutableLiveData<ResponseHosDetails> getAllHosDetailsRes()
    {
        return hosDetailsRes;
    }

    public MutableLiveData<ResponseHospital> getHospitalResponse()
    {
        return HospitalResponse;
    }

    public MutableLiveData<ResponseDocDetails> getAllDoctorsRes()
    {
        return docDetailsRes;
    }
    public MutableLiveData<ResponseDoctor> getDoctorResponse()
    {
        return doctorResponse;
    }

    public MutableLiveData<ResponseVaccine> getVaccineResponse(){
        return vaccineResponse;
    }
    public MutableLiveData<ResponseVaccineDetails> getALLVaccinesRes()
    {
        return vaccineDetailsRes;
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
                if(response.code()<300) {
                    HospitalResponse.postValue(response.body());
                }else if(response.code()>400) {
                    HospitalResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseHospital> call, Throwable t) {
                HospitalResponse.postValue(null);
            }
        });
    }

    //doctor
    public void getAllDoctors(String hospitalId)
    {
        apiHelper.GetAllDoctors(hospitalId).enqueue(new Callback<ResponseDocDetails>() {
            @Override
            public void onResponse(Call<ResponseDocDetails> call, Response<ResponseDocDetails> response) {
                if(response.code()<300) {
                    docDetailsRes.postValue(response.body());
                } else if(response.code()>400) {
                    docDetailsRes.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseDocDetails> call, Throwable t) {
                docDetailsRes.postValue(null);
            }
        });
    }

    public void getDoctor(String doctorID)
    {
        apiHelper.GetDoctor(doctorID).enqueue(new Callback<ResponseDoctor>() {
            @Override
            public void onResponse(Call<ResponseDoctor> call, Response<ResponseDoctor> response) {
                if(response.code()<300) {
                    doctorResponse.postValue(response.body());
                }else if(response.code()>400) {
                    doctorResponse.postValue(null);
                }

            }

            @Override
            public void onFailure(Call<ResponseDoctor> call, Throwable t) {
                doctorResponse.postValue(null);

            }
        });
    }

    public void getVaccine(String vaccineId)
    {
        apiHelper.GetVaccine(vaccineId).enqueue(new Callback<ResponseVaccine>() {
            @Override
            public void onResponse(Call<ResponseVaccine> call, Response<ResponseVaccine> response) {
                if(response.code()<300) {
                    vaccineResponse.postValue(response.body());
                }else if(response.code()>400) {
                    vaccineResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseVaccine> call, Throwable t) {
                vaccineResponse.postValue(null);

            }
        });
    }

    public void getAllVaccines()
    {
        apiHelper.GetAllVaccines().enqueue(new Callback<ResponseVaccineDetails>() {
            @Override
            public void onResponse(Call<ResponseVaccineDetails> call, Response<ResponseVaccineDetails> response) {
                if(response.code()<300) {
                    vaccineDetailsRes.postValue(response.body());
                }else if(response.code()>400) {
                    vaccineDetailsRes.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseVaccineDetails> call, Throwable t) {
                vaccineDetailsRes.postValue(null);
            }
        });
    }

}
