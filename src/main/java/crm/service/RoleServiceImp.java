package crm.service;

import java.util.List;

import crm.entity.RoleEntity;

public interface RoleServiceImp {
	 boolean addRole(RoleEntity role) ;
     List<RoleEntity> listRoles();
     boolean deleteRoleById(int id);
     boolean updateRole(RoleEntity roles);
     RoleEntity getRoleById(int id);
}
