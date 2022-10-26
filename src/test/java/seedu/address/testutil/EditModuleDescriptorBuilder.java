package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.ModuleCommand;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Module;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Person;
import seedu.address.model.person.user.User;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private ModuleCommand.EditModuleDescriptor descriptor;

    /**
     * A constructor for EditModuleDescriptorBuilder.
     */
    public EditModuleDescriptorBuilder() {
        descriptor = new ModuleCommand.EditModuleDescriptor();
    }

    /**
     * A constructor for EditModuleDescriptorBuilder that takes in an EditModuleDescriptor.
     * @param descriptor is the EditModuleDescriptor used by this class
     */
    public EditModuleDescriptorBuilder(ModuleCommand.EditModuleDescriptor descriptor) {
        this.descriptor = new ModuleCommand.EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code person}'s modules
     */
    public EditModuleDescriptorBuilder(Person person) {
        descriptor = new ModuleCommand.EditModuleDescriptor();
        descriptor.setCurrModules(person.getCurrModules());
        descriptor.setPrevModules(person.getPrevModules());
        descriptor.setPlanModules(person.getPlanModules());
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code user}'s modules
     */
    public EditModuleDescriptorBuilder(User user) {
        descriptor = new ModuleCommand.EditModuleDescriptor();
        descriptor.setCurrModules(user.getCurrModules());
        descriptor.setPrevModules(user.getPrevModules());
        descriptor.setPlanModules(user.getPlanModules());
    }

    /**
     * Parses the {@code currMods} into a {@code Set<CurrentModule>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withCurrentModules(String... currMods) {
        Set<CurrentModule> currentModuleSet = Stream.of(currMods).map(CurrentModule::new).collect(Collectors.toSet());
        descriptor.setCurrModules(currentModuleSet);
        return this;
    }

    /**
     * Parses the {@code prevMods} into a {@code Set<PreviousModule>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withPreviousModules(String... prevMods) {
        Set<PreviousModule> previousModuleSet =
                Stream.of(prevMods).map(PreviousModule::new).collect(Collectors.toSet());
        descriptor.setPrevModules(previousModuleSet);
        return this;
    }

    /**
     * Parses the {@code planMods} into a {@code Set<PlannedModule>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withPlannedModules(String... planMods) {
        Set<PlannedModule> plannedModuleSet = Stream.of(planMods).map(PlannedModule::new).collect(Collectors.toSet());
        descriptor.setPlanModules(plannedModuleSet);
        return this;
    }

    /**
     * Parses the {@code modsToRemove} into a {@code Set<Module>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withModToRemove(String... modsToRemove) {
        Set<Module> modulesToRemoveSet = Stream.of(modsToRemove).map(Module::new).collect(Collectors.toSet());
        descriptor.setModulesToRemove(modulesToRemoveSet);
        return this;
    }

    public ModuleCommand.EditModuleDescriptor build() {
        return descriptor;
    }
}
