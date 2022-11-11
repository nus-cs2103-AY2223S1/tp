package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_EMAIL_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_NAME_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_PHONE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_TAGS_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.LABEL_PERSON_EMAIL;
import static seedu.address.logic.parser.CliSyntax.LABEL_PERSON_NAME;
import static seedu.address.logic.parser.CliSyntax.LABEL_PERSON_PHONE;
import static seedu.address.logic.parser.CliSyntax.LABEL_PERSON_TAGS;

import java.util.HashSet;
import java.util.Set;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds a person to the TruthTable.
 */
@CommandLine.Command(name = AddPersonCommand.COMMAND_WORD, aliases = {AddPersonCommand.ALIAS},
        mixinStandardHelpOptions = true)
public class AddPersonCommand extends Command {
    public static final String COMMAND_WORD = "person";
    public static final String ALIAS = "p";
    public static final String FULL_COMMAND = AddCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to add a new person to TruthTable.\n";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the contacts list";

    @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true,
            paramLabel = LABEL_PERSON_NAME,
            description = FLAG_PERSON_NAME_DESCRIPTION)
    private Name name;

    @CommandLine.Option(names = {FLAG_PHONE_STR, FLAG_PHONE_STR_LONG}, required = true,
            paramLabel = LABEL_PERSON_PHONE,
            description = FLAG_PERSON_PHONE_DESCRIPTION)
    private Phone phone;

    @CommandLine.Option(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG}, required = true,
            paramLabel = LABEL_PERSON_EMAIL,
            description = FLAG_PERSON_EMAIL_DESCRIPTION)
    private Email email;

    @CommandLine.Option(names = {FLAG_TAG_STR, FLAG_TAG_STR_LONG}, description = FLAG_PERSON_TAGS_DESCRIPTION,
            paramLabel = LABEL_PERSON_TAGS,
            arity = "*")
    private Set<Tag> tags = new HashSet<>();

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public AddPersonCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);

        Person toAdd = new Person(name, phone, email, tags);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
