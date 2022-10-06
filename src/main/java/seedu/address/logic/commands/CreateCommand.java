package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.poc.Poc;

/**
 * Changes the remark of an existing person in the address book.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";


    //Update here
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a Point-Of-Contact and links to Company. "
            + "Parameters: "
            + "Index "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "HP "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "97814456 "
            + PREFIX_EMAIL + "test@test.com "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New POC created: %1$s";

    private final Index index;

    private final Poc POC;

    /**
     * @param index of the company in the company list to add the POC to
     * @param POC to be added
     */

    public CreateCommand(Index index, Poc POC) {
        this.index = index;
        this.POC = POC;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }


}
