package seedu.address.logic.NusModules;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to parse NusModule json file
 */
public class NusModulesParser {
    private final File nusModuleJson = new File("src/main/java/seedu/address/logic/NUSModules/NUSModules.json");
    private ObjectMapper mapper = new ObjectMapper();
    private NusModule[] nusModuleList;

    private List<NusModule> nusModuleLists = mapper.readValue(nusModuleJson, new TypeReference<List<NusModule>>() {
    });

    public NusModulesParser() throws IOException {
    }

    /**
     * Queries module title based on given module code
     *
     * @param moduleCode Module Code to query
     * @return NusModule object if module is present, null if not
     */
    public NusModule getModuleTitle(String moduleCode) {
        for (NusModule nusModule : nusModuleLists) {
            if (nusModule.getModuleTitle().equals(moduleCode)) {
                return nusModule;
            }
        }
        return null;
    }

    /**
     * Queries module code based on given module title
     *
     * @param moduleTitle Module Title to query
     * @return NusModule object if title is present, null if not
     */
    public NusModule getModuleCode(String moduleTitle) {
        for (NusModule nusModule : nusModuleLists) {
            if (nusModule.getModuleCode().equals(moduleTitle)) {
                return nusModule;
            }
        }
        return null;
    }
}
