package nus.climods.testutil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.ModulesApi;

import nus.climods.model.module.Module;

/**
 * Test utility class for ModuleList
 */
public class ModuleListTestUtil {

    /**
     * Load module list
     *
     * @param academicYear academic year
     * @return list of modules in the specified academic year
     */
    public static List<Module> loadModuleList(String academicYear) {
        try {
            return ModulesApi.getInstance().acadYearModuleInfoJsonGet(academicYear).stream()
                    .map(moduleInfo -> new Module(moduleInfo, academicYear))
                    .collect(Collectors.toList());
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
