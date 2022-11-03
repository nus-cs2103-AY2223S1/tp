package seedu.address.testutil;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final String DEFAULT_MODULE_NAME = "Software Engineering";
    public static final int DEFAULT_MODULE_CREDIT = 4;

    private ModuleCode moduleCode;
    private ModuleName moduleName;
    private ModuleCredit moduleCredit;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        moduleName = new ModuleName(DEFAULT_MODULE_NAME);
        moduleCredit = new ModuleCredit(DEFAULT_MODULE_CREDIT);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleCode = moduleToCopy.getModuleCode();
        moduleName = moduleToCopy.getModuleName();
        moduleCredit = moduleToCopy.getModuleCredit();
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code ModuleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleName(String moduleName) {
        this.moduleName = new ModuleName(moduleName);
        return this;
    }

    /**
     * Sets the {@code ModuleCredit} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleCredit(int moduleCredit) {
        this.moduleCredit = new ModuleCredit(moduleCredit);
        return this;
    }

    public Module build() {
        return new Module(moduleCode, moduleName, moduleCredit);
    }
}
