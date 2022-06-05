package com.cloudservices.mss.users.control.service;

import com.cloudservices.mss.users.control.api.request.NewRoleRequest;
import com.cloudservices.mss.users.control.dto.RoleDto;
import com.cloudservices.mss.users.control.entity.Role;

import java.util.List;

public interface RoleService {

  List<RoleDto> getRoles();
  RoleDto createNewRole(NewRoleRequest request);
}
