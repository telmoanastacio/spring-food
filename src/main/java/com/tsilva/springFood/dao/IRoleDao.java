package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Role;
import org.springframework.lang.Nullable;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public interface IRoleDao
{
	@Nullable
	public Role findRoleById(Long roleId);

	@Nullable
	public Role findRoleByName(String roleName);
}