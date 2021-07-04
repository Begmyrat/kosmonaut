package com.begmyratmammedov.rocketick.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.begmyratmammedov.rocketick.model.LaunchModel;
import com.begmyratmammedov.rocketick.network.APIService;
import com.begmyratmammedov.rocketick.network.RetroInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LaunchListViewModel extends ViewModel {

    private MutableLiveData<List<LaunchModel>> launchList;
    private List<LaunchModel> all;

    public LaunchListViewModel(){
        launchList = new MutableLiveData<>();
        all = new ArrayList<>();
    }

    public MutableLiveData<List<LaunchModel>> getLaunchList() {
        return launchList;
    }

    public void setLaunchList(MutableLiveData<List<LaunchModel>> launchList) {
        this.launchList = launchList;
    }

    public void makeApiCall(){
        APIService apiService = RetroInstance.getRetroClient()
                .create(APIService.class);

        Call<List<LaunchModel>> call = apiService.getLaunchList();

        call.enqueue(new Callback<List<LaunchModel>>() {
            @Override
            public void onResponse(Call<List<LaunchModel>> call, Response<List<LaunchModel>> response) {
                launchList.postValue(response.body());
                for(int i=0;i<response.body().size();i++){
                    all.add(response.body().get(i));
                }
            }

            @Override
            public void onFailure(Call<List<LaunchModel>> call, Throwable t) {
                launchList.postValue(null);
            }
        });
    }

    public void showFilteredResult(String year){
        List<LaunchModel> result = new ArrayList<>();
        for(int i=0;i<all.size(); i++){
            String x = all.get(i).getStatic_fire_date_utc();
            if(year.equals("All") || (x!=null && x.length()>4 && x.contains(year)))
                result.add(all.get(i));
        }
        launchList.postValue(result);
    }
}
