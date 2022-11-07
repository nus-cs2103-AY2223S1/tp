package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Provides the user with a template with all the fields for a particular Person
 */
public class TemplateCommand extends Command {

    public static final String COMMAND_WORD = "tt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": copies a template of prof/ta/student into "
        + "clipboard.";

    public static final String MESSAGE_CONSTRAINTS = "tt command can only be called on 'prof', 'student',"
        + "or 'ta' only.";

    public static final String SHOWING_HELP_MESSAGE = "Pasted template onto screen! Press tab once then left arrow, "
        + "to quickly access it";

    private final String personChosen;

    /**
     * Creates a TemplateCommand object
     * @param personChosen the command word of a Person.
     */
    public TemplateCommand(String personChosen) {
        requireNonNull(personChosen);
        this.personChosen = personChosen;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (personChosen.equals(ProfCommand.COMMAND_WORD)) {
            return new CommandResult(SHOWING_HELP_MESSAGE, ProfCommand.PROF_TEMPLATE);
        }

        if (personChosen.equals(StudentCommand.COMMAND_WORD)) {
            return new CommandResult(SHOWING_HELP_MESSAGE, StudentCommand.STUDENT_TEMPLATE);
        }

        if (personChosen.equals(TaCommand.COMMAND_WORD)) {
            return new CommandResult(SHOWING_HELP_MESSAGE, TaCommand.TA_TEMPLATE);
        }
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }

    /**
     * @param person The string representing the COMMAND_WORD of the Person chosen
     * @return true if it is a valid COMMAND_WORD of a Person, false otherwise.
     */
    public static boolean isValidPerson(String person) {
        if (person.equals(ProfCommand.COMMAND_WORD)) {
            return true;
        } else if (person.equals(StudentCommand.COMMAND_WORD)) {
            return true;
        } else {
            return person.equals(TaCommand.COMMAND_WORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TemplateCommand // instanceof handles nulls
            && personChosen.equals(((TemplateCommand) other).personChosen));
    }

}
