package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Sort all persons in the address book by
 * name in lexicographical order
 */
public class SortLexicographicalCommand extends Command {

    public static final String COMMAND_WORD = "sort-lexi";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the list of contact in a "
            + "lexicographical manner by name.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Contacts sorted"
            + " lexicographically by name";


    @Override
    public CommandResult execute(Model model) {
        model.sortContactLexicographical();
        return new CommandResult(MESSAGE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof SortLexicographicalCommand;
    }
}
