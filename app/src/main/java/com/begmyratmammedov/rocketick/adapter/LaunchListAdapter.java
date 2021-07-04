package com.begmyratmammedov.rocketick.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.begmyratmammedov.rocketick.R;
import com.begmyratmammedov.rocketick.model.LaunchModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class LaunchListAdapter extends RecyclerView.Adapter<LaunchListAdapter.MyViewHolder> {

    private Context context;
    private List<LaunchModel> list;
    private ItemClickListener itemClickListener;

    public LaunchListAdapter(Context context, List<LaunchModel> list, ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener = itemClickListener;
    }

    public List<LaunchModel> getList() {
        return list;
    }

    public void setList(List<LaunchModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LaunchListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_launch, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LaunchListAdapter.MyViewHolder holder, int position) {
        holder.t_name.setText(list.get(position).getName());
        holder.t_id.setText("ID: "+list.get(position).getId());
        if(list.get(position).getStatic_fire_date_utc()!=null && list.get(position).getStatic_fire_date_utc().length()>9)
            holder.t_year.setText("Tarih: "+list.get(position).getStatic_fire_date_utc().substring(0, 10));
        else
            holder.t_year.setText("Tarih belirtilmemiş.");

        Glide
                .with(context)
                .load(list.get(position).getLinks().getPatch().getSmall())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.i_launch);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onLaunchClick(list.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        if(list==null)
            return 0;
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView i_launch;
        TextView t_name, t_id, t_year;

        public MyViewHolder(View itemView) {
            super(itemView);

            i_launch = itemView.findViewById(R.id.i_launch);
            t_name = itemView.findViewById(R.id.t_name);
            t_id = itemView.findViewById(R.id.t_id);
            t_year = itemView.findViewById(R.id.t_year);

        }
    }

    public interface ItemClickListener{
        public void onLaunchClick(LaunchModel launchModel);
    }
}
