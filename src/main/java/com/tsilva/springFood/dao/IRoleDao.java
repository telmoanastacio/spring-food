package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Role;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public interface IRoleDao
{
	public Role findRoleById(Long roleId);

	public Role findRoleByName(String roleName);
}