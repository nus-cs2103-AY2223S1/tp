package seedu.address.model.module;

/**
 * Modules CS student plans to take.
 */
public class PlannedModule implements Module {
    private String code;

    /**
     * Constructor for new PlannedModule instance.
     *
     * @param code Module code name e.g. CS2103T.
     */
    public PlannedModule(String code) {
        this.code = code;
    }
}
