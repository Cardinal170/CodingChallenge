package com.mindex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@RequestMapping("/compensation")
public class CompensationController {

    @Autowired
    private CompensationService compensationService;

    @PostMapping
    public ResponseEntity<Compensation> create(@RequestBody Compensation compensation){
        return new ResponseEntity<>(compensationService.create(compensation), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compensation> read(@PathVariable String id) {
        return new ResponseEntity<>(compensationService.read(id), HttpStatus.OK);
    }
}
