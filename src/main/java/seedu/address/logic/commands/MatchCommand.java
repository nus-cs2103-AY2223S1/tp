package seedu.address.logic.commands;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetGrader;

/**
 * Matches Pets given an Order.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Matches pets given an order, based on how pets fit the request."
            + "Parameters: "
            + "match " + " INDEX\n"
            + "Example: "
            + "match " + " 1\n";
    public static final String MESSAGE_SUCCESS = "Matched pets given the order. ";

    private static final Logger LOGGER = LogsCenter.getLogger(MatchCommand.class);
    private final Index index;

    /**
     * Constructs a MatchCommand object.
     * @param index The index of the Order that needs to be matched to Pets.
     */
    public MatchCommand(Index index) {
        this.index = index;
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
        ObservableList<Object> currList = model.getFilteredCurrList();
        ObservableList<Pet> petList = model.getFilteredPetList();

        if (index.getZeroBased() >= currList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Object o = currList.get(index.getZeroBased());
        if (!(o instanceof Order)) {
            throw new CommandException(String.format(Messages.INVALID_ORDER, index.getOneBased()));
        }

        Order order = (Order) o;

        PetGrader grader = new PetGrader(order);

        Map<Pet, Double> petScoreMap = new HashMap<>();
        petList.forEach(x -> petScoreMap.put(x, grader.evaluate(x)));
        Comparator<Pet> comparator = (x, y) -> {
            if (!petScoreMap.containsKey(y)) {
                LOGGER.warning(y.getName() + "'s score is not in the map.");
            }
            if (!petScoreMap.containsKey(x)) {
                LOGGER.warning(x.getName() + "'s score is not in the map.");
            }
            return Double.compare(petScoreMap.getOrDefault(y, 0.0),
                    petScoreMap.getOrDefault(x, 0.0));
        };

        model.sortPet(comparator);
        model.switchToPetList();

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchCommand // instanceof handles nulls
                && index.equals(((MatchCommand) other).index)); // state check
    }
}
