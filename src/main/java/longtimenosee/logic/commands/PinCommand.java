package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import longtimenosee.commons.core.Messages;
import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.person.Person;


/**
 * Pin command, used to pin.
 */
public class PinCommand extends Command {

    public static final String COMMAND_WORD = "pin";

    public static final String MESSAGE_USAGE = "To use " + COMMAND_WORD
            + ":\nindex should be indices shown on currently viewed list";

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

        Person personToPin = lastShownList.get(targetIndex.getZeroBased());
        personToPin.setPin(!personToPin.getPin());
        return new CommandResult(String.format(personToPin.getPin()
                ? MESSAGE_PIN_PERSON_SUCCESS : MESSAGE_UNPIN_PERSON_SUCCESS , personToPin), false, true, false);
    }

}
