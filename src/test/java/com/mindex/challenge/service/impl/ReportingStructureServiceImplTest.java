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
    private String targetEmployeeID;
    @Autowired
    private ReportingStructureService reportingService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingIdUrl = "http://localhost:" + port + "/reporting/{id}";
        targetEmployeeID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
    }

    @Test
    public void testReporting() {
        ReportingStructure report = restTemplate.getForEntity(reportingIdUrl, ReportingStructure.class, targetEmployeeID).getBody();
        assertEquals(4, report.getNumberOfReports());
    }
}
