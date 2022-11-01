package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_CUISINE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_LOCATION;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_NAME;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_PRICE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.Eatery;

/**
 * Adds an eatery to the food guide.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an eatery to the food guide. "
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + PREFIX_PRICE + " PRICE "
            + PREFIX_CUISINE + " CUISINE "
            + PREFIX_LOCATION + " LOCATION "
            + "[" + PREFIX_TAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " Starbucks "
            + PREFIX_PRICE + " $$$ "
            + PREFIX_CUISINE + " cafe "
            + PREFIX_LOCATION + " Science Block S9 "
            + PREFIX_TAG + " coffee ";

    public static final String MESSAGE_SUCCESS = "New eatery added: %1$s";
    public static final String MESSAGE_DUPLICATE_EATERY = "This eatery already exists in the food guide";

    private final Eatery toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Eatery}
     */
    public AddCommand(Eatery eatery) {
        requireNonNull(eatery);
        toAdd = eatery;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEatery(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EATERY);
        }

        model.addEatery(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
