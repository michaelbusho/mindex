package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure generateReportingStructure(String id){
        LOG.debug("Creating report for employee with id [{}]", id);

        //Get target employee
        List<Employee> allEmployees = employeeRepository.findAll();
        Employee parent = findEmployee(id, allEmployees);

        //Instantiate report obj
        ReportingStructure report = new ReportingStructure();

        //Create hierarchy of employees & get number of reports
        addChildren(parent, allEmployees, report);
        report.setEmployee(parent);

        return report;
    }

    private void addChildren(Employee parent, List<Employee> all, ReportingStructure report) {
        if(parent != null && parent.getDirectReports() != null){
            List<Employee> children = new ArrayList<>();
            parent.getDirectReports().forEach(directEmployee -> {
                Employee childEmployee = findEmployee(directEmployee.getEmployeeId(), all);
                children.add(childEmployee);
                parent.setDirectReports(children);
                report.setNumberOfReports(report.getNumberOfReports()+1);
                addChildren(childEmployee, all, report);
            });
        }
    }

    private Employee findEmployee(String id, List<Employee> employees ){
        return employees.stream()
                .filter(employee -> id.equals(employee.getEmployeeId()))
                .findAny()
                .orElse(null);
    }
}
