package com.example.projetv2.ui.films;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FilmViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public FilmViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");



    }

    public LiveData<String> getText() {
        return mText;
    }
}