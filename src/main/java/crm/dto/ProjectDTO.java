package crm.dto;

import java.time.LocalDate;

public record ProjectDTO (int id, String name,LocalDate startTime,LocalDate endTime){
        
}
