package com.hoainam10th.phanquyendatabase.services;

import com.hoainam10th.phanquyendatabase.entities.Permission;
import com.hoainam10th.phanquyendatabase.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permission add(Permission data){
        return permissionRepository.save(data);
    }
}
