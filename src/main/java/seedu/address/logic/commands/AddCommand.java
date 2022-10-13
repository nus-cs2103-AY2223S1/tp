package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXTOFKIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    /**
     * Accepted entity to add types.
     */
    public enum Entity {
        STUDENT,
        TUTOR,
        CLASS;

        public static final String MESSAGE_CONSTRAINTS = "Entity should only be either student, tutor or class";
        public static final String VALIDATION_REGEX = "(?i)student|tutor|class";

        public static boolean isValidEntity(String test) {
            return test.matches(VALIDATION_REGEX);
        }

        /**
         * Returns a {@code Entity} from {@code String entity}.
         */
        public static Entity fromString(String entity) {
            if (entity.matches(("(?i)student"))) {
                return STUDENT;
            } else if (entity.matches("(?i)tutor")) {
                return TUTOR;
            } else if (entity.matches("(?i)class")) {
                return CLASS;
            } else {
                return null;
            }
        }

    }

    public static final String COMMAND_WORD = "add";

    //improve in future
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds either a student, a tutor or a class to the database.\n"
            + "Parameters:"
            + "student "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SCHOOL + "SCHOOL "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_NEXTOFKIN + "NEXTOFKIN "
            + "[" + PREFIX_TAG + "TAG]... OR\n"
            + "tutor "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_QUALIFICATION + "QUALIFICATION "
            + PREFIX_INSTITUTION + "INSTITUTION "
            + "[" + PREFIX_TAG + "TAG]... OR\n"
            + "class "
            + PREFIX_NAME + "NAME "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "TIME "
            + "[" + PREFIX_TAG + "TAG]... OR\n"
            + "Example: " + COMMAND_WORD + " "
            + "student "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SCHOOL + "Keming Primary School "
            + PREFIX_LEVEL + "Primary3 "
            + PREFIX_NEXTOFKIN + "Mom Doe "
            + PREFIX_TAG + "badBoy "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New entity added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student or tutor already exists in the database";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists in the database";

    private Person personToAdd;
    private TuitionClass classToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    private AddCommand() {
        this.personToAdd = null;
        this.classToAdd = null;
    }

    /**
     * Creates and returns a {@code AddCommand} to add the specified {@code Person}
     */
    public static AddCommand of(Person person) {
        requireNonNull(person);
        AddCommand addCommand = new AddCommand();
        addCommand.personToAdd = person;
        return addCommand;
    }


    /**
     * Creates and returns a {@code AddCommand} to add the specified {@code TuitionClass}
     */
    public static AddCommand of(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        AddCommand addCommand = new AddCommand();
        addCommand.classToAdd = tuitionClass;
        return addCommand;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.classToAdd == null) {
            requireNonNull(this.personToAdd);
            if (model.hasPerson(this.personToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
            model.addPerson(this.personToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.personToAdd));
        } else {
            requireNonNull(this.classToAdd);
            if (model.hasTuitionClass(this.classToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_CLASS);
            }
            model.addTuitionClass(this.classToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.classToAdd));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && (Objects.equals(this.personToAdd, ((AddCommand) other).personToAdd))
                && (Objects.equals(this.classToAdd, ((AddCommand) other).classToAdd)));
    }
}
