package seedu.address.model.module;

/**
 * Module that CS student is currently taking.
 */
public class CurrentModule extends Module {
    public CurrentModule(String moduleName) {
        super(moduleName);
    }

    public PreviousModule toPrevModule() {
        return new PreviousModule(this.moduleName);
    }

}
