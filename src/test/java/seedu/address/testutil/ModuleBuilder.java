package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building {@code Module} objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final String DEFAULT_MODULE_TITLE = "";
    public static final Set<Task> DEFAULT_TASKS = new HashSet<>();
    public static final Set<Link> DEFAULT_LINKS = new HashSet<>();

    private ModuleCode moduleCode;
    private ModuleTitle moduleTitle;
    private Set<Task> tasks;
    private Set<Link> links;
    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        moduleTitle = new ModuleTitle(DEFAULT_MODULE_TITLE);
        tasks = DEFAULT_TASKS;
        links = DEFAULT_LINKS;
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleCode = moduleToCopy.getModuleCode();
        moduleTitle = moduleToCopy.getModuleTitle();
        tasks = moduleToCopy.getTasks();
        links = moduleToCopy.getLinks();
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code ModuleTitle} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleTitle(String moduleTitle) {
        this.moduleTitle = new ModuleTitle(moduleTitle);
        return this;
    }

    /**
     * Sets the set of {@code Task} objects of the {@code Module} that we are building.
     */
    public ModuleBuilder withTasks(Set<Task> tasks) {
        this.tasks = new HashSet<>(tasks);
        return this;
    }

    /**
     * Sets the set of {@code Link} objects of the {@code Module} that we are building.
     */
    public ModuleBuilder withLinks(Set<Link> links) {
        this.links = new HashSet<>(links);
        return this;
    }

    public Module build() {
        return new Module(moduleCode, moduleTitle, tasks, links);
    }

}
