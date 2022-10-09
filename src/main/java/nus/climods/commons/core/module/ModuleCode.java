package nus.climods.commons.core.module;

import java.util.Objects;

/**
 * Represents a Module code supplied by the user.
 */
public class ModuleCode {
    private String moduleCode;
    public ModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ModuleCode)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        ModuleCode that = (ModuleCode) o;
        return Objects.equals(moduleCode, that.moduleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode);
    }
}
