package com.cnleon.controllers;

import com.cnleon.converters.SwimmerToSwimmerResponseConverter;
import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;
import com.cnleon.enumerates.Gender;
import com.cnleon.mappers.responses.SwimmerResponse;
import com.cnleon.services.SwimmerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * Unit test for the SwimmerController class.
 *
 * Created by anita on 9/07/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = SwimmerController.class, secure = false)
public class SwimmerControllerTest {

    private static final String DATE_PATTERN = "DD-MM-yyyy";

    private static final String SWIMMER_1_ID = "1";
    private static final String SWIMMER_1_FIRST_NAME = "Jane";
    private static final String SWIMMER_1_LAST_NAME = "Doe";
    private static final String SWIMMER_1_LICENCE = "000012222";
    private static final String SWIMMER_1_BIRTH_DATE = "29-08-1987";
    private static final String SWIMMER_1_BIRTH_PLACE = "Leon";
    private static final String SWIMMER_1_FULL_NAME = "Jane Doe";

    private static final String SWIMMER_2_ID = "2";
    private static final String SWIMMER_2_FIRST_NAME = "John";
    private static final String SWIMMER_2_LAST_NAME = "Doe";
    private static final String SWIMMER_2_LICENCE = "012345678";
    private static final String SWIMMER_2_BIRTH_DATE = "12-05-1995";
    private static final String SWIMMER_2_BIRTH_PLACE = "Madrid";
    private static final String SWIMMER_2_FULL_NAME = "John Doe";

    /**
     * Bean to mock the mvc operations done in the controller
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mock of the SwimmerService. Used to simulate calls to this service.
     */
    @MockBean
    private SwimmerService swimmerService;

    /**
     * Mock of the SwimmerToSwimmerResponseConverter bean. Used to simulate calls to this bean.
     */
    @MockBean
    private SwimmerToSwimmerResponseConverter swimmerToSwimmerResponseConverter;

    /**
     * Test the the "retriveAllSwimmsers" with the expected results.
     *
     * The test should return 200 - OK Status; The body of the response is in JSON.
     * The body of the response contains two swimmersResponse objects that are checked
     * to see if they have the proper values.
     *
     * SwimmerService calls and SwimmerToSwimmerResponseConverter calls are mocked.
     *
     * @throws Exception - throws a exception if there is any problem in the test.
     */
    @Test
    public void retrieveAllSwimmersOk() throws Exception {
        List<Swimmer> mockSwimmerList = this.generateSwimmersList();
        List<SwimmerResponse> mockSwimmerResponseList = this.getSwimmerResponseFromSwimmers(mockSwimmerList);

        // Mock the swimmerService.getSwimmers() call
        when(swimmerService.getSwimmers()).thenReturn(mockSwimmerList);

        // Mock the swimmerToSwimmerResponseConverter.swimmerListToSwimmerResponseList(swimmers) call
        when(swimmerToSwimmerResponseConverter.swimmerListToSwimmerResponseList(mockSwimmerList)).thenReturn(mockSwimmerResponseList);

        // Mock the get request
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/swimmers").accept(MediaType.APPLICATION_JSON);

        // Perform the request and then check the values
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(SWIMMER_1_ID) ))
                .andExpect(jsonPath("$[0].fullName", is(SWIMMER_1_FULL_NAME)))
                .andExpect(jsonPath("$[0].category", is(Category.MASTER.toString())))
                .andExpect(jsonPath("$[0].gender", is(Gender.FEMALE.toString())))
                .andExpect(jsonPath("$[1].id", is(SWIMMER_2_ID) ))
                .andExpect(jsonPath("$[1].fullName", is(SWIMMER_2_FULL_NAME)))
                .andExpect(jsonPath("$[1].category", is(Category.JUNIOR.toString())))
                .andExpect(jsonPath("$[1].gender", is(Gender.MALE.toString())));

    }

    /**
     * Helper method that generates a swimmer list with two elements for testing purposes.
     *
     * @return a list with two swimmer elements with predefined values.
     * @throws ParseException - throws a ParseException if there is a problem parsing the birth date for the swimmer.
     */
    private List<Swimmer> generateSwimmersList() throws ParseException {
        List<Swimmer> results = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);

        // Swimmer Response 1
        Swimmer swimmer = new Swimmer();
        swimmer.setId(SWIMMER_1_ID);
        swimmer.setFirstName(SWIMMER_1_FIRST_NAME);
        swimmer.setLastName(SWIMMER_1_LAST_NAME);
        swimmer.setLicence(SWIMMER_1_LICENCE);
        swimmer.setBirthDate(simpleDateFormat.parse(SWIMMER_1_BIRTH_DATE));
        swimmer.setBirthPlace(SWIMMER_1_BIRTH_PLACE);
        swimmer.setCategory(Category.MASTER);
        swimmer.setGender(Gender.FEMALE);
        swimmer.setIsMaster(true);
        results.add(swimmer);

        // Swimmer Response 2
        swimmer = new Swimmer();
        swimmer.setId(SWIMMER_2_ID);
        swimmer.setFirstName(SWIMMER_2_FIRST_NAME);
        swimmer.setLastName(SWIMMER_2_LAST_NAME);
        swimmer.setLicence(SWIMMER_2_LICENCE);
        swimmer.setBirthDate(simpleDateFormat.parse(SWIMMER_2_BIRTH_DATE));
        swimmer.setBirthPlace(SWIMMER_2_BIRTH_PLACE);
        swimmer.setCategory(Category.JUNIOR);
        swimmer.setGender(Gender.MALE);
        swimmer.setIsMaster(false);
        results.add(swimmer);

        return results;
    }

    /**
     * Helper method that generates a swimmerResponse list with two elements for testing purposes.
     *
     * @param swimmers - list of swimmers to generate the swimmerResponse from.
     * @return the generated list of swimmerResponse elements.
     */
    private List<SwimmerResponse> getSwimmerResponseFromSwimmers(List<Swimmer> swimmers) {
        List<SwimmerResponse> result = new ArrayList<>();
        SwimmerResponse swimmerResponse;
        for (Swimmer swimmer : swimmers) {
            swimmerResponse = new SwimmerResponse();
            swimmerResponse.setId(swimmer.getId());
            swimmerResponse.setFullName(swimmer.getFullName());
            swimmerResponse.setCategory(swimmer.getCategory());
            swimmerResponse.setGender(swimmer.getGender());
            result.add(swimmerResponse);
        }

        return result;
    }

}
