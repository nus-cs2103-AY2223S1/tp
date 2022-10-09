package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchKeywordsPredicate;

/**
 * Filters and lists all persons in address book whose attributes are equal to any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FilterCommand extends ModelCommand {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": filters the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ROOM + "FLOOR-UNIT] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_HOUSE + "HOUSE] "
            + "[" + PREFIX_MATRIC_NUMBER + "MATRIC_NUMBER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_FILTER_PERSON_SUCCESS = "filtered Person: %1$s";
    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the resident book.";

    private final ResidentDescriptor filterPersonDescriptor;

    /**
     * @param filterPersonDescriptor details to filter the person with
     */
    public FilterCommand(ResidentDescriptor filterPersonDescriptor) {
        requireNonNull(filterPersonDescriptor);
        this.filterPersonDescriptor = new ResidentDescriptor(filterPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        //return new CommandResult("Command Still in progress");
        requireNonNull(model);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(filterPersonDescriptor);
        model.updateFilteredResidentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_RESIDENTS_LISTED_OVERVIEW, model.getFilteredResidentList().size()));
    }

}
