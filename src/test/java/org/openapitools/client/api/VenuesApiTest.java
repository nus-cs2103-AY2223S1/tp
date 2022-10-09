package org.openapitools.client.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Module;
import org.openapitools.client.model.ModuleCondensed;
import org.openapitools.client.model.ModuleInformation;


/**
 * API tests for VenuesAPI
 */
public class VenuesApiTest {

    private final VenuesApi api = new VenuesApi();

    /**
     * Test that list of all venues for academic year and semester can be retrieved
     *
     * @throws ApiException if error occured during request
     */
    @Test
    public void acadYearSemestersVenuesGetTest() throws ApiException {
        String acadYear = "2022-2023";
        BigDecimal semester = BigDecimal.valueOf(1);
        List<String> venues = api.acadYearSemestersSemesterVenuesJsonGet(acadYear, semester);

        assertTrue(venues.size() > 0);
    }

    /**
     * Test that detailed information for all venues in given academic year and semester can be retrieved
     *
     * @throws ApiException if error occured during request
     */
    @Test
    public void acadYearSemestersDetailedVenuesGetTest() throws ApiException {
        String acadYear = "2022-2023";
        BigDecimal semester = BigDecimal.valueOf(1);
        List<String> detailedVenues = api.acadYearSemestersSemesterVenuesJsonGet(acadYear, semester);

        assertTrue(detailedVenues.size() > 0);
    }

}
