package com.producer.addressValidator.validator

import com.producer.addressValidator.data.Address
import com.producer.addressValidator.data.AddressError
import org.springframework.stereotype.Service

@Service
class AddressValidator {

    fun validate(address: Address):List<AddressError>{
        address.let{

        }
        if(address.addresssLine1.isEmpty()){
            return listOf(AddressError(field = "addressline1",message = "addressline1 should not be empty"))
        }

        return emptyList()
    }
}
