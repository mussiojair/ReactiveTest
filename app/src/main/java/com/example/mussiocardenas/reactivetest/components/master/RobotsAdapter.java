package com.example.mussiocardenas.reactivetest.components.master;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mussiocardenas.reactivetest.R;
import com.example.mussiocardenas.reactivetest.domain.Robot;

import java.util.ArrayList;
import java.util.List;

public class RobotsAdapter extends RecyclerView.Adapter<RobotsAdapter.RobotViewHolder> {

    private List<Robot> items;
    private Context context;
    private OnClickRobotsListener listener;

    RobotsAdapter(Context context, OnClickRobotsListener listener) {
        this.context = context;
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setItems(List<Robot> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RobotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new RobotViewHolder(LayoutInflater.from(context).inflate(R.layout.robot_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RobotViewHolder viewHolder, int position) {
        final Robot robot = items.get(position);
        viewHolder.name.setText(robot.name);
        viewHolder.itemView.setOnClickListener(view -> listener.onClick(robot));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class RobotViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        RobotViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }

    interface OnClickRobotsListener {
        void onClick(Robot robot);
    }
}
