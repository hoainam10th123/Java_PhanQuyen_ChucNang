package com.hoainam10th.phanquyendatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/todos")
@RestController
public class TodoController {


    @PreAuthorize("@requiredPermission.checkPermission('TODO_CREATED')")
    @PostMapping("{id}")
    public ResponseEntity<Object> addTodo(@PathVariable Long id){
        return new ResponseEntity<>("Add success "+ id, HttpStatus.CREATED);
    }

    @PreAuthorize("@requiredPermission.checkPermission('TODO_UPDATE')")
    @PutMapping("{id}")
    public ResponseEntity<Object> editTodo(@PathVariable Long id){
        return ResponseEntity.ok("Edit success "+ id);
    }

    @PreAuthorize("@requiredPermission.checkPermission('TODO_GET')")
    @GetMapping("{id}")
    public ResponseEntity<Object> getTodo(@PathVariable Long id){
        return ResponseEntity.ok("Get success "+ id);
    }

    @PreAuthorize("@requiredPermission.checkPermission('TODO_DELETE')")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delTodo(@PathVariable Long id){
        return ResponseEntity.ok("Get success "+ id);
    }
}
