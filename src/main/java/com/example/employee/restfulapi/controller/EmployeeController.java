package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    //在此处完成Employee API
    @Autowired
    EmployeeRepository employeeRepository;
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable("id")long id){
        return employeeRepository.findOne(id);
    }
    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}",method = RequestMethod.GET)
    public Page<Employee> getEmployeesByPage(@PathVariable("page")int page,
                                           @PathVariable("pageSize")int pageSize){
        return employeeRepository.findAll(new PageRequest(page,pageSize));
    }
    @RequestMapping(value = "/male",method = RequestMethod.GET)
    public List<Employee> getEmployeesByGender(){
        return employeeRepository.findByGender("male");
    }
    @RequestMapping(method = RequestMethod.POST)
    public Employee addEmployee(@RequestParam String name,
                                @RequestParam Integer age,
                                @RequestParam String gender,
                                @RequestParam Integer salary,
                                @RequestParam Long companyId){
        return employeeRepository.save(new Employee(name,age,gender,salary,companyId));
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Employee updateEmployeeNameById(@PathVariable("id")long id, @RequestParam(required = false) String name){
        employeeRepository.updateNameById(id,name);
        return employeeRepository.findOne(id);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable(value = "id",required = false)Long id){
        employeeRepository.delete(id);
        return "success";
    }
}
