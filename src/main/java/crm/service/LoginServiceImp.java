package crm.service;

import crm.dto.LoginDTO;

public interface LoginServiceImp {
	 LoginDTO checkLogIn(String email,String password);
}
