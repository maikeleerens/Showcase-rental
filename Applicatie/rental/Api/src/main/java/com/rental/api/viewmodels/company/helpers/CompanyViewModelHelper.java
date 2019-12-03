package com.rental.api.viewmodels.company.helpers;

import com.rental.api.viewmodels.company.CompanyViewModel;
import com.rental.domain.interfaces.entities.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyViewModelHelper {
    public static List<CompanyViewModel> toCompanyViewModels(List<? extends Company> companies) {
        List<CompanyViewModel> returnCompanyList = new ArrayList<>();

        for (var company:
             companies) {
            returnCompanyList.add(new CompanyViewModel(company));
        }
        return returnCompanyList;
    }
}
