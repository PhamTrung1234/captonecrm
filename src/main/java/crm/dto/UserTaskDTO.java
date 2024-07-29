package crm.dto;

import java.time.LocalDate;

public record UserTaskDTO(String nameStatus,String nameProject,
		String nameTask, LocalDate startDate ,LocalDate endDate) {

}
