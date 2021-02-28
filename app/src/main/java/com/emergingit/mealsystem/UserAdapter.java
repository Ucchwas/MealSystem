package com.emergingit.mealsystem;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.emergingit.mealsystem.Models.Users;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Data;

@Data
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<Users> usersList = new ArrayList<>();
    Context context;
    private int currentColor;


    public UserAdapter(Context ct){
        context = ct;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.uName.setText(usersList.get(position).getUname());
        holder.cardView.setCardBackgroundColor(getRandomColorCode());
        holder.uEmail.setText(usersList.get(position).getEmail());
    }



    public int getRandomColorCode(){
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256),     random.nextInt(256));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView uName,uEmail;
        CardView cardView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            uName = (TextView) itemView.findViewById(R.id.u_name);
            uEmail = (TextView) itemView.findViewById(R.id.u_email);
            cardView = (CardView) itemView.findViewById(R.id.cardViewUser);
        }
    }
}
