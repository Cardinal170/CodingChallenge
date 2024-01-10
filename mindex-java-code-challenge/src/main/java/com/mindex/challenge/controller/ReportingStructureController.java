package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/reporting-structure/{employeeId}")
    public ReportingStructure getReportingStructure(@PathVariable String employeeId) {
        Employee employee = employeeService.read(employeeId);
        int numberOfReports = employeeService.calculateTotalReports(employeeId);

        return new ReportingStructure(employee, numberOfReports);
    }
}
