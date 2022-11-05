package seedu.address.testutil;

import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private EditModuleCommand.EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleCommand.EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleCommand.EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleCommand.EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleCommand.EditModuleDescriptor();
        descriptor.setModuleCode(module.getModuleCode());
        descriptor.setModuleName(module.getModuleName());
        descriptor.setModuleCredit(module.getModuleCredit());
    }

    /**
     * Sets the {@code moduleCode} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        return this;
    }

    /**
     * Sets the {@code moduleName} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withModuleName(String moduleName) {
        descriptor.setModuleName(new ModuleName(moduleName));
        return this;
    }

    /**
     * Sets the {@code moduleCredit} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withModuleCredit(int moduleCredit) {
        descriptor.setModuleCredit(new ModuleCredit(moduleCredit));
        return this;
    }

    public EditModuleCommand.EditModuleDescriptor build() {
        return descriptor;
    }
}
