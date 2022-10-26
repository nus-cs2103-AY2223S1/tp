package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.pet.Pet;

/**
 * Matches pets given an order.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Matches pets given an order, based on how pets fit the request."
            + "Parameters: "
            + "match " + PREFIX_INDEX + " INDEX\n"
            + "Example: "
            + "match " + PREFIX_INDEX + " 1\n";

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

        if (index.getZeroBased() >= orderList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Order order = orderList.get(index.getZeroBased());
        Map<Pet, Double> petScoreMap = new HashMap<>();


        return null;
    }
}
