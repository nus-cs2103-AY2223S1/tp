package seedu.address.logic.commands.addcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Adds a person to the address book by using a pop-up window
 * (currently just buyer and supplier, may extend to deliverer, order, and pet in the future).
 */
public class AddCommandWithPopup extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "Add a Buyer/Supplier with a pop-up window.\n"
            + "Example: add buyer/supplier";
    public static final String MESSAGE_SUCCESS = "Pop-up window for adding %1$s initialised";

    public static final String ADD_BUYER = "BUYER";
    public static final String ADD_SUPPLIER = "SUPPLIER";
    public static final String ADD_DELIVERER = "DELIVERER";

    private final String typeToAdd;

    /**
     * Constructs an {@code AddCommandWithPopup}.
     *
     * @param typeToAdd Type of person to be added.
     */
    public AddCommandWithPopup(String typeToAdd) {
        this.typeToAdd = typeToAdd;
    }

    @Override
    public CommandResult execute(Model model) {
        return CommandResult.createAddByPopupCommandResult(String.format(MESSAGE_SUCCESS, typeToAdd), typeToAdd);
    }

    @Override
    public boolean equals(Object object) {
        return (this == object) || (object instanceof AddCommandWithPopup
                && ((AddCommandWithPopup) object).typeToAdd.equals(typeToAdd));
    }

}
