package com.magie.taskmanager20.fragments;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.magie.taskmanager20.BuildConfig;
import com.magie.taskmanager20.R;
import com.magie.taskmanager20.adapter.TaskAdapter;
import com.magie.taskmanager20.helper.DAOTask;
import com.magie.taskmanager20.helper.DataBaseConfig;
import com.magie.taskmanager20.helper.RecyclerItemClickListener;
import com.magie.taskmanager20.model.Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FirstFragment extends Fragment {

    private List<Tasks> itemList = new ArrayList<>();
    private Tasks selectedTask;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        //RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.listTaksRecycler);
        //Adding Click Event
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //Trying to communicate with SecondFragment but didn't make it
/*
                Tasks selectedTask = itemList.get(position); //Get the position and the item selected
                SecondFragment fragment = new SecondFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", selectedTask);
                fragment.setArguments(bundle);
 */
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                selectedTask = itemList.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Sure you want to DELETE?");
                dialog.setMessage("You're deleting: " + selectedTask.getNameTask());
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DAOTask daoTask = new DAOTask(getContext());
                        daoTask.delete(selectedTask);
                    }
                });
                dialog.setNegativeButton("NO", null);
                dialog.create().show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayout.VERTICAL));

        DAOTask daoTask = new DAOTask(getContext());
        itemList = daoTask.list();
        //Adapter
        recyclerView.setAdapter(new TaskAdapter(itemList));
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}