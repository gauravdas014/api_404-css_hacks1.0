package com.example.nirog.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.nirog.data.api.ApiHelper;
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
    private MutableLiveData<ResponseLogin> signUpResponse;
    private MutableLiveData<ResponseLogin> LoginResponse;
    private MutableLiveData<ResponseGet_user> get_userResponse;
    private MutableLiveData<RespomseBabyData> getbabyResponse;
    private MutableLiveData<RespomseBabyData> getBabyDataResponse;
    private MutableLiveData<RespomseBabyData> addResponse;
    private MutableLiveData<RespomseBabyData> removeResponse;



    public HospitalViewModel(@NonNull Application application) {
        super(application);
        apiHelper = new ApiHelper(application);
        hosDetailsRes = new MutableLiveData<ResponseHosDetails>();
        HospitalResponse = new MutableLiveData<ResponseHospital>();
        doctorResponse = new MutableLiveData<ResponseDoctor>();
        docDetailsRes = new MutableLiveData<ResponseDocDetails>();
        vaccineResponse = new MutableLiveData<ResponseVaccine>();
        vaccineDetailsRes = new MutableLiveData<ResponseVaccineDetails>();
        signUpResponse = new MutableLiveData<ResponseLogin>();
        LoginResponse = new MutableLiveData<ResponseLogin>();
        get_userResponse = new MutableLiveData<ResponseGet_user>();
        getbabyResponse = new MutableLiveData<RespomseBabyData>();
        getBabyDataResponse = new MutableLiveData<RespomseBabyData>();
        addResponse = new MutableLiveData<RespomseBabyData>();
        removeResponse = new MutableLiveData<RespomseBabyData>();
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

    public MutableLiveData<ResponseGet_user> Get_userResponse()
    {
        return  get_userResponse;
    }

    public MutableLiveData<ResponseLogin> getSignUpResponse()
    {
        return signUpResponse;
    }

    public MutableLiveData<ResponseLogin> getLoginResponse()
    {
        return LoginResponse;
    }
    public MutableLiveData<RespomseBabyData> getGetbabyResponse() { return getbabyResponse; }
    public MutableLiveData<RespomseBabyData> getGetBabyDataResponse() { return getBabyDataResponse; }

    public MutableLiveData<RespomseBabyData>  AddVaccinesTakenRes()
    {
        return  addResponse;
    }

    public MutableLiveData<RespomseBabyData> RemoveVaccinesRes()
    {
        return removeResponse;
    }


    public void getAllHospitals()
    {
        apiHelper.GetAllHospitals().enqueue(new Callback<ResponseHosDetails>() {
            @Override
            public void onResponse(Call<ResponseHosDetails> call, Response<ResponseHosDetails> response) {

                if(response.code()<300){
                    hosDetailsRes.postValue(response.body());
                    Log.d("Hospital", String.valueOf(response.code())+" : success");
                }else if(response.code()>400){
                    hosDetailsRes.postValue(null);
                    Log.d("Hospital", String.valueOf(response.code())+" : failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseHosDetails> call, Throwable t) {
                hosDetailsRes.postValue(null);
                Log.d("Hospital", String.valueOf(t)+" : failed");
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

    public void SignUp(NEWSIGNUP signup)
    {
        apiHelper.signUp_User(signup).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.code()<300) {
                    signUpResponse.postValue(response.body());
                    Log.i("Api:",""+response.code());
                }else if(response.code()>400) {
                    signUpResponse.postValue(null);
                    Log.i("Api:",""+response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                signUpResponse.postValue(null);

            }
        });
    }

    public void Login(Login login)
    {
        apiHelper.Login_user(login).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.code()<300) {
                    LoginResponse.postValue(response.body());
                    Log.i("Api:",""+response.code());
                }else if(response.code()>400) {
                    LoginResponse.postValue(null);
                    Log.i("Api:",""+response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                LoginResponse.postValue(null);
            }
        });
    }

    public void Get_User(String userId)
    {
        apiHelper.GET_USER(userId).enqueue(new Callback<ResponseGet_user>() {
            @Override
            public void onResponse(Call<ResponseGet_user> call, Response<ResponseGet_user> response) {
                if(response.code()<300) {
                    get_userResponse.postValue(response.body());
                }else if(response.code()>400) {
                    get_userResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseGet_user> call, Throwable t) {
                get_userResponse.postValue(null);

            }
        });
    }

    public  void Register_Baby_detail(String id, Babydata bd)
    {
        apiHelper.Register_Baby(id,bd).enqueue(new Callback<RespomseBabyData>() {
            @Override
            public void onResponse(Call<RespomseBabyData> call, Response<RespomseBabyData> response) {
                if(response.code()<300){
                    getbabyResponse.postValue(response.body());
                }
                else if(response.code()>400){
                    getbabyResponse.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<RespomseBabyData> call, Throwable t) {
                    getbabyResponse.postValue(null);
            }
        });
    }

    public void Get_Baby_Data(String id)
    {
        apiHelper.RetrieveBabyData(id).enqueue(new Callback<RespomseBabyData>() {
            @Override
            public void onResponse(Call<RespomseBabyData> call, Response<RespomseBabyData> response) {
                if(response.code()<300){
                    getbabyResponse.postValue(response.body());
                    Log.i("Api response: ",""+response.code());
                }
                else if(response.code()>400){
                    getbabyResponse.postValue(null);
                    Log.i("Api response: ",""+response.code());
                }
            }

            @Override
            public void onFailure(Call<RespomseBabyData> call, Throwable t) {
                getbabyResponse.postValue(null);
            }
        });
    }

    public void AddVaccinesTaken(VTaken vTaken)
    {
        apiHelper.AddVaccinesTaken(vTaken).enqueue(new Callback<RespomseBabyData>() {
            @Override
            public void onResponse(Call<RespomseBabyData> call, Response<RespomseBabyData> response) {
                if(response.code()<300){
                    addResponse.postValue(response.body());
                    Log.i("Api response: ",""+response.code());
                }
                else if(response.code()>400){
                    addResponse.postValue(null);
                    Log.i("Api response: ",""+response.code());
                }
            }

            @Override
            public void onFailure(Call<RespomseBabyData> call, Throwable t) {
                addResponse.postValue(null);

            }
        });

    }

    public void RemoveVaccines(VTaken vTaken)
    {
        apiHelper.RemoveVaccine(vTaken).enqueue(new Callback<RespomseBabyData>() {
            @Override
            public void onResponse(Call<RespomseBabyData> call, Response<RespomseBabyData> response) {
                if(response.code()<300){
                    removeResponse.postValue(response.body());
                    Log.i("Api response: ",""+response.code());
                }
                else if(response.code()>400){
                    removeResponse.postValue(null);
                    Log.i("Api response: ",""+response.code());
                }
            }

            @Override
            public void onFailure(Call<RespomseBabyData> call, Throwable t) {
                removeResponse.postValue(null);
            }
        });
    }


}
