package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;

/**
 * This class represents a Jackson friendly version of {@link Module}.
 */
public class JsonAdaptedModule {
    public static final String MISSING_MODULE_MESSAGE_FORMAT =
            "Module's fields are not present";
    private final String moduleCode;
    private final String moduleName;
    private final String moduleCredit;

    /**
     * Builds a {@code JsonAdaptedModule} with the module code, module name and module credit.
     *
     * @param moduleCode The module code of the module,
     * @param moduleName The module name of the module.
     * @param moduleCredit The module credit of the module.
     */
    public JsonAdaptedModule(@JsonProperty("modCode") String moduleCode, @JsonProperty("modName") String moduleName,
                             @JsonProperty("modCredit") String moduleCredit) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredit = moduleCredit;
    }

    /**
     * Converts an existing module into {@code JsonAdaptedModule} object
     *
     * @param mod The module object being converted.
     */
    public JsonAdaptedModule(Module mod) {
        moduleCode = mod.getModuleCode().moduleCode;
        moduleName = mod.getModuleName().moduleName;
        moduleCredit = String.valueOf(mod.getModuleCredit().moduleCredit);
    }

    /**
     * Converts this Jackson-friendly module object into a Module Object.
     *
     * @return The Module Object which is created.
     * @throws IllegalValueException If moduleCode is null.
     */
    public Module toModelType() throws IllegalValueException {
        if (moduleCode == null || moduleName == null || moduleCredit == null) {
            throw new IllegalValueException(MISSING_MODULE_MESSAGE_FORMAT);
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MODULE_CODE_CONSTRAINTS);
        }
        if (!ModuleName.isValidModuleName(moduleName)) {
            throw new IllegalValueException(ModuleName.MODULE_NAME_CONSTRAINTS);
        }
        int integerModuleCredit;
        try {
            integerModuleCredit = Integer.parseInt(moduleCredit);
        } catch (NumberFormatException nfe) {
            throw new IllegalValueException(ModuleCredit.MODULE_CREDIT_CONSTRAINTS);
        }
        if (!ModuleCredit.isValidModuleCredit(integerModuleCredit)) {
            throw new ParseException(ModuleCredit.MODULE_CREDIT_CONSTRAINTS);
        }
        final ModuleCode modCode = new ModuleCode(moduleCode);
        final ModuleName modName = new ModuleName(moduleName);
        final ModuleCredit modCredit = new ModuleCredit(integerModuleCredit);
        return new Module(modCode, modName, modCredit);
    }
}
