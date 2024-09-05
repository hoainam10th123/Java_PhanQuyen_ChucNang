package com.hoainam10th.phanquyendatabase.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPermissionDto {
    private String roleName; //ROLE_ADMIN
    private String queryRole;// ROLE_ADMIN,ROLE_USER
}
