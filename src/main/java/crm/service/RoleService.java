package crm.service;

import java.util.List;

import crm.entity.RoleEntity;
import crm.repository.RoleRepository;


public class RoleService implements RoleServiceImp{
	RoleRepository roleRepository = new RoleRepository();
	@Override
	public boolean addRole(RoleEntity role) {
		int rSet = roleRepository.addRole(role);
		
		return rSet >0;
	}
	@Override
	public List<RoleEntity> listRoles() {
		return roleRepository.getListRoles();
	}
	@Override
	public boolean deleteRoleById(int id) {
		int result = roleRepository.deleteRoleById(id);
		return result >0 ;
	}
	@Override
	public boolean updateRole(RoleEntity roles) {
		return roleRepository.updateRole(roles) >0;
	}
	@Override
	public RoleEntity getRoleById(int id) {
		return roleRepository.getRoleById(id);
	}
}
