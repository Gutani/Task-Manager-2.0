package com.magie.taskmanager20.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tasks implements Serializable {
    private Long id;
    private String nameTask;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}
