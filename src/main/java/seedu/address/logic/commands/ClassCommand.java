package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IsPartOfClassPredicate;
import seedu.address.model.tag.Tag;

/**
 * Enters focus mode for the specified class
 * TODO: replace tag with class
 */
public class ClassCommand extends Command {

    public static final String COMMAND_WORD = "class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Enters focus mode for the specified class.\n"
            + "Parameters: CLASS_NAME\n"
            + "Example: " + COMMAND_WORD + " CS1101S";

    private final Tag targetClass;

    /**
     * Creates an ClassCommand that enters focus mode for the specified {@code Tag}.
     */
    public ClassCommand(Tag targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        IsPartOfClassPredicate predicate = new IsPartOfClassPredicate(targetClass);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format(
                Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
