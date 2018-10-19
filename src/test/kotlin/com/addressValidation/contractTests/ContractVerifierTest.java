package com.addressValidation.contractTests;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.producer.addressValidator.AddressValidatorApplication;
import com.producer.addressValidator.api.AddressValidationApi;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AddressValidatorApplication.class})
public class ContractVerifierTest {

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void initMocks() {
        RestAssuredMockMvc.standaloneSetup(new AddressValidationApi(null));
        MockitoAnnotations.initMocks(this);
        MockMvc mockmvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        RestAssuredMockMvc.mockMvc(mockmvc);
    }


    @Test
    public void validate_shouldReturnEmptyErrorListForValidAddress() throws Exception {
        // given:
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json")
                .body("{\"addresssLine1\":\"1234 abc\",\"city\":\"palo\",\"zipCode\":\"12345\",\"state\":\"AF\"}");

        // when:
        ResponseOptions response = given().spec(request)
                .post("/api/validateAddress");

        // then:
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.header("Content-Type")).isEqualTo("application/json;charset=UTF-8");
        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).field("['validAddress']").isEqualTo(true);
    }

}
