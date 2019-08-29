package com.xiupeilian.carpart.service;

import com.xiupeilian.carpart.model.Company;

public interface CompanyService {
    Company findCompanyByName(String companyName);
}
