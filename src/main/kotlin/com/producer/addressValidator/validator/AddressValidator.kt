package com.producer.addressValidator.validator

import com.producer.addressValidator.data.Address
import com.producer.addressValidator.data.AddressError
import org.springframework.stereotype.Service

@Service
class AddressValidator {

    fun validate(address: Address):AddressError{

        if(address.addresssLine1.isEmpty() || address.city.isEmpty() || address.zipCode.isEmpty() || address.state.isEmpty()){
            return AddressError(false)
        }

        return AddressError(true)
    }
}
