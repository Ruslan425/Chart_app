package ru.romazanov.app_7862.ui.startScreen;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.romazanov.app_7862.utils.SingleLiveEvent;
import ru.romazanov.app_7862.api.Api;
import ru.romazanov.app_7862.model.Point;
import ru.romazanov.app_7862.model.PointResponse;

@HiltViewModel
public class StartViewModel extends ViewModel {

    Api api;

    @Inject
    StartViewModel(Api api) {
        this.api = api;
    }

    private SingleLiveEvent<ArrayList<Point>> single;
        LiveData<ArrayList<Point>> getSingle() {
            if(single == null) {
                single = new SingleLiveEvent<>();
            }
            return single;
        };


    private SingleLiveEvent<String> error;
        LiveData<String> error() {
            if (error == null) {
                error = new SingleLiveEvent<>();
            }
            return error;
        }

    public void getPoint(int count) {
        api.getPoints(count).enqueue(new Callback<PointResponse>() {
            @Override
            public void onResponse(@NonNull Call<PointResponse> call, @NonNull Response<PointResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    response.body().getList().sort(new Comparator<Point>() {
                        @Override
                        public int compare(Point point, Point t1) {
                            return point.getX().compareTo(t1.getX());
                        }
                    });
                    single.postValue(response.body().getList());
                } else {
                    try {
                        assert response.errorBody() != null;
                        error.postValue(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<PointResponse> call, Throwable t) {
                error.postValue(t.getMessage());
            }
        });
    }


}