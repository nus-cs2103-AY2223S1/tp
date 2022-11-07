package modtrekt.testutil;

import modtrekt.model.ModuleList;
import modtrekt.model.module.Module;

/**
 * A utility class to help with building ModuleList objects.
 * Example usage: <br>
 *     {@code ModuleList ab = new ModuleListBuilder().withModule("John", "Doe").build();}
 */
public class ModuleListBuilder {

    private ModuleList moduleList;

    public ModuleListBuilder() {
        moduleList = new ModuleList();
    }

    public ModuleListBuilder(ModuleList moduleBook) {
        this.moduleList = moduleBook;
    }

    /**
     * Adds a new {@code Module} to the {@code ModuleList} that we are building.
     */
    public ModuleListBuilder withModule(Module module) {
        moduleList.addModule(module);
        return this;
    }

    public ModuleList build() {
        return moduleList;
    }
}
