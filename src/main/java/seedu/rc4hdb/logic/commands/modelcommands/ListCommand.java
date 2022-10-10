package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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

    public ListCommand() {
        // Set the required list of fields to the global field list
        this.fieldList = Fields.fields;
    }

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
        if (fieldList == Fields.fields) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(SHOW_ONLY_SPECIFIED);
        }
    }
}
