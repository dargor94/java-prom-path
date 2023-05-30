package org.dargor.aop.controller;

import lombok.AllArgsConstructor;
import org.dargor.aop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {


    private final UserService service;

    @GetMapping("{q}")
    public ResponseEntity<List<Integer>> getUsersIds(@PathVariable int q) {
        return ResponseEntity.ok(service.getUsersIds(q));
    }

}
