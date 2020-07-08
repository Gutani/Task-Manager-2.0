package com.magie.taskmanager20.helper;

import com.magie.taskmanager20.model.Tasks;

import java.util.List;

public interface IDAOTask {
    public boolean save(Tasks tasks);
    public boolean update(Tasks tasks);
    public boolean delete(Tasks tasks);
    public List<Tasks> list();
}
