package crm.service;

import java.util.List;

import crm.dto.TaskByIdDTO;
import crm.dto.TaskDTO;
import crm.entity.TaskEntity;

public interface TaskServiceImp {
    boolean addTask(TaskEntity taskEntity);
    List<TaskDTO> getAllTask();
    boolean deleteTaskById(int id);
    TaskByIdDTO getTaskById(int id);
    boolean updateTask(TaskEntity task);
}
