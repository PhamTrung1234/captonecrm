package crm.service;

import java.util.ArrayList;
import java.util.List;

import crm.dto.ProjectDTO;
import crm.dto.ProjectTaskDTO;
import crm.entity.ProjectEntity;
import crm.repository.ProjectRepository;

public class ProjectService implements ProjectServiceImp{
    ProjectRepository pRepository = new ProjectRepository();
	@Override
	public boolean addProject(ProjectEntity projectEntity) {
		return pRepository.addProject(projectEntity) >0 ;
	}
	@Override
	public List<ProjectDTO> getListProject() {
		return pRepository.getListProject();
	}
	@Override
	public boolean deleteProjectById(int id) {
		return pRepository.deleteProjectById(id) >0 ;
	}
	@Override
	public ProjectDTO getProjectById(int id) {
		return pRepository.getProjectById(id);
	}
	@Override
	public boolean updateProject(ProjectEntity projectEntity) {
		return pRepository.updateProjectById(projectEntity)>0;
	}
	@Override
	public List<ProjectTaskDTO> getProjectDetailById(int id) {
		return pRepository.getProjectDetailById(id);
	}
	@Override
	public List<ProjectTaskDTO> checkStatus(List<ProjectTaskDTO> listTasks, int found) {
		List<ProjectTaskDTO> listTaskByStatus = new ArrayList<ProjectTaskDTO>();
		for (ProjectTaskDTO projectTaskDTO : listTasks) {
			if(found == 1) {
				if(projectTaskDTO.nameStatus().equalsIgnoreCase("chưa bắt đầu")) {
					listTaskByStatus.add(projectTaskDTO);
				}
			}else if (found == 2) {
				if(projectTaskDTO.nameStatus().equalsIgnoreCase("chưa hoàn thành")) {
					listTaskByStatus.add(projectTaskDTO);
				}
			}else if (found == 3) {
				if(projectTaskDTO.nameStatus().equalsIgnoreCase("đã hoàn thành")) {
					listTaskByStatus.add(projectTaskDTO);
				}
			}
		}
		return listTaskByStatus;
	}
	@Override
	public List<Integer> getProjectByIdLeader(int id) {
		return pRepository.getProjectByIdLeader(id);
	}

}
