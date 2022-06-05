package com.cloudservices.mss.users.control.service.impl;

import com.cloudservices.mss.users.control.api.request.NewRoleRequest;
import com.cloudservices.mss.users.control.dto.RoleDto;
import com.cloudservices.mss.users.control.entity.Role;
import com.cloudservices.mss.users.control.repository.RoleRepository;
import com.cloudservices.mss.users.control.service.RoleService;
import com.cloudservices.mss.users.control.utility.UtilityHelper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final transient RoleRepository roleRepository;
    private final transient ModelMapper modelMapper;
    private final transient UtilityHelper utilityHelper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper, UtilityHelper utilityHelper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.utilityHelper = utilityHelper;
    }


    @Override
    public List<RoleDto> getRoles() {
        final List<Role> roleList = roleRepository.findAll();
        return roleList.stream()
                .map(role -> utilityHelper.mapToResource(role,RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto createNewRole(NewRoleRequest request) {
        log.info("Creating new Role: {}",request);
        final Role role = modelMapper.map(request, Role.class);
        final Role newRole = roleRepository.save(role);
        return mapRoleToResource(newRole);
    }

    private RoleDto mapRoleToResource(final Role role){
        return modelMapper.map(role, RoleDto.class);
    }
}
