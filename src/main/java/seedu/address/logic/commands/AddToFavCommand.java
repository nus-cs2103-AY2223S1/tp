package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * Adds an existing person to the favorite group.
 */
public class AddToFavCommand extends Command {

    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the person to the 'favorite' group.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in 'favorite'";
    public static final String MESSAGE_FAV_PERSON_SUCCESS = "Person added to successfully to favorite!";

    private final Index index;
    private final Group favorite;

    /**
     * Constructor for AddToFavCommand.
     *
     * @param index     person to be added in the group
     * @param favorite  'favorite' group the person will be added into.
     */
    public AddToFavCommand(Index index, Group favorite) {
        requireNonNull(index);
        this.index = index;
        this.favorite = favorite;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AddToGroupCommand addToGroupCommand = new AddToGroupCommand(index, favorite);
        Person personToGroup = model.getFilteredPersonList().get(index.getZeroBased());
        Person groupedPerson = addToGroupCommand.getGroupedPerson(personToGroup, favorite);

        UndoCommand.prepareSaveModelBefore(this, model);
        model.setPerson(personToGroup, groupedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        UndoCommand.saveBeforeMod(model);

        return new CommandResult(String.format(MESSAGE_FAV_PERSON_SUCCESS, groupedPerson));

    }

}
