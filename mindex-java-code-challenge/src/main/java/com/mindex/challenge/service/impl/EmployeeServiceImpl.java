package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
//Retruns the total number of unique indviduals underneath the employee
    @Override
    public int calculateTotalReports(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        Set<String> allReportIds = new HashSet<>();
        calculateTotalReportsRecursive(employee, allReportIds);

        return allReportIds.size();
    }

    private void calculateTotalReportsRecursive(Employee employee, Set<String> allReportIds) {
        if (employee.getDirectReports() != null) {
            for (Employee report : employee.getDirectReports()) {
             if(report.getEmployeeId() != null && !allReportIds.contains(report.getEmployeeId())){
                allReportIds.add(report.getEmployeeId());
                Employee fullReportDetails = employeeRepository.findByEmployeeId(report.getEmployeeId());

                if (fullReportDetails != null) {              
                    calculateTotalReportsRecursive(fullReportDetails, allReportIds);
                }
             }
            }
        }
    }
}