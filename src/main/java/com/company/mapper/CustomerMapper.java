package com.company.mapper;

import com.company.dto.request.CustomerReq;
import com.company.dto.response.CustomerResp;
import com.company.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer toCustomer(CustomerReq customerReq);

    CustomerResp toCustomerResp(Customer customer);
}
