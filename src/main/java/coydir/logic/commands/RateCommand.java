package coydir.logic.commands;

import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_RATE;
import static java.util.Objects.requireNonNull;

import java.util.List;

import coydir.commons.core.Messages;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;
import coydir.model.person.Rating;

/**
 * Rate an employee's current performance.
 */
public class RateCommand extends Command {

    public static final String COMMAND_WORD = "rate";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rate the current performance of an employee.\n"
            + "5: Outstanding | 4: Exceeds Expectations | 3: Satisfactory | 2: Needs Improvement | 1: Unsatisfactory\n"
            + "Parameters: "
            + PREFIX_ID + "ID "
            + PREFIX_RATE + "RATING "
            + " | Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "1 "
            + PREFIX_RATE + "3 ";

    public static final String MESSAGE_RATE_SUCCESS = "Performance successfully rated for %1$s";
    public static final String MESSAGE_EMPLOYEE_RATING_COMPLETED =
        "This employee's performance for the day has been rated.";

    private EmployeeId targetId;
    private Rating rating;

    /**
     * Creates a RateCommand object.
     * @param targetid of the employee to rate.
     * @param rating of the employee to add.
     */
    public RateCommand(EmployeeId targetid, Rating rating) {
        this.targetId = targetid;
        this.rating = rating;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person targetPerson = null;
        for (Person person : lastShownList) {
            if (person.getEmployeeId().equals(targetId)) {
                targetPerson = person;
                break;
            }
        }
        if (targetPerson == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        for (Rating ratinglist : targetPerson.getRatingHistory()) {
            if (ratinglist.getTime().equals(rating.getTime())) {
                throw new CommandException(MESSAGE_EMPLOYEE_RATING_COMPLETED);
            }
        }

        targetPerson.setRating(rating);
        targetPerson.addRating(rating);
        return new CommandResult(String.format(MESSAGE_RATE_SUCCESS, targetPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RateCommand // instanceof handles nulls
                && targetId.equals(((RateCommand) other).targetId)
                && rating.equals(((RateCommand) other).rating)); // state check
    }

}
