package com.jmockit.userOperations;

/**
 * @author Ashish Mishra Feb 12, 2017
 */

public class LoginService {
	private LoginDao loginDao;
	private String currentUser;

	public boolean login(UserForm userForm) {
		
		assert null != userForm;
		
		int loginResults = loginDao.login(userForm);
		switch (loginResults) {
		case 1:
			return true;

		default:
			return false;
		}
		
	}

	public void setCurrentUser(String username){
		if(null != username){
            this.currentUser = username;
        }

	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
}