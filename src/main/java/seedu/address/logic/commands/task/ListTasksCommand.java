package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AFTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEFORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.AssignedToContactsPredicate;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.DeadlineIsAfterPredicate;
import seedu.address.model.task.DeadlineIsBeforePredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TitleContainsKeywordPredicate;

/**
 * Lists all tasks in the task panel to the user that match the specified requirements.
 */
public class ListTasksCommand extends TaskCommand {

    public static final String ALL_FLAG = "a";
    public static final String COMPLETED_FLAG = "c";

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Lists all tasks that satisfy the specified requirements.\n"
            + "Parameters: "
            + "[" + "KEYWORD]"
            + "[" + PREFIX_CONTACT + "CONTACT_INDEX]...\n"
            + "[" + PREFIX_BEFORE + "DATE]...\n"
            + "[" + PREFIX_AFTER + "DATE]...\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + "fix "
            + PREFIX_CONTACT + "1 ";

    public static final String MESSAGE_SUCCESS = "Showing all %1$s";

    private String keywordFilter;
    private Optional<Deadline> beforeArgs;
    private Optional<Deadline> afterArgs;
    private List<String> flags;
    private final Set<Index> personIndexes = new HashSet<>();

    /**
     * Creates a ListTasksCommand to list the {@code Task}s with the specified filters
     * @param personsIndexes a set of indexes to view only tasks assigned to the corresponding contacts
     */
    public ListTasksCommand(String keywordFilter,
                            List<String> flags,
                            Optional<Deadline> beforeArgs,
                            Optional<Deadline> afterArgs,
                            Set<Index> personsIndexes) {
        requireAllNonNull(flags, personsIndexes);
        this.keywordFilter = keywordFilter;
        this.flags = flags;
        this.beforeArgs = beforeArgs;
        this.afterArgs = afterArgs;
        this.personIndexes.addAll(personsIndexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Predicate<Task> filter;

        if (flags.contains(COMPLETED_FLAG)) {
            filter = Model.PREDICATE_COMPLETED_TASKS;
        } else if (flags.contains(ALL_FLAG)) {
            filter = Model.PREDICATE_SHOW_ALL_TASKS;
        } else {
            filter = Model.PREDICATE_INCOMPLETE_TASKS;
        }

        filter = filter.and(new TitleContainsKeywordPredicate(keywordFilter));
        filter = filter.and(parseDeadlineArgs());
        filter = filter.and(new AssignedToContactsPredicate(model, personIndexes));

        model.updateFilteredTaskList(filter);

        return new CommandResult(getSuccessMessage(model));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListTasksCommand)) {
            return false;
        }

        // state check
        ListTasksCommand e = (ListTasksCommand) other;
        return keywordFilter.equals(e.keywordFilter)
                && flags.equals(e.flags)
                && beforeArgs.equals(e.beforeArgs)
                && afterArgs.equals(e.afterArgs)
                && setIndexEquals(personIndexes, e.personIndexes);
    }

    private boolean setIndexEquals(Set<Index> firstSet, Set<Index> secondSet) {
        if (firstSet.size() != secondSet.size()) {
            return false;
        }

        Set<Integer> firstSetValues = firstSet.stream().map(Index::getZeroBased).collect(Collectors.toSet());
        Set<Integer> secondSetValues = secondSet.stream().map(Index::getZeroBased).collect(Collectors.toSet());

        return firstSetValues.equals(secondSetValues);
    }

    private Predicate<Task> parseDeadlineArgs() {
        Predicate<Task> predicate = unused -> true;
        if (beforeArgs.isPresent()) {
            Deadline before = beforeArgs.get();
            predicate = predicate.and(new DeadlineIsBeforePredicate(before));
        }
        if (afterArgs.isPresent()) {
            Deadline after = afterArgs.get();
            predicate = predicate.and(new DeadlineIsAfterPredicate(after));
        }
        return predicate;
    }

    public String getSuccessMessage(Model model) {
        StringBuilder successMessage = new StringBuilder();

        if (flags.contains(COMPLETED_FLAG)) {
            successMessage.append("completed tasks");
        } else if (flags.contains(ALL_FLAG)) {
            successMessage.append("tasks");
        } else {
            successMessage.append("incomplete tasks");
        }
        if (!keywordFilter.isEmpty()) {
            successMessage.append(String.format(" containing '%s'", keywordFilter));
        }
        if (!personIndexes.isEmpty()) {
            successMessage.append(
                    personIndexes.isEmpty()
                    ? ""
                    : String.format("that are assigned to %s contacts", personIndexes.size())
            );
        }
        if (beforeArgs.isPresent()) {
            Deadline before = beforeArgs.get();
            successMessage.append(
                    before.isUnspecified()
                            ? ""
                            : String.format(" that are due before %s", before)
            );
        }
        if (afterArgs.isPresent()) {
            Deadline after = afterArgs.get();
            successMessage.append(
                    after.isUnspecified()
                            ? ""
                            : String.format(" that are due after %s", after)
            );
        }
        return String.format(MESSAGE_SUCCESS, successMessage.toString());
    }

}
