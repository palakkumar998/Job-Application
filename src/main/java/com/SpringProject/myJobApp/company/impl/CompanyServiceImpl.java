package com.SpringProject.myJobApp.company.impl;

import com.SpringProject.myJobApp.company.Company;
import com.SpringProject.myJobApp.company.CompanyRepository;
import com.SpringProject.myJobApp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }



    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }




    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }



    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);

    }



    @Override
    public boolean deleteCompanyById(Long id) {

        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
//        try {
//            companyRepository.deleteById(id);
//            return true;
//        }
//        catch (Exception e){
//            return false;
//
//        }
    }

    @Override
    public boolean updateCompanyById(Long id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            company.setName(updateCompany.getName());
            company.setJobs(updateCompany.getJobs());
            companyRepository.save(company);

            return  true;
        }
        else {
            return false;
        }

    }
}
