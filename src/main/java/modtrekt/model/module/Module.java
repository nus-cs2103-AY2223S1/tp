package modtrekt.model.module;

import java.util.Objects;

/**
 * Represents a module in the module list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {
    private final ModCode code;
    private final ModName name;
    private final ModCredit credits;
    private final boolean isDone;
    private ModTaskCount taskCount;

    /**
     * Creates a Module with the given code, name, credits and tasks.
     *
     * @param code    the module code
     * @param name    the module name
     * @param credits the number of credits for the module
     */
    public Module(ModCode code, ModName name, ModCredit credits, ModTaskCount taskCount, boolean isDone) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.taskCount = taskCount;
        this.isDone = isDone;
    }

    /**
     * Creates a Module with the given code, name, credits and tasks.
     *
     * @param code    the module code
     * @param name    the module name
     * @param credits the number of credits for the module
     */
    public Module(ModCode code, ModName name, ModCredit credits, ModTaskCount taskCount) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.taskCount = taskCount;
        this.isDone = false;
    }

    /**
     * Creates a Module with the string representation of code, name, credits and tasks.
     *
     * @param code    the module code
     * @param name    the module name
     * @param credits the number of credits for the module
     */
    public Module(String code, String name, String credits, String taskCount) {
        this.code = new ModCode(code);
        this.name = new ModName(name);
        this.credits = new ModCredit(credits);
        this.taskCount = new ModTaskCount(taskCount);
        this.isDone = false;
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

    public String getTaskCountStr() {
        return taskCount.toString();
    }
    public int getTaskCountInt() {
        return Integer.parseInt(taskCount.toString());
    }

    public boolean isDone() {
        return this.isDone;
    }

    public Module done() {
        return new Module(this.code, this.name, this.credits, this.taskCount, true);
    }

    public Module undone() {
        return new Module(this.code, this.name, this.credits, this.taskCount, false);
    }

    /**
     * Updates the number of tasks the module has.
     *
     * @param taskCount the updated number of tasks.
     */
    public void updateTaskCount(int taskCount) {
        String updatedCount = Integer.toString(taskCount);
        this.taskCount = new ModTaskCount(updatedCount);
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
                && otherModule.getName().equals(getName())
                && otherModule.isDone() == this.isDone();
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
                && otherModule.getCredits() == getCredits()
                && otherModule.isDone() == this.isDone();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, code, credits);
    }

    @Override
    public String toString() {
        return getName() + ", Code: " + getCode() + ", Credits: " + getCredits() + (isDone ? "(DONE)" : "");
    }
}
