package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mindex.challenge.service.EmployeeService;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceTest {

    private String compensationUrl;
    private String compensationEmployeeIdUrl;
    private String employeeUrl;
    private String employeeIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {

        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationEmployeeIdUrl = "http://localhost:" + port + "/compensation/{id}";
        
    }

    //For These Tests I basically copied and reworked what had been done in EmployeeServiceImplTest
    //I used the file as an outline, once again this is my first time creating Unit Tests of any kind
    @Test
    public void testCreateCompensation() {
        
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");

   
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(100000);
        testCompensation.setEffectiveDate(LocalDate.now());

        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();


        assertNotNull(createdCompensation);
    
    }

    @Test
    public void testReadCompensation() {
        ResponseEntity<Compensation> response = restTemplate.getForEntity(compensationEmployeeIdUrl, Compensation.class, "12345");
        Compensation readCompensation = response.getBody();

        assertNotNull(readCompensation);
    
    }

}
