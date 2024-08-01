package crm.service;

import crm.dto.LoginDTO;
import crm.repository.LoginRepository;

public class LoginService implements LoginServiceImp{
    LoginRepository login = new LoginRepository();
	@Override
	public LoginDTO checkLogIn(String email, String password) {
		return login.checkLogIn(email, password);
	}

}
