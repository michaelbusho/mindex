package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    private String reportingIdUrl;

    @Autowired
    private ReportingStructureService reportingService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingIdUrl = "http://localhost:" + port + "/reporting/{id}";
    }

    @Test
    public void testReportingCounter() {
        String targetEmployeeID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        ReportingStructure report = restTemplate.getForEntity(reportingIdUrl, ReportingStructure.class, targetEmployeeID).getBody();
        assertEquals(4, report.getNumberOfReports());
    }

    @Test
    public void testReportingEmployee() {
        String employeeId = "c0c2293d-16bd-4603-8e08-638a9d18b22c";
        ReportingStructure report = restTemplate.getForEntity(reportingIdUrl, ReportingStructure.class, employeeId).getBody();

        //Create expected Employee
        Employee expectedEmployee = new Employee();
        expectedEmployee.setFirstName("George");
        expectedEmployee.setLastName("Harrison");
        expectedEmployee.setDepartment("Engineering");
        expectedEmployee.setPosition("Developer III");

        assertEmployeeEquivalence(expectedEmployee, report.getEmployee());
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
        assertEquals(expected.getDirectReports(), actual.getDirectReports());
    }
}
