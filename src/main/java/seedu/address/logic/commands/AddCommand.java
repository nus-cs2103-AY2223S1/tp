package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELIGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Survey;

/**
 * Adds a person to Survin.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to Survin.\n" + "Parameters: "
            + PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_GENDER + "GENDER " + PREFIX_BIRTHDATE + "BIRTHDATE " + PREFIX_RACE + "RACE " + PREFIX_RELIGION
            + "RELIGION " + "[" + PREFIX_SURVEY + "SURVEY]... " + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe " + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com " + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_GENDER + "male " + PREFIX_BIRTHDATE + "1998-11-28 " + PREFIX_RACE + "Chinese " + PREFIX_RELIGION
            + "Christian " + PREFIX_SURVEY + "Shopping survey " + PREFIX_TAG + "friends " + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in Survin";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            Person person = model.getPerson(toAdd).orElseThrow(() -> new CommandException(MESSAGE_DUPLICATE_PERSON));
            if (person.getSurveys().containsAll(toAdd.getSurveys())) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
            Set<Survey> surveySet = new HashSet<>();
            surveySet.addAll(person.getSurveys());
            surveySet.addAll(toAdd.getSurveys());
            Person newPerson = new Person(toAdd.getName(), toAdd.getPhone(), toAdd.getEmail(), toAdd.getAddress(),
                    toAdd.getGender(), toAdd.getBirthdate(), toAdd.getRace(), toAdd.getReligion(), surveySet,
                    toAdd.getTags());
            model.setPerson(person, newPerson);
        } else {
            model.addPerson(toAdd);
        }
        model.commitSurvin();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && toAdd.equals(((AddCommand) other).toAdd));
    }
}
