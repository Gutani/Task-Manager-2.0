package com.magie.taskmanager20.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magie.taskmanager20.R;
import com.magie.taskmanager20.model.Tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Tasks> listTasks;

    public TaskAdapter(List<Tasks> list) {
        this.listTasks = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewList = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tasks_adapter, parent, false);
        return new MyViewHolder(viewList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.taskView.setText(listTasks.get(position).getNameTask());
    }

    @Override
    public int getItemCount() {
        return this.listTasks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView taskView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            taskView = itemView.findViewById(R.id.textViewTasks);
        }
    }
}
