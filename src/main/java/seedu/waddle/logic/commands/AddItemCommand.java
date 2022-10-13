package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Itinerary;


/**
 * Adds an item to an itinerary.
 */
public class AddItemCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to an itinerary. "
            + "Parameters: "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Visit Taj Mahal ";

    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists";

    private final Item toAdd;

    /**
     * Creates an AddItemCommand to add the specified {@code Item}
     */
    public AddItemCommand(Item item) {
        requireNonNull(item);
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StageManager stageManager = StageManager.getInstance();

        // if not at wish stage, throw exception and change to wish stage
        /*
        if (!stageManager.isCurrentStage(Stages.WISH)) {
            return new CommandResult(MESSAGE_WRONG_STAGE);
        }

        stageManager.setHomeStage();
         */
        Itinerary itinerary = stageManager.getSelectedItinerary();

        if (itinerary.hasItem(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        itinerary.addItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getDescription()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand // instanceof handles nulls
                && toAdd.equals(((AddItemCommand) other).toAdd));
    }
}
