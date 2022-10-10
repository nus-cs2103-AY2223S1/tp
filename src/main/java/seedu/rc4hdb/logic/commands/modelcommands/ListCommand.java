package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.person.Fields;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends ModelCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String SHOW_ONLY_SPECIFIED = "Listed all persons. Only specified fields are shown.";

    private final List<String> fieldList;

    /**
     * Constructor for a ListCommand instance.
     */
    public ListCommand() {
        // Set the required list of fields to the global field list
        this.fieldList = Fields.FIELDS;
    }

    /**
     * Constructor for a ListCommand instance.
     * @param fieldList The fields to be hidden when listing the data
     */
    public ListCommand(List<String> fieldList) {
        requireNonNull(fieldList);
        this.fieldList = fieldList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.setObservableFields(fieldList);

        // Determine which ListCommand constructor was invoked
        if (fieldList == Fields.FIELDS) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(SHOW_ONLY_SPECIFIED);
        }
    }
}
