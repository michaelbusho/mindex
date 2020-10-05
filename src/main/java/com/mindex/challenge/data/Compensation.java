package com.mindex.challenge.data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Compensation {
    private String compensationId;
    private Employee employee;
    private BigDecimal salary;
    private Date effectiveDate;

    public Compensation(Employee employee, BigDecimal salary, Date effectiveDate ){
        this.compensationId = UUID.randomUUID().toString();
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getCompensationId() {
        return compensationId;
    }

    public void setCompensationId(String compensationId) {
        this.compensationId = compensationId;
    }
}
