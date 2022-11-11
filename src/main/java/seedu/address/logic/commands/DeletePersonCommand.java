package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_INDEX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_PERSON_INDEX;

import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using its displayed index from the TruthTable.
 */
@CommandLine.Command(name = DeletePersonCommand.COMMAND_WORD,
        aliases = {DeletePersonCommand.ALIAS}, mixinStandardHelpOptions = true)
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "person";
    public static final String ALIAS = "p";
    public static final String FULL_COMMAND = DeleteCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to delete a person from TruthTable.\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    @CommandLine.Parameters(arity = "1", paramLabel = LABEL_PERSON_INDEX, description = FLAG_PERSON_INDEX_DESCRIPTION)
    private Index targetIndex;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public DeletePersonCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

}
