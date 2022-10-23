package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Filters contacts in address book whose name contains the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";
    public static final String COMMAND_WORD_ALIAS = "f";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters for contacts with the specified name and tags (both case-insensitive) \n"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_NAME + "NAME1,NAME2,...] [" + PREFIX_TAG + "TAG1,TAG2,...] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice";

    protected final FilterCommandPredicate predicate;

    /**
     * Creates a {@code FilterCommand} object for filtering.
     *
     * @param predicate {@code FilterCommandPredicate} to use for filtering.
     */
    public FilterCommand(FilterCommandPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    protected FilterCommand() {
        this.predicate = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(predicate);
        requireTagExists(model, predicate);
        applySpecifiedFilters(model);
        return new CommandResult(getListOverviewAsString(model) + "\n" + getFiltersAppliedAsString(model));
    }

    protected void requireTagExists(Model model, FilterCommandPredicate predicate) throws CommandException {
        if (predicate == null || predicate.getTagPredicate() == null) {
            return;
        }
        List<Tag> tagsNotInModel = predicate.getTagPredicate().stream()
                .map(tagPred -> tagPred.getTag())
                .filter(tag -> !model.hasTag(tag))
                .collect(Collectors.toList());
        if (!tagsNotInModel.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_TAGS_NOT_FOUND, Tag.toString(tagsNotInModel)));
        }
    }

    private void applySpecifiedFilters(Model model) {
        assert isAnyNonNull(predicate.getNamePredicate(), predicate.getTagPredicate());
        model.addNewFilterToFilteredPersonList(predicate);
    }

    protected String getListOverviewAsString(Model model) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size());
    }

    protected String getFiltersAppliedAsString(Model model) {
        List<String> names = model.getNameFilters().stream().map(pred -> pred.toString()).collect(Collectors.toList());
        List<String> tags = model.getTagFilters().stream().map(pred -> pred.toString()).collect(Collectors.toList());
        String namesString = names.size() > 0 ? "Name filters: " + String.join(", ", names) + "\n" : "";
        String tagsString = tags.size() > 0 ? "Tag filters: " + String.join(", ", tags) : "";
        return namesString + tagsString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                        && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
