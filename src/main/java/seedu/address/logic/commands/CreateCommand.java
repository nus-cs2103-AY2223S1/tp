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
import seedu.address.model.company.Company;

/**
 * Creates a new company to be added to the specified client.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    //Update here
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a company and links to Client. "
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
            + PREFIX_TAG + "MainCompany "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New Company created: %1$s\nLinked to Client: %2$s";
    public static final String MESSAGE_DUPLICATE_COMPANY = "This client already has %1$s as Company";
    public static final String MESSAGE_COMPANY_INVALID = "Company cannot be created. Enter a valid Company details:\n"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "HP "
            + PREFIX_EMAIL + "EMAIL ";

    private final Index index;
    private final Company company;

    /**
     * @param index of the client in the client list to add the Company to
     * @param company to be added
     */
    public CreateCommand(Index index, Company company) {
        requireNonNull(index);
        requireNonNull(company);

        this.index = index;
        this.company = company;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        // if index of client not valid
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());

        if (clientToEdit.hasCompany(company)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_COMPANY, company.getName()));
        }

        Client editedClient = clientToEdit;
        editedClient.addCompany(company);
        model.setClient(clientToEdit, editedClient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, company.getName(), editedClient));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && index.equals(((CreateCommand) other).index)
                && company.equals(((CreateCommand) other).company));
    }

}
