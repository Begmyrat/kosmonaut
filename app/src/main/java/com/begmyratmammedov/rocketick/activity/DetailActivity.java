package com.begmyratmammedov.rocketick.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.begmyratmammedov.rocketick.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {

    Bundle extras;
    String name="", detail="", date="", image="", flightNumber="", rocket="";
    TextView t_detail, t_name, t_date, t_rocket, t_flightNumber;
    ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        prepareMe();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void prepareMe() {

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));// set status background white

        extras = getIntent().getExtras();
        t_detail = findViewById(R.id.t_detail);
        t_name = findViewById(R.id.t_name);
        t_date = findViewById(R.id.t_fireDateInfo);
        t_rocket = findViewById(R.id.t_rocketInfo);
        t_flightNumber = findViewById(R.id.t_flightNumberInfo);
        imageView = findViewById(R.id.i_image);

        if(extras!=null){
            name = extras.getString("name");
            detail = extras.getString("detail");
            date = extras.getString("date");
            image = extras.getString("image");
            flightNumber = extras.getString("flightNumber");
            rocket = extras.getString("rocket");
        }

        if(detail!=null && detail.length()>0)
            t_detail.setText(detail);
        if(name!=null && name.length()>0)
            t_name.setText(name);
        if(date!=null && date.length()>0)
            t_date.setText(date);
        if(flightNumber!=null && flightNumber.length()>0)
            t_flightNumber.setText(flightNumber);
        if(rocket!=null && rocket.length()>0)
            t_rocket.setText(rocket);

        if(image!=null && image.length()>0)
            Glide
                    .with(this)
                    .load(image)
                    .into(imageView);



    }

    public void clickBack(View view) {
        finish();
    }
}