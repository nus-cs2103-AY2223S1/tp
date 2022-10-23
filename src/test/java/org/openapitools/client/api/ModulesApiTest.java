package org.openapitools.client.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.Module;
import org.openapitools.client.model.ModuleCondensed;
import org.openapitools.client.model.ModuleInformation;

/**
 * API tests for ModulesApi
 */
public class ModulesApiTest {

    private final ModulesApi api = new ModulesApi();

    @Test
    public void acadYearModuleInfoJsonGetTest() throws ApiException {
        String acadYear = "2022-2023";
        List<ModuleInformation> response = api.acadYearModuleInfoJsonGet(acadYear);

        assertTrue(response.size() > 0);
    }

    @Test
    public void acadYearModuleListJsonGetTest() throws ApiException {
        String acadYear = "2022-2023";
        List<ModuleCondensed> response = api.acadYearModuleListJsonGet(acadYear);

        assertTrue(response.size() > 0);
    }

    @Test
    public void acadYearModulesModuleCodeJsonGetTest() throws ApiException {
        String acadYear = "2022-2023";
        String moduleCode = "CS2103";
        Module response = api.acadYearModulesModuleCodeJsonGet(acadYear, moduleCode);

        assertEquals(response.getTitle(), "Software Engineering");
    }
}
