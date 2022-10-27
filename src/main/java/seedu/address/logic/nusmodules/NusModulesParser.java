package seedu.address.logic.nusmodules;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to parse NusModule json file
 */
//TODO to throw error when module not available
public class NusModulesParser {
    private static final File nusModuleJson = new File("file:src/main/java/seedu/address/logic/nusmodules/NUSModules.json");

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Query module title based on given module code
     *
     * @param moduleCode Module Code to query
     * @return NusModule Title if module is present, null if not
     */
    public static String getModuleTitle(String moduleCode) {
        try {
            List<NusModule> nusModuleLists = mapper.readValue(nusModuleJson, new TypeReference<List<NusModule>>() {});
            for (NusModule nusModule : nusModuleLists) {
                if (nusModule.getModuleCode().equalsIgnoreCase(moduleCode)) {
                    return nusModule.getModuleTitle();
                }
            }
            return null;
        } catch (IOException e) {
            //TODO
        }
        return "No module title";
    }

    /**
     * Query module object based on module code
     *
     * @param moduleCode Module code to query
     * @return NusModule object if module is present, null if not
     */
    public NusModule getModule(String moduleCode) {
        try {
            List<NusModule> nusModuleLists = mapper.readValue(nusModuleJson, new TypeReference<List<NusModule>>() {});
            for (NusModule nusModule :nusModuleLists) {
                if (nusModule.getModuleCode().equals(moduleCode)) {
                    return nusModule;
                }
            }
        return null;
        } catch (IOException e) {
            //TODO
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
        try {
            List<NusModule> nusModuleLists = mapper.readValue(nusModuleJson, new TypeReference<List<NusModule>>() {
            });
            for (NusModule nusModule : nusModuleLists) {
                if (nusModule.getModuleTitle().equals(moduleCode)) {
                    return nusModule.getSemesterOffered();
                }
            }
            return null;
        } catch (IOException e) {
            //TODO
        }
        return null;
    }
}
