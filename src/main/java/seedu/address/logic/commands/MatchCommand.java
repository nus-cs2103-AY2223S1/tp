package seedu.address.logic.commands;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetGrader;

/**
 * Matches pets given an order.
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

    private final Index index;

    /**
     * Constructs a MatchCommand object.
     * @param index The index of the order that needs to be matched to pets.
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
        ObservableList<Order> orderList = model.getFilteredOrderList();
        ObservableList<Pet> petList = model.getFilteredPetList();

        if (index.getZeroBased() >= orderList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Order order = orderList.get(index.getZeroBased());
        PetGrader grader = new PetGrader(order);
        Map<Pet, Double> petScoreMap = new HashMap<>();
        petList.forEach(x -> petScoreMap.put(x, grader.evaluate(x)));
        Comparator<Pet> comparator = (x, y) -> Double.compare(petScoreMap.getOrDefault(y, 0.0),
                        petScoreMap.getOrDefault(x, 0.0));
        model.sortPet(comparator);

        return new CommandResult(MESSAGE_SUCCESS,
                false,
                false,
                true,
                ListCommand.LIST_PET,
                false,
                null,
                false,
                null,
                null);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchCommand // instanceof handles nulls
                && index.equals(((MatchCommand) other).index)); // state check
    }
}
