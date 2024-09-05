package com.hoainam10th.phanquyendatabase.security;

import com.hoainam10th.phanquyendatabase.dtos.PermissionDto;
import com.hoainam10th.phanquyendatabase.dtos.RoleDto;
import com.hoainam10th.phanquyendatabase.dtos.UserDto;
import com.hoainam10th.phanquyendatabase.entities.Role;
import com.hoainam10th.phanquyendatabase.entities.User;
import com.hoainam10th.phanquyendatabase.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequiredPermission {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public boolean checkPermission(String permission){
        Optional<User> user = userService.getCurrentUser();
        boolean success = false;

        if(user.isPresent()){
            List<RoleDto> roles = user.get().getRoles().stream().map(role -> modelMapper.map(role, RoleDto.class)).toList();
            for(var role: roles){
                var permissions = role.getPermissions().stream().toList();

                for(var mPermission: permissions){
                    if (Objects.equals(mPermission.getName(), permission)) {
                        success = true;
                        break;
                    }
                }
                if(success) break;
            }
        }
        return success;
    }

}
