package seedu.address.logic.commands;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.tag.Tag;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    private final Optional<Address> address;
    private final Optional<String> category;
    private final Optional<Gender> gender;
    private final Optional<Tag> tag;

    public static final String MESSAGE_ARGUMENTS = "CATEGORY: %s, GENDER: %s, ADDRESS: %s, TAG: %s";

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all enrolled users who fit the specified criteria, "
            + "or all enrolled users if no criteria were specified.\n"
            + "Parameters: \n"
            + "<optional> c/ [CATEGORY]\n"
            + "<optional> g/ [GENDER]\n"
            + "<optional> a/ [ADDRESS]\n"
            + "<optional> t/ [TAG]\n"
            + "Example: " + COMMAND_WORD + "  "
            + "c/ nurse";

    /**
     * @param a address to be filtered
     * @param c category (nurse/patient) to be filtered
     * @param g gender to be filtered
     * @param t tag to be filtered
     */
    public ListCommand(Optional<Address> a, Optional<String> c, Optional<Gender> g, Optional<Tag> t) {
        address = a;
        category = c;
        gender = g;
        tag = t;
    }

    @Override
    public CommandResult execute(Model model) {
        /*
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
         */

        //return new CommandResult(MESSAGE_SUCCESS);
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS,
                        address.orElse(new Address("NIL")).value,
                        category.orElse("NIL"),
                        gender.orElse(new Gender("NIL")).value,
                        tag.orElse(new Tag("NIL")).tagName)
        );
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        // state check
        ListCommand e = (ListCommand) other;
        return address.equals(e.address)
                && category.equals(e.category)
                && gender.equals(e.gender)
                && tag.equals(e.tag);
    }
}
