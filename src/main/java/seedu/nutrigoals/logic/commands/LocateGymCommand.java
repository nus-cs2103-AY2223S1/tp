package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Location;
import seedu.nutrigoals.model.Model;

/**
 * Finds the closest 3 gyms to a faculty in NUS.
 */
public class LocateGymCommand extends Command {
    public static final String COMMAND_WORD = "locate";

    public static final String MESSAGE_LOCATE_SUCCESS = "Gyms (closest to furthest): \n%s";
    public final Location currentLocation =
        // new Location("RVRC", "1.2979296797732855, 103.77681006058592"); // RVRC
        new Location("RC4", "1.308245523260011, 103.77340430257533"); // RC4
    private final ArrayList<Location> locations = new ArrayList<>();
    {
        locations.add(new Location("MPSH", "1.3007599674153045, 103.77578206094384")); // MPSH 5
        locations.add(new Location(
            "STEPHEN RIADY CENTRE", "1.304511666901411, 103.77205745840185")); // stephen riady centre
        locations.add(new Location("USC", "1.2998680145010344, 103.77528575803385")); // USC
    }

    // dummy constructor TO REMOVE
    public LocateGymCommand() {
    }
    /**
     * @param location Location
     */
    public LocateGymCommand(String locationName) {
        
    }
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        locations.sort((l1, l2) -> l1.distTo(currentLocation).compareTo(l2.distTo(currentLocation)));
        String sortedGyms = "";
        for (int i = 0; i < locations.size(); i++) {
            sortedGyms += (i + 1) + ": " + locations.get(i).toString() + "\n";
        }
        return new CommandResult(String.format(MESSAGE_LOCATE_SUCCESS, sortedGyms));
    }
}
