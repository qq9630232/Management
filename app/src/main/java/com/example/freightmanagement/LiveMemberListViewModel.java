package com.example.freightmanagement;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;

import com.example.freightmanagement.common.EmClientRepository;
import com.example.freightmanagement.common.Resource;

import java.util.List;

public class LiveMemberListViewModel extends AndroidViewModel {
    private EmClientRepository repository;
    private MediatorLiveData<Resource<List<String>>> membersObservable;

    public LiveMemberListViewModel(@NonNull Application application) {
        super(application);
        repository = new EmClientRepository();
        membersObservable = new MediatorLiveData<>();
    }

    public MediatorLiveData<Resource<List<String>>> getMembersObservable() {
        return membersObservable;
    }

    public void getMembers(String chatRoomId) {
        membersObservable.addSource(repository.getOnlyMembers(chatRoomId), response -> membersObservable.postValue(response));
    }
}
