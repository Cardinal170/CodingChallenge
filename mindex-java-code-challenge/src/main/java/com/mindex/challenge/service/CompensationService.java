package com.mindex.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;

@Service
public class CompensationService {

    @Autowired
    private CompensationRepository compensationRepository;

    public Compensation create( Compensation compensation){
        return compensationRepository.save(compensation);
    }

    public Compensation read(String employeeId){
        return compensationRepository.findByEmployeeEmployeeId(employeeId);
    }
}
