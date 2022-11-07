package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADUATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.ListOfContainsKeywordsPredicates;
import seedu.address.storage.Storage;

/**
 * Finds and lists all persons in address book whose field(s) contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose field(s) contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: specifier/SPECIFIER_KEYWORDS [more_specifier/MORE_KEYWORDS]...\n"
            + "Possible specifier/SPECIFIER_KEYWORDS:\n"
            + "[" + PREFIX_NAME + "NAME_KEYWORDS] "
            + "[" + PREFIX_PHONE + "PHONE_KEYWORDS] "
            + "[" + PREFIX_EMAIL + "EMAIL_KEYWORDS] "
            + "[" + PREFIX_ADDRESS + "ADDRESS_KEYWORDS] "
            + "[" + PREFIX_GENDER + "GENDER_KEYWORDS] "
            + "[" + PREFIX_GRADUATION_DATE + "GRADUATION_DATE_KEYWORDS] "
            + "[" + PREFIX_CAP + "CAP_VALUE_KEYWORDS]"
            + "[" + PREFIX_UNIVERSITY + "UNIVERSITY_KEYWORDS] "
            + "[" + PREFIX_MAJOR + "MAJOR_KEYWORDS] "
            + "[" + PREFIX_JOB_ID + "JOB_ID_KEYWORDS] "
            + "[" + PREFIX_JOB_TITLE + "JOB_TITLE_KEYWORDS] "
            + "[" + PREFIX_TAG + "TAG_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GENDER + "Male "
            + PREFIX_CAP + "3.5 5 "
            + PREFIX_TAG + "offered KIV ";
    public static final String MESSAGE_NO_FIELD_GIVEN = "At least one field specifier to search into must be provided.";

    private final ListOfContainsKeywordsPredicates predicates;

    public FindCommand(ListOfContainsKeywordsPredicates predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicates.getChainedPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
