package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.link.Link;
import seedu.address.model.module.task.Task;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building {@code Module} objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS2109S";
    public static final String DEFAULT_MODULE_TITLE = EMPTY_STRING;
    public static final List<Task> DEFAULT_TASKS = new ArrayList<>();
    public static final Set<Link> DEFAULT_LINKS = new TreeSet<>();
    public static final Set<Person> DEFAULT_PERSONS = new HashSet<>();

    private ModuleCode moduleCode;
    private ModuleTitle moduleTitle;
    private List<Task> tasks;
    private Set<Link> links;
    private Set<Person> persons;
    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        moduleTitle = new ModuleTitle(DEFAULT_MODULE_TITLE);
        tasks = DEFAULT_TASKS;
        links = DEFAULT_LINKS;
        persons = DEFAULT_PERSONS;
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleCode = moduleToCopy.getModuleCode();
        moduleTitle = moduleToCopy.getModuleTitle();
        tasks = new ArrayList<>(moduleToCopy.getTasks());
        links = new TreeSet<>(moduleToCopy.getLinks());
        persons = new HashSet<>(moduleToCopy.getPersons());
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
     * Sets the {@code List} of {@code Task} objects of the {@code Module} that
     * we are building.
     */
    public ModuleBuilder withTasks(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        return this;
    }

    /**
     * Sets the set of {@code Link} objects of the {@code Module} that we are building.
     */
    public ModuleBuilder withLinks(Set<Link> links) {
        this.links = new TreeSet<>(links);
        return this;
    }

    /**
     * Sets the set of {@code Person} objects of the {@code Module} that we are building.
     */
    public ModuleBuilder withPersons(Set<Person> persons) {
        this.persons = new HashSet<>(persons);
        return this;
    }

    /**
     * Builds a new {@code Module}.
     * @return new {@code Module} that was built.
     */
    public Module build() {
        return new Module(moduleCode, moduleTitle, tasks, links, persons);
    }

}
