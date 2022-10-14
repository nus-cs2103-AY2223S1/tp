package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.poc.Poc;

/**
 * Creates a new poc to be added to the specified company.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    //Update here
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a Point-Of-Contact and links to Company. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "HP "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "97814456 "
            + PREFIX_EMAIL + "test@test.com "
            + PREFIX_TAG + "MainPoc "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New POC created: %1$s\nLinked to Company: %2$s";
    public static final String MESSAGE_DUPLICATE_POC = "This company already has %1$s as POC";
    public static final String MESSAGE_POC_INVALID = "POC cannot be created. Enter a valid POC details:\n"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "HP "
            + PREFIX_EMAIL + "EMAIL ";

    private final Index index;
    private final Poc poc;

    /**
     * @param index of the company in the company list to add the POC to
     * @param poc to be added
     */
    public CreateCommand(Index index, Poc poc) {
        requireNonNull(index);
        requireNonNull(poc);

        this.index = index;
        this.poc = poc;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredCompanyList();

        // if index of company not valid
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());

        if (clientToEdit.hasPoc(poc)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_POC, poc.getName()));
        }

        Client editedClient = clientToEdit;
        editedClient.addPoc(poc);
        model.setCompany(clientToEdit, editedClient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, poc.getName(), editedClient));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && index.equals(((CreateCommand) other).index)
                && poc.equals(((CreateCommand) other).poc));
    }

}
