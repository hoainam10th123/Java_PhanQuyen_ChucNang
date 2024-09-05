package com.hoainam10th.phanquyendatabase.services;

import com.hoainam10th.phanquyendatabase.dtos.RoleDto;
import com.hoainam10th.phanquyendatabase.entities.Permission;
import com.hoainam10th.phanquyendatabase.entities.Role;
import com.hoainam10th.phanquyendatabase.repositories.PermissionRepository;
import com.hoainam10th.phanquyendatabase.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper;

    public RoleDto addPermissionToRole(String roleName, String permissionQuery){ // ADMIN,USER
        Role role = roleRepository.findByName(roleName);
        if(role != null){
            List<String> selectedPermissions = Arrays.stream(permissionQuery.split(",")).toList();

            List<String> availablePermission = role.getPermissions().stream().map(Permission::getName).toList();


            List<String> resultL1 = new ArrayList<>(selectedPermissions);
            resultL1.removeAll(availablePermission);

            // them permission chua co
            for(String permission : resultL1){
                Optional<Permission> perDb = permissionRepository.findByName(permission);
                perDb.ifPresent(value -> role.getPermissions().add(value));
            }
            Role returnRole = roleRepository.save(role);

            //xoa nhung permission co roi
            List<String> resultL2 = new ArrayList<>(availablePermission);
            resultL2.removeAll(selectedPermissions);

            for(String permission: resultL2){
                Optional<Permission> perDb = permissionRepository.findByName(permission);
                perDb.ifPresent(value -> returnRole.getPermissions().remove(value));
            }
            Role role1 = roleRepository.save(returnRole);
            return modelMapper.map(role1, RoleDto.class);
        }
        return null;
    }

}
