package seedu.address.model.module;

/**
 * Module that CS student has taken.
 */
public class PreviousModule implements Module {
    private String code;

    /**
     * Constructor for new PreviousModule instance.
     *
     * @param code Module code name e.g. CS2103T.
     */
    public PreviousModule(String code) {
        this.code = code;
    }
}
