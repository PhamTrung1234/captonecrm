package crm.service;

import java.util.List;

import crm.dto.TaskByIdDTO;
import crm.dto.TaskDTO;
import crm.entity.TaskEntity;
import crm.repository.TaskRepository;

public class TaskService implements TaskServiceImp{
     TaskRepository taskRepository = new TaskRepository();
	@Override
	public boolean addTask(TaskEntity taskEntity) {
		return taskRepository.addTask(taskEntity)>0;
	}
	@Override
	public List<TaskDTO> getAllTask() {
		return taskRepository.getAllTask();
	}
	@Override
	public boolean deleteTaskById(int id) {
		return taskRepository.deleteTaskById(id)>0;
	}
	@Override
	public TaskByIdDTO getTaskById(int id) {
		return taskRepository.getTaskByID(id);
	}
	@Override
	public boolean updateTask(TaskEntity task) {
		return taskRepository.updateTask(task) >0;
	}

}
