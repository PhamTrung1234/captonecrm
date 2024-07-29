package crm.dto;

import java.time.LocalDate;

public record ProjectTaskDTO(String nameUser,String nameTask,String nameStatus
		,LocalDate startDate,LocalDate endDate) {

}
