package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.travelr.model.trip.TripComparators.COMPARE_BY_COMPLETION;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.trip.Trip;

/**
 * Adds a Trip to Travelr.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a trip to Travelr. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESC + "DESCRIPTION "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_DATE + "{dd-mm-yyyy} \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Honeymoon "
            + PREFIX_DESC + "Going on a honeymoon with my prof "
            + PREFIX_LOCATION + "Jeju Island "
            + PREFIX_DATE + "26-12-2023";

    public static final String MESSAGE_SUCCESS = "New trip added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRIP = "This trip already exists in Travelr";

    private final Trip toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public AddCommand(Trip trip) {
        requireNonNull(trip);
        toAdd = trip;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTrip(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRIP);
        }

        model.addTrip(toAdd);
        model.sortTripsByComparator(COMPARE_BY_COMPLETION);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
