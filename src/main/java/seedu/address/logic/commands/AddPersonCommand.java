package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * The abstract base class of all AddPersonCommand variations,
 * including AddBuyerCommand, AddDelivererCommand, and AddSupplierCommand.
 */
public abstract class AddPersonCommand extends Command {

    public static final String COMMAND_WORD = "add-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Please specify the person category: 'c/Buyer', 'c/Deliverer', or 'c/Supplier'"
            + "Parameters: "
            + PREFIX_PERSON_CATEGORY + "PERSON_CATEGORY "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSON_CATEGORY + "Buyer "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    /**
     * Construct a default base AddPersonCommand.
     */
    public AddPersonCommand() {}

    /**
     * Returns the command result to display.
     * This is an abstract method that requires its subclasses,
     * such as {@code AddBuyerCommand, AddDelivererCommand, AddSupplierCommand}, to implement.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult the result to be displayed.
     * @throws CommandException if the command cannot work.
     */
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
