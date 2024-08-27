package com.SpringProject.myJobApp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")

public class CompanyController {

    public CompanyController(CompanyService companyService) {
     this.companyService = companyService;

    }

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity <List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
       companyService.createCompany(company);
        return new ResponseEntity<>("company added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);


        if (company != null) {

            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean isdeleted = companyService.deleteCompanyById(id);
        if (isdeleted){
            return  new ResponseEntity<>("Company Deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
        }

    }


    @PutMapping("/{id}")
    public  ResponseEntity<String> updateCompany(@PathVariable Long id,  @RequestBody Company updateCompany){
        boolean updated = companyService.updateCompanyById(id, updateCompany);
        if (updated){
            return new ResponseEntity<>("Company Updated",  HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }







}






