package com.example.nirog.AppPreferences;

public interface SharedPreferencesHelper {
    void AddBabyId(String id);
    void AddUserId(String id);

    String RetrieveBabyId();
    String RetrieveUserId();
}
