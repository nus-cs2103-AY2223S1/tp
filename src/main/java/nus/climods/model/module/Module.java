package nus.climods.model.module;

import java.util.List;

import org.openapitools.client.model.ModuleInformationSemesterDataInner;
import org.openapitools.client.model.Workload;

/**
 * A wrapper class for <code>ModuleCondensed</code>
 */
public class Module {

    private final org.openapitools.client.model.ModuleInformation apiModuleInfo;

    public Module(org.openapitools.client.model.ModuleInformation apiModuleInfo) {
        this.apiModuleInfo = apiModuleInfo;
    }

    public String getTitle() {
        return apiModuleInfo.getTitle();
    }

    public String getCode() {
        return apiModuleInfo.getModuleCode();
    }

    public String getDepartment() {
        return apiModuleInfo.getDepartment();
    }

    public Workload getWorkload() {
        return apiModuleInfo.getWorkload();
    }

    public List<ModuleInformationSemesterDataInner> getSemesterData() {
        return apiModuleInfo.getSemesterData();
    }

    public int getModuleCredit() {
        String moduleCreditStr = apiModuleInfo.getModuleCredit();
        if (moduleCreditStr.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(apiModuleInfo.getModuleCredit());
    }
}
