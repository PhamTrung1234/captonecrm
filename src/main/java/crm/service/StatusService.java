package crm.service;

import java.util.List;

import crm.dto.StatusDTO;
import crm.repository.StatusRepository;

public class StatusService {
	   StatusRepository statusRepository = new StatusRepository();
        public List<StatusDTO> getAllStatus() {
			    return statusRepository.getAllStatus();
		}
}
