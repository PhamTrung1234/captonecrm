package crm.dto;

import java.time.LocalDate;

public record TaskDTO(int id,int idProject,String nameUser, String nameProject
		,String nameStatus,String nameTask
		,LocalDate startDate,
		LocalDate endDate) {
 
}
