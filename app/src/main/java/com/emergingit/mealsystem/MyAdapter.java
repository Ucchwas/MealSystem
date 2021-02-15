package com.emergingit.mealsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emergingit.mealsystem.Models.Api.weeklyData;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<weeklyData> weeklyDataList = new ArrayList<>();
    Context context;

    public MyAdapter(Context ct){
        context = ct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.uname.setText(weeklyDataList.get(position).getUser().get(0).getUname());
        holder.totalMeal.setText(""+weeklyDataList.get(position).getTotalMeal());
        holder.totalPrice.setText(""+weeklyDataList.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return weeklyDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView uname,totalMeal,totalPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            uname =  (TextView) itemView.findViewById(R.id.uname);
            totalMeal = (TextView) itemView.findViewById(R.id.totalMeal);
            totalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
        }
    }
}
