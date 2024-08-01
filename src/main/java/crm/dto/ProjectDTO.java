package crm.dto;

import java.time.LocalDate;

import crm.entity.UserEntity;

public record ProjectDTO (int id, String name,LocalDate startTime,LocalDate endTime,UserEntity users){
        
}
