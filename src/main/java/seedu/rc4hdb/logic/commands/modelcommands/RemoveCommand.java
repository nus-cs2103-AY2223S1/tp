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

import java.util.List;
import java.util.function.Predicate;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.parser.FilterSpecifier;
import seedu.rc4hdb.logic.parser.ParserUtil;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchAllKeywordsPredicate;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchAnyKeywordPredicate;

/**
 * Filters and lists all residents in resident book whose attributes are equal to any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class RemoveCommand implements ModelCommand {
    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": "
            + "removes the resident book by the attributes specified in the command"
            + "Parameters:"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ROOM + "FLOOR-UNIT] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_HOUSE + "HOUSE] "
            + "[" + PREFIX_MATRIC_NUMBER + "MATRIC_NUMBER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";

    /** description to remove the residents with */
    private final ResidentStringDescriptor removePersonDescriptor;
    private final FilterSpecifier specifier;

    /**
     * @param removePersonDescriptor description object to remove the resident with
     */
    public RemoveCommand(ResidentStringDescriptor removePersonDescriptor, FilterSpecifier specifier) {
        assert removePersonDescriptor != null : "Descriptor object is null";
        this.removePersonDescriptor = new ResidentStringDescriptor(removePersonDescriptor);
        this.specifier = specifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model object is null";

        requireNonNull(model);
        List<Resident> lastShownList = model.getFilteredResidentList();
        Predicate<Resident> predicate;
        if (specifier.getSpecifier() == "any") {
            predicate = new AttributesMatchAnyKeywordPredicate(removePersonDescriptor);
        } else {
            predicate = new AttributesMatchAllKeywordsPredicate(removePersonDescriptor);
        }

        for (int index = 0; index < lastShownList.size(); index++) {

            Resident residentToDelete = lastShownList.get(index);
            if (predicate.test(residentToDelete)) {
                model.deleteResident(residentToDelete);
            }
        }
            return new CommandResult(String.format(String.format(Messages.MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1)));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveCommand)) {
            return false;
        }

        // state check
        RemoveCommand r = (RemoveCommand) other;
        return removePersonDescriptor.equals(r.removePersonDescriptor);
    }
}
