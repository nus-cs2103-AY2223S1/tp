package seedu.address.logic.nusmodules;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to parse NusModule json file
 */
//TODO to throw error when module not available
public class NusModulesParser {
    private final File nusModuleJson = new File("src/main/java/seedu/address/logic/NUSModules/NUSModules.json");
    private ObjectMapper mapper = new ObjectMapper();

    private List<NusModule> nusModuleLists = mapper.readValue(nusModuleJson, new TypeReference<List<NusModule>>() {
    });

    public NusModulesParser() throws IOException {
    }

    /**
     * Query module title based on given module code
     *
     * @param moduleCode Module Code to query
     * @return NusModule Title if module is present, null if not
     */
    public String getModuleTitle(String moduleCode) {
        for (NusModule nusModule : nusModuleLists) {
            if (nusModule.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return nusModule.getModuleTitle();
            }
        }
        return null;
    }

    /**
     * Query module object based on module code
     *
     * @param moduleCode Module code to query
     * @return NusModule object if module is present, null if not
     */
    public NusModule getModule(String moduleCode) {
        for (NusModule nusModule : nusModuleLists) {
            if (nusModule.getModuleCode().equals(moduleCode)) {
                return nusModule;
            }
        }
        return null;
    }

    /**
     * Query semester offered based on module code
     *
     * @param moduleCode Module code to query
     * @return Array of integer of modules offered if module is present, null if not.
     */
    public int[] getSemesterOffered(String moduleCode) {
        for (NusModule nusModule : nusModuleLists) {
            if (nusModule.getModuleTitle().equals(moduleCode)) {
                return nusModule.getSemesterOffered();
            }
        }
        return null;
    }
}
