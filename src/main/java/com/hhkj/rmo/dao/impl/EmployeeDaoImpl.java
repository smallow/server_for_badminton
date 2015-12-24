package com.hhkj.rmo.dao.impl;

import com.hhkj.rmo.dao.EmployeeDao;
import com.hhkj.rmo.model.Employee;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by smallow on 2015/6/11.
 */
@Repository
public class EmployeeDaoImpl extends BaseDao<Employee> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }
}
