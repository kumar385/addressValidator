import org.springframework.cloud.contract.spec.Contract;

Contract.make{
    request{
        method 'POST'
        url '/api/validateAddress'
        headers{
            header('Content-Type','application/json')
        }
        body([
                "addresssLine1":"String",
                "city":"String",
                "zipCode":"String",
                "state":"String"
        ])
    }
    response{
        status 200
        body([[]])

    }
}