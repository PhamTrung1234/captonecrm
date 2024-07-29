package crm.service;

import crm.repository.LoginRepository;

public class LoginService implements LoginServiceImp{
    LoginRepository login = new LoginRepository();
	@Override
	public boolean checkLogIn(String email, String password) {
		int id = login.checkLogIn(email, password);
		if (id>0 ) System.out.println("đăng nhập thành công");
		return id>0;
	}

}
