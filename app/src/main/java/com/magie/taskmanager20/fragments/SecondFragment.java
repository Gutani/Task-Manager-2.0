package com.magie.taskmanager20.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import com.magie.taskmanager20.R;
import com.magie.taskmanager20.helper.DAOTask;
import com.magie.taskmanager20.model.Tasks;

import java.io.Serializable;
import java.util.Objects;

public class SecondFragment extends Fragment{

    private EditText editText;
    private Tasks returnStatement;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment

        return  inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);



        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_save){
           // NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            DAOTask daoTask = new DAOTask(getContext());
            Tasks tasks = new Tasks();
            String getTaskFromEditText = editText.getText().toString();
            if(!getTaskFromEditText.isEmpty()){
                tasks.setNameTask(getTaskFromEditText);
                daoTask.save(tasks);
                Toast.makeText(getContext(), "Sucess!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "You have added nothing on your task list", Toast.LENGTH_SHORT).show();
            }
            requireActivity().onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }


}