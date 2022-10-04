package modtrekt.model.module;

import java.util.List;

/**
 * Represents a module in the module list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {
    public final String code;
    public final String name;
    public final int credits;
    public final List<String> tasks;

    /**
     * Creates a Module with the given code, name, credits and tasks.
     *
     * @param code    the module code
     * @param name    the module name
     * @param credits the number of credits for the module
     * @param tasks   the list of tasks for the module
     */
    public Module(String code, String name, int credits, List<String> tasks) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.tasks = tasks;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getCredits() {
        return credits;
    }
}
