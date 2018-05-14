package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    private CompanyRepository companyRepository;

    @RequestMapping
    public List<Company> getCompanyList() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompanyById(@PathVariable("id") long id) {
        return companyRepository.findOne(id);
    }


    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
    public List<Employee> getEmployeesById(@PathVariable("id") long id) {
        return companyRepository.findOne(id).getEmployees();
    }

    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Company> getCompanyListByPage(@PathVariable("page") int page, @PathVariable("pageSize") int pagesize) {
        return companyRepository.findAll(new PageRequest(page, pagesize));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company addCompany(@RequestParam String companyName, @RequestParam Integer employeesNumber) {

        return companyRepository.save(new Company(companyName, employeesNumber));

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Company updateCompanyName(@PathVariable("id") long id, @RequestParam String newName) {
        companyRepository.updateCompanyName(id, newName);
        return companyRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteCompany(@PathVariable("id")long id){
        companyRepository.delete(id);
        return "success";
    }

}
