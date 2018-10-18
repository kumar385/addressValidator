package com.producer.addressValidator.api

import com.producer.addressValidator.data.Address
import com.producer.addressValidator.data.AddressError
import com.producer.addressValidator.validator.AddressValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class AddressValidationApi(@Autowired val addressValidator: AddressValidator?){

    @PostMapping("/validateAddress")
    fun addressValidator(@RequestBody address: Address):List<AddressError>?{
        return addressValidator?.validate(address)
    }

    @GetMapping("/test")
    fun addressValidatortest(address: Address):String{
        return "this is success"
    }
}
