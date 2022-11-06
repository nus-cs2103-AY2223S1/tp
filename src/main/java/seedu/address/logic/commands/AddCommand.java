package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandResult.CommandType.ADDSTUDENT;
import static seedu.address.logic.commands.CommandResult.CommandType.ADDTUTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;
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

        public static final String MESSAGE_CONSTRAINTS =
                "Entity type should be one of the valid entity types that can be added to the database.\n"
                        + "The valid entity types are:\n"
                        + "1. Student\n"
                        + "2. Tutor\n"
                        + "3. Class\n";
        public static final String MESSAGE_DID_YOU_MEAN_STUDENT = "Did you mean \"student\"?";
        public static final String MESSAGE_DID_YOU_MEAN_TUTOR = "Did you mean \"tutor\"?";
        public static final String MESSAGE_DID_YOU_MEAN_CLASS = "Did you mean \"class\"?";

        public static final String VALIDATION_REGEX = "(?i)s|t|c|student|tutor|class";

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
    public static final String FEEDBACK_MESSAGE = "Valid add command format:\n"
            + "add student "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SUBJECT_OR_SCHOOL + "SCHOOL "
            + PREFIX_LEVEL + "LEVEL "
            + "[" + PREFIX_TAG + "TAG]... OR\n"
            + "add tutor "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_QUALIFICATION + "QUALIFICATION "
            + PREFIX_INSTITUTION + "INSTITUTION "
            + "[" + PREFIX_TAG + "TAG]... OR\n"
            + "add class "
            + PREFIX_NAME + "NAME "
            + PREFIX_SUBJECT_OR_SCHOOL + "SUBJECT "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "TIME "
            + "[" + PREFIX_TAG + "TAG]... \n";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds either a student, a tutor or a class to the database.\n"
            + "\nParameters:\n"
            + "student "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SUBJECT_OR_SCHOOL + "SCHOOL "
            + PREFIX_LEVEL + "LEVEL "
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
            + PREFIX_SUBJECT_OR_SCHOOL + "SUBJECT "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "TIME "
            + "[" + PREFIX_TAG + "TAG]... \n"
            + "\nExample: \n" + COMMAND_WORD + " "
            + "student "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 \n"
            + PREFIX_SUBJECT_OR_SCHOOL + "Keming Primary School "
            + PREFIX_LEVEL + "Primary3 "
            + PREFIX_TAG + "badBoy "
            + PREFIX_TAG + "owesMoney";;

    public static final String HELP_MESSAGE = COMMAND_WORD
            + " - Adds either a student, a tutor or a class to the database.\n"
            + "\n"
            + "Usage:\n"
            + "add <entity> <args>\n"
            + "\n"
            + "Description:\n"
            + "Depending on the entity type specified to be added, a specific set of arguments must be included.\n"
            + "The respective prefixes must be prepended to each argument except the entity type.\n "
            + "To add a student, follow 'add student " + PREFIX_NAME + "<name> " + PREFIX_PHONE + "<phone> "
            + PREFIX_EMAIL + "<email> " + PREFIX_ADDRESS + "<address> " + PREFIX_SUBJECT_OR_SCHOOL + "<school> "
            + PREFIX_LEVEL
            + "<level> [" + PREFIX_TAG + "<tag>]'.\n"
            + "To add a tutor, follow 'add student " + PREFIX_NAME + "<name> " + PREFIX_PHONE + "<phone> "
            + PREFIX_EMAIL + "<email> "
            + PREFIX_ADDRESS + "<address> " + PREFIX_QUALIFICATION + "<qualification> " + PREFIX_INSTITUTION
            + "<institution> [" + PREFIX_TAG
            + "<tag>]'.\n"
            + "To add a class, follow 'add class " + PREFIX_NAME + "<name> " + PREFIX_SUBJECT_OR_SCHOOL + "<subject> "
            + PREFIX_LEVEL + "<level> "
            + PREFIX_DAY + "<day> " + PREFIX_TIME + "<time> [" + PREFIX_TAG + "<tag>]'. \n"
            + "\n"
            + "Regarding prefixes,\n"
            + "Name - " + Name.MESSAGE_CONSTRAINTS
            + "Phone - " + Phone.MESSAGE_CONSTRAINTS
            + "Email - " + Email.MESSAGE_CONSTRAINTS
            + "Address - " + Address.MESSAGE_CONSTRAINTS
            + "School - " + School.MESSAGE_CONSTRAINTS
            + "Level - " + Level.MESSAGE_CONSTRAINTS
            + "Qualification - " + Qualification.MESSAGE_CONSTRAINTS
            + "Institution - " + Institution.MESSAGE_CONSTRAINTS
            + "Subject - " + Subject.MESSAGE_CONSTRAINTS
            + "Day - " + Day.MESSAGE_CONSTRAINTS
            + "Time - " + Time.MESSAGE_CONSTRAINTS
            + "Tag - " + Tag.MESSAGE_CONSTRAINTS
            + "\n"
            + "Example:\n"
            + "1." + COMMAND_WORD + " "
            + "student "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SUBJECT_OR_SCHOOL + "Keming Primary School "
            + PREFIX_LEVEL + "Primary 3 "
            + PREFIX_TAG + "badBoy "
            + PREFIX_TAG + "owesMoney\n"
            + "2." + COMMAND_WORD + " "
            + "tutor "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_QUALIFICATION + "MSc, Master of Science "
            + PREFIX_INSTITUTION + "National University of Singapore "
            + PREFIX_TAG + "hardWorker\n"
            + "3." + COMMAND_WORD + " "
            + "class "
            + PREFIX_NAME + "Class A1 "
            + PREFIX_SUBJECT_OR_SCHOOL + "Math "
            + PREFIX_LEVEL + "Primary 3 "
            + PREFIX_DAY + "Monday "
            + PREFIX_TIME + "12:00 - 14:00 "
            + PREFIX_TAG + "fullClass\n";



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
            assert (!(this.personToAdd instanceof NextOfKin));
            requireNonNull(this.personToAdd);
            if (model.hasPerson(this.personToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }
            model.addPerson(this.personToAdd);
            if (this.personToAdd instanceof Student) {
                return new CommandResult(String.format(MESSAGE_SUCCESS, this.personToAdd), ADDSTUDENT);
            } else {
                assert (this.personToAdd instanceof Tutor);
                return new CommandResult(String.format(MESSAGE_SUCCESS, this.personToAdd), ADDTUTOR);
            }
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
