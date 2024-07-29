package crm.service;

import java.util.ArrayList;
import java.util.List;

import crm.dto.UserTaskDTO;
import crm.entity.UserDetail;
import crm.entity.UserEntity;
import crm.repository.UserRepository;

public class UserService implements UserServiceImp{
	    UserRepository userRepository = new UserRepository();
		@Override
		public boolean addUser(UserEntity user) {
			int result = userRepository.addUser(user);
			return checkResult(result, "thêm user thành công");
		}
		@Override
		public List<UserEntity> getListUser() {
			return userRepository.getListUser();
		}
		@Override
		public boolean deleteUserById(int id) {
			int result = userRepository.deleteUserById(id);
			return checkResult(result, "xóa user thành công");
		}
		@Override
		public UserEntity getUserById(int id) {
			return userRepository.getUserById(id);
		}
		@Override
		public boolean updateUserById(UserEntity user) {
			int result = userRepository.updateUserById(user);
			
			return checkResult(result, "update user thành công");
		}
		public boolean checkResult(int result,String massage) {
			if(result >0) {
				System.out.println(massage);
				return true;
			}else {
				return false;
			}
		}
		@Override
		public UserDetail getUserDetailById(int id) {
			return userRepository.getUserDetailById(id);
		}
		@Override
		public List<UserTaskDTO> checkStatus(UserDetail users, int found) {
			List<UserTaskDTO> listTaskByStatus = new ArrayList<UserTaskDTO>();
			for (UserTaskDTO tasks : users.getListTasks()) {
				if(found == 1) {
					if(tasks.nameStatus().equalsIgnoreCase("chưa bắt đầu")) {
						listTaskByStatus.add(tasks);
					}
				}else if (found == 2) {
					if(tasks.nameStatus().equalsIgnoreCase("chưa hoàn thành")) {
						listTaskByStatus.add(tasks);
					}
				}else if (found == 3) {
					if(tasks.nameStatus().equalsIgnoreCase("đã hoàn thành")) {
						listTaskByStatus.add(tasks);
					}
				}
				
			}
			return listTaskByStatus;
		}
		@Override
		public boolean checkEmail(List<UserEntity> listUsers,String email) {
			boolean found = true;
			for (UserEntity userEntity : listUsers) {
				if(email.equalsIgnoreCase(userEntity.getEmail())) {
					found = false;
				}
			}
			return found;
		}
}
