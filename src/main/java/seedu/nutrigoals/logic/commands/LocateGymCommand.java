package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Location;
import seedu.nutrigoals.model.Model;

/**
 * Finds the closest 3 gyms to a faculty in NUS.
 */
public class LocateGymCommand extends Command {
    public static final String COMMAND_WORD = "locate";
    // Remember to update the User guide
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds the nearest gym to your stated location\n"
        + "Parameters: COM2 | S13 | S17 | CLB | UHC | LT1 | LT9 | AS6\n"
        + "Example: " + COMMAND_WORD + " CLB";
    public static final String MESSAGE_LOCATE_SUCCESS = "Gyms (closest to furthest): \n%s";
    public final Location currentLocation;

    public LocateGymCommand(Location currentLocation) {
        this.currentLocation = currentLocation;
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
        List<Location> nusGymLocations = model.getNusGymLocations();
        nusGymLocations.sort((l1, l2) -> l1.distTo(currentLocation).compareTo(l2.distTo(currentLocation)));
        StringBuilder sortedGyms = new StringBuilder();
        for (int i = 0; i < Math.min(nusGymLocations.size(), 10); i++) {
            sortedGyms.append(i + 1).append(": ").append(nusGymLocations.get(i).toString()).append("\n");
        }
        return new CommandResult(String.format(MESSAGE_LOCATE_SUCCESS, sortedGyms));
    }
}
