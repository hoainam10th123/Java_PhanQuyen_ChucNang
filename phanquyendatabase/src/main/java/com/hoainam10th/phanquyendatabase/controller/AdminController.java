package com.hoainam10th.phanquyendatabase.controller;

import com.hoainam10th.phanquyendatabase.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
    private RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("query")
    public ResponseEntity<Object> AddPermissionToRole(@RequestParam String roleName, @RequestParam String permission){
        return ResponseEntity.ok(roleService.addPermissionToRole(roleName, permission));
    }

}
