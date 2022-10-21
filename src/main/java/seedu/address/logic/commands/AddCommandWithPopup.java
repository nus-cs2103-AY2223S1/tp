package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class AddCommandWithPopup extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String ADD_BUYER = "BUYER";
    public static final String ADD_SUPPLIER = "SUPPLIER";
    public static final String ADD_DELIVERER = "DELIVERER";
    public static final String ADD_ORDER = "ORDER";
    public static final String ADD_PET = "PET";

    private final String typeToAdd;

    public AddCommandWithPopup(String typeToAdd) {
        this.typeToAdd = typeToAdd;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
    }
}
