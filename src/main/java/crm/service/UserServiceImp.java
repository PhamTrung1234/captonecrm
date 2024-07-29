package crm.service;

import java.util.List;


import crm.dto.UserTaskDTO;
import crm.entity.UserDetail;
import crm.entity.UserEntity;

public interface UserServiceImp {
	 boolean addUser(UserEntity user);
     List<UserEntity> getListUser();
     boolean deleteUserById(int id);
     UserEntity getUserById(int id);
     boolean updateUserById(UserEntity user);
     UserDetail getUserDetailById(int id);
     
	 List<UserTaskDTO> checkStatus(UserDetail users, int found);
	 boolean checkEmail(List<UserEntity> listUsers,String email);
}
