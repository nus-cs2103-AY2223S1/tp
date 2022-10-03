package seedu.address.model.module;

/**
 * Module that CS student is currently taking.
 */
public class CurrentModule implements Module {
    private String code;

    /**
     * Constructor for new CurrentModule instance.
     *
     * @param code Module code name e.g. CS2103T.
     */
    public CurrentModule(String code) {
        this.code = code;
    }
}
