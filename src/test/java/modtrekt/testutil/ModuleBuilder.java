package modtrekt.testutil;

import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.module.ModTaskCount;
import modtrekt.model.module.Module;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_NAME = "Discrete Structures";
    public static final String DEFAULT_CODE = "CS1231S";
    public static final String DEFAULT_CREDIT = "4";

    private ModName name;
    private ModCode code;
    private ModCredit credit;
    private boolean isDone = false;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        name = new ModName(DEFAULT_NAME);
        code = new ModCode(DEFAULT_CODE);
        credit = new ModCredit(DEFAULT_CREDIT);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code personToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        name = moduleToCopy.getName();
        code = moduleToCopy.getCode();
        credit = moduleToCopy.getCredits();
    }

    /**
     * Sets the {@code ModName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.name = new ModName(name);
        return this;
    }

    /**
     * Sets the {@code ModCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.code = new ModCode(code);
        return this;
    }

    /**
     * Sets the {@code ModCredit} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCredit(String credit) {
        this.credit = new ModCredit(credit);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Module} that we are building.
     */
    public ModuleBuilder withDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }


    public Module build() {
        return new Module(code, name, credit, new ModTaskCount("0"), isDone);
    }

}
