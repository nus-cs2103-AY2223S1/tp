package seedu.rc4hdb.logic.commands.residentcommands;

import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_FILTER_ALL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.parser.Specifier;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchAllKeywordsPredicate;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchAnyKeywordPredicate;

/**
 * Filters and lists all residents in resident book whose attributes are equal to any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FilterCommand implements ModelCommand {
    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": "
            + "filters the resident book by the attributes specified in the command"
            + "Parameters: "
            + "[Specifier (/any or /all)] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ROOM + "FLOOR-UNIT] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_HOUSE + "HOUSE] "
            + "[" + PREFIX_MATRIC_NUMBER + "MATRIC_NUMBER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FILTER_ALL + " "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";

    /** description to filter the resident with */
    private final ResidentStringDescriptor filterPersonDescriptor;

    private final Specifier specifier;

    /**
     * @param filterPersonDescriptor description object to filter the resident with
     */
    public FilterCommand(ResidentStringDescriptor filterPersonDescriptor, Specifier specifier) {
        assert filterPersonDescriptor != null : "Descriptor object is null";
        this.filterPersonDescriptor = new ResidentStringDescriptor(filterPersonDescriptor);
        this.specifier = specifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model object is null";
        Predicate<Resident> predicate;
        if (specifier.getSpecifier().equals("any")) {
            predicate = new AttributesMatchAnyKeywordPredicate(filterPersonDescriptor);
        } else {
            predicate = new AttributesMatchAllKeywordsPredicate(filterPersonDescriptor);
        }
        model.updateFilteredResidentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_RESIDENTS_LISTED_OVERVIEW, model.getFilteredResidentList().size()));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand f = (FilterCommand) other;
        return filterPersonDescriptor.equals(f.filterPersonDescriptor);
    }
}
