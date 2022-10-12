package modtrekt.model.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import modtrekt.model.task.Task;

/**
 * Represents a module in the module list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {
    private final ModCode code;
    private final ModName name;
    private final ModCredit credits;
    private final ArrayList<Task> tasksList;

    /**
     * Creates a Module with the given code, name, credits and tasks.
     *
     * @param code    the module code
     * @param name    the module name
     * @param credits the number of credits for the module
     */
    public Module(ModCode code, ModName name, ModCredit credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.tasksList = new ArrayList<>();
    }

    /**
     * Creates a Module with the string representation of code, name, credits and tasks.
     *
     * @param code    the module code
     * @param name    the module name
     * @param credits the number of credits for the module
     */
    public Module(String code, String name, String credits) {
        this.code = new ModCode(code);
        this.name = new ModName(name);
        this.credits = new ModCredit(credits);
        this.tasksList = new ArrayList<>();
    }

    public ModName getName() {
        return name;
    }

    public ModCode getCode() {
        return code;
    }

    public ModCredit getCredits() {
        return credits;
    }

    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }
    public int getTaskCount() {
        return this.tasksList.size();
    }


    public void addTask(Task t) {
        this.tasksList.add(t);
    }

    public void removeTask(Task t) {
        this.tasksList.remove(t);
    }


    public void addTasks(List<Task> t) {
        this.tasksList.addAll(t);
    }


    /**
     * Returns true if both modules have the same name.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getName().equals(getName());
    }

    /**
     * Returns true if both modules have the same name.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameCodeModule(ModCode otherModule) {

        return otherModule != null
                && otherModule.equals(getCode());
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
        return otherModule.getName().equals(getName())
                && otherModule.getCode().equals(getCode())
                && otherModule.getCredits() == getCredits();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, code, credits);
    }

    @Override
    public String toString() {
        return getName() + ", Code: " + getCode() + ", Credits: " + getCredits();
    }
}
