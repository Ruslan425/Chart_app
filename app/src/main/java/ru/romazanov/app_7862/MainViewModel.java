package ru.romazanov.app_7862;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.romazanov.app_7862.api.Api;
import ru.romazanov.app_7862.model.Point;
import ru.romazanov.app_7862.model.PointResponse;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final Api api;

    @Inject
    MainViewModel(Api api) {
        this.api = api;
    }

    private final MutableLiveData<ArrayList<Point>> dataList = new MutableLiveData<>(new ArrayList<>());

    LiveData<ArrayList<Point>> getDataList() {
        return dataList;
    }

    public void test(Integer count) {
        api.getPoints(count).enqueue(new Callback<PointResponse>() {
            @Override
            public void onResponse(@NonNull Call<PointResponse> call, @NonNull Response<PointResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    dataList.postValue(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<PointResponse> call, Throwable t) {

            }
        });
    }
}
