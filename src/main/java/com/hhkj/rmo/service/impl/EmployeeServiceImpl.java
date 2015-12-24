package com.hhkj.rmo.service.impl;

import com.hhkj.rmo.dao.EmployeeDao;
import com.hhkj.rmo.model.Employee;
import com.hhkj.rmo.service.EmployeeService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/6/11.
 */
@Service
public class EmployeeServiceImpl extends BaseService<Employee> implements EmployeeService {


    private EmployeeDao employeeDao;
    @Resource
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
        this.dao=employeeDao;
    }
}
