package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javafx.collections.ObservableList;
import seedu.address.model.module.link.Link;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskList;
import seedu.address.model.person.Person;

/**
 * Represents a Module in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module implements Comparable<Module> {

    // Default value for empty module title
    public static final String EMPTY_MODULE_TITLE = "";

    // Identity fields
    private final ModuleCode moduleCode;

    // Data fields
    private final ModuleTitle moduleTitle;
    private final TaskList tasks;
    private final Set<Link> links;
    private final Set<Person> persons;

    /**
     * Every field must be present and not null.
     */
    public Module(ModuleCode moduleCode, ModuleTitle moduleTitle,
                  List<Task> tasks,
                  Set<Link> links,
                  Set<Person> persons) {
        requireAllNonNull(moduleCode, moduleTitle, tasks, links, persons);
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
        this.tasks = new TaskList(tasks);
        this.links = new TreeSet<>(links);
        this.persons = new HashSet<>(persons);
    }

    /**
     * Adds a {@code Module} with module code, title and links but without
     * any tasks and persons.
     */
    public Module(ModuleCode moduleCode, ModuleTitle moduleTitle,
                  Set<Link> links) {
        requireAllNonNull(moduleCode, moduleTitle, links);
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
        this.tasks = new TaskList();
        this.links = new TreeSet<>(links);
        this.persons = new HashSet<>();
    }

    /**
     * Adds a {@code Module} with module code, without module title and without any
     * associated tasks, links and persons.
     */
    public Module(ModuleCode moduleCode) {
        this(moduleCode, new ModuleTitle(EMPTY_MODULE_TITLE), new TreeSet<>());
    }

    /**
     * Adds a {@code Module} with module code and module title but without any
     * associated tasks, links and persons.
     */
    public Module(ModuleCode moduleCode, ModuleTitle moduleTitle) {
        this(moduleCode, moduleTitle, new TreeSet<>());
    }
    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns the module code as a {@code String} in upper case.
     */
    public String getModuleCodeAsUpperCaseString() {
        return moduleCode.getModuleCodeAsUpperCaseString();
    }

    public ModuleTitle getModuleTitle() {
        return moduleTitle;
    }

    /**
     * Returns the module title as a {@code String} in upper case.
     */
    public String getModuleTitleAsUpperCaseString() {
        return moduleTitle.getModuleTitleAsUpperCaseString();
    }

    /**
     * Returns an immutable links set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Link> getLinks() {
        return Collections.unmodifiableSet(links);
    }

    /**
     * Returns a copied links set
     */
    public Set<Link> copyLinks() {
        return new TreeSet<>(links);
    }

    /**
     * Returns an immutable task set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns an unmodifiable {@code ObservableList} of the tasks available.
     */
    public ObservableList<Task> getTasks() {
        return tasks.asUnmodifiableObservableList();
    }

    /**
     * Returns true if the task list contains duplicates.
     */
    public Boolean hasDuplicateTasks() {
        return tasks.containsDuplicate();
    }

    /**
     * Returns an immutable person set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Person> getPersons() {
        return Collections.unmodifiableSet(persons);
    }

    /**
     * Returns a copied persons set.
     */
    public Set<Person> copyPersons() {
        return new HashSet<>(persons);
    }

    /**
     * Returns true if both modules have the same moduleCode.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleCode().equals(getModuleCode());
    }

    /**
     * Returns a boolean indicating if the specified person is contained in the module.
     *
     * @param person The person that is being checked for.
     * @return Boolean indicating if the specified person is contained in the module.
     */
    public boolean containsPerson(Person person) {
        return persons.contains(person);
    }

    /**
     * Replaces the person {@code target} in the module's set of persons with {@code editedPerson}.
     *
     * @param target The person in the module's set to be replaced.
     * @param editedPerson The person to replace {@code target} in the module's set.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        persons.remove(target);
        persons.add(editedPerson);
    }

    /**
     * Removes the specified person from the module's set of persons.
     *
     * @param person The person to be removed from the module's set of persons.
     */
    public void removePerson(Person person) {
        persons.remove(person);
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getModuleTitle().equals(getModuleTitle())
                && otherModule.getLinks().equals(getLinks())
                && otherModule.getTasks().equals(getTasks())
                && otherModule.getPersons().equals(getPersons());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, links, tasks, persons);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode());

        if (!moduleTitle.toString().isBlank()) {
            builder.append("; Title: ");
            builder.append(moduleTitle);
        }

        Set<Link> links = getLinks();
        if (!links.isEmpty()) {
            builder.append("; Links: ");
            links.forEach(builder::append);
        }

        List<Task> tasks = getTasks();
        if (!tasks.isEmpty()) {
            builder.append("; Tasks: ");
            tasks.forEach(builder::append);
        }

        Set<Person> persons = getPersons();
        if (!persons.isEmpty()) {
            builder.append("; Persons: ");
            persons.forEach(person -> builder.append("[" + person.toString() + "]"));
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Module other) {
        return moduleCode.compareTo(other.moduleCode);
    }
}
