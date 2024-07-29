package crm.dto;

import java.time.LocalDate;

public record TaskByIdDTO(int id,int idProject,int idStatus,int idUser,String name,LocalDate startDate
		,LocalDate endDate) {

}
