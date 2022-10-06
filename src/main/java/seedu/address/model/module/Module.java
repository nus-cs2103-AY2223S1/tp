package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

public class Module {
    private final ModuleCode moduleCode;

    public Module(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public boolean isSameModuleCode(Module otherModule) {
        if (otherModule == null) {
            return false;
        }
        return otherModule == this || otherModule.moduleCode.equals(this.moduleCode);
    }

}
