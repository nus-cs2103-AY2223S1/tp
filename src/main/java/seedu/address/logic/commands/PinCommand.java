package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Pin command, used to pin.
 */
public class PinCommand extends Command {

    public static final String COMMAND_WORD = "pin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": pins client to a list which can be viewed separately";

    public static final String MESSAGE_PIN_PERSON_SUCCESS = "Pinned Client: %1$s";

    public static final String MESSAGE_UNPIN_PERSON_SUCCESS = "Unpinned Client: %1$s";
    private final Index targetIndex;

    public PinCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToPin = lastShownList.get(targetIndex.getZeroBased()); //gets the person to be pinned
        personToPin.setPin(!personToPin.getPin()); //set to pin or not
        //model.deletePerson(personToPin); optional because we don't alter the list
        return new CommandResult(String.format(personToPin.getPin()
                ? MESSAGE_PIN_PERSON_SUCCESS : MESSAGE_UNPIN_PERSON_SUCCESS , personToPin), false, true);
    }

}
