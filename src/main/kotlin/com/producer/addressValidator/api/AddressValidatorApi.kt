package com.producer.addressValidator.api

import com.producer.addressValidator.data.Address
import com.producer.addressValidator.data.AddressError
import com.producer.addressValidator.validator.AddressValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class AddressValidationApi(@Autowired val addressValidator: AddressValidator?){

    @RequestMapping(method = [RequestMethod.POST],value= ["/validateAddress"],consumes = [MediaType.APPLICATION_JSON_VALUE],produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun addressValidator(@RequestBody address: Address):AddressError?{
        return addressValidator?.validate(address)
    }

}
