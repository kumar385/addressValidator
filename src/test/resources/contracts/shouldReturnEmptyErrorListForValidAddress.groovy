import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    request {
        method 'POST'
        url '/api/validateAddress'
        headers {
            header('Content-Type', 'application/json')
        }
        body('''{
                "addresssLine1":"1234 abc",
                "city":"palo",
                "zipCode":"12345",
                "state":"AF"
                }
        ''')
    }
    response {
        status 200
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
        body('''
{
 "validAddress":true
}
''')

    }
}