package com.cloudservices.mss.users.control.api;

import com.cloudservices.mss.users.control.api.request.NewRoleRequest;
import com.cloudservices.mss.users.control.dto.RoleDto;
import com.cloudservices.mss.users.control.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleApi {

    private final transient RoleService roleService;

    public RoleApi(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDto> createNewRole(@RequestBody @Valid
                                                 final NewRoleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createNewRole(request));
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getRoles(){
        return ResponseEntity.ok(roleService.getRoles());
    }
}
