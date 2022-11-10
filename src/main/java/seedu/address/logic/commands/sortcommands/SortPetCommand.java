package seedu.address.logic.commands.sortcommands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;

/**
 * Sorts the Pets' list.
 */
public class SortPetCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS = "pet list has been sorted successfully";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting pet list \n%2$s";
    public static final String MESSAGE_USAGE =
            "Acceptable order attributes are name, color, colorpattern, birthdate, species, price";

    private final Comparator<Pet> comparator;

    /**
     * Constructs a SortPetCommand with specified comparator.
     * @param comparator The specified comparator.
     */
    public SortPetCommand(Comparator<Pet> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortPet(comparator);
        model.switchToPetList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
