package com.begmyratmammedov.rocketick.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.begmyratmammedov.rocketick.R;
import com.begmyratmammedov.rocketick.adapter.LaunchListAdapter;
import com.begmyratmammedov.rocketick.model.LaunchModel;
import com.begmyratmammedov.rocketick.viewmodel.LaunchListViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

/*
Developed by Begmyrat Mammedov for PORTAL GROUP
 */

public class MainActivity extends AppCompatActivity implements LaunchListAdapter.ItemClickListener{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    LaunchListAdapter adapter;
    List<LaunchModel> launchModelList;
    LaunchListViewModel viewModel;
    Toolbar mTopToolbar;
    BottomSheetDialog bd;
    Spinner spinner;
    int currentYear = 0;
    String currentYearString = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareMe();
    }

    private void prepareMe() {

        mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LaunchListAdapter(this, launchModelList, this);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(LaunchListViewModel.class);
        viewModel.makeApiCall();
        viewModel.getLaunchList().observe(this, new Observer<List<LaunchModel>>() {
            @Override
            public void onChanged(List<LaunchModel> launchModels) {

                launchModelList = launchModels;

                if(launchModels!=null){
                    adapter.setList(launchModels);
                    if(launchModelList.size()==0)
                        Toast.makeText(MainActivity.this, "Bu seneye ait bilgi bulunamadı", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "nothing", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onLaunchClick(LaunchModel launchModel) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("detail", launchModel.getDetails());
        intent.putExtra("name", launchModel.getName());
        intent.putExtra("image", launchModel.getLinks().getPatch().getSmall());
        intent.putExtra("date", launchModel.getStatic_fire_date_utc());
        intent.putExtra("flightNumber", launchModel.getFlight_number());
        intent.putExtra("rocket", launchModel.getRocket());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_filter) {
            popupFilter();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void popupFilter(){
        bd = new BottomSheetDialog(this);
        View view;
        view = LayoutInflater.from(this).inflate(R.layout.popup_filter, null);
        spinner = (Spinner) view.findViewById(R.id.spinner_year);

        ArrayList<String> years = new ArrayList<>();
        years.add("All");
        for (int i = 1990; i <= 2021; i++) {
            years.add(""+i);
        }
        ArrayAdapter<String> adapterSpinner =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, years);
        adapterSpinner.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        currentYear = years.indexOf(currentYearString);
        spinner.setSelection(currentYear);

        bd.setContentView(view);
        bd.show();
    }


    public void clickFilterButton(View view) {
        bd.dismiss();
        currentYearString = spinner.getSelectedItem().toString();
        viewModel.showFilteredResult(currentYearString);
    }
}