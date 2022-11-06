package seedu.address.logic.commands.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_CONTACT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.teammate.Teammate;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;

/**
 * Assigns person(s) to a task.
 */
public class AssignTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "assign";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL + ": Assigns/unassigns person(s) to a task.\n"
            + "Parameters: "
            + "TASK_INDEX (must be a positive integer) "
            + "[" + PREFIX_ADD_CONTACT + "CONTACT_INDEX OR CONTACT_NAME]... "
            + "[" + PREFIX_DELETE_CONTACT + "CONTACT_INDEX OR CONTACT_NAME]...\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + "1 "
            + PREFIX_ADD_CONTACT + "1 "
            + PREFIX_ADD_CONTACT + "Bernice Yu "
            + PREFIX_DELETE_CONTACT + "3 "
            + PREFIX_DELETE_CONTACT + "David Li";

    public static final String MESSAGE_SUCCESS = "Assigned persons updated for task: %1$s";

    public static final String MESSAGE_REPEATED_CONTACT = "Add: %1$s has already been added.";

    public static final String MESSAGE_CONTACT_DOES_NOT_EXIT = "Delete: %1$s has not been previously assigned.";

    public static final String MESSAGE_PARTIAL_SUCCESS = "All other persons updated accordingly";

    private final Index taskIndex;
    private final Set<Index> personAddIndexes = new HashSet<>();
    private final Set<String> personAddNames = new HashSet<>();
    private final Set<Index> personDeleteIndexes = new HashSet<>();
    private final Set<String> personDeleteNames = new HashSet<>();

    private final Set<String> invalidNames = new HashSet<>();
    private final Set<Index> invalidIndexes = new HashSet<>();

    private final Set<Contact> alreadyAddedContacts = new HashSet<>();
    private final Set<Contact> notAddedContacts = new HashSet<>();

    /**
     * Creates an AssignTaskCommand to assign persons to the given task.
     *
     * @param taskIndex of the task to be updated
     * @param personAddIndexes Indexes of all persons to be assigned to task
     * @param personAddNames Names of all persons to be assigned to task
     * @param personDeleteIndexes Indexes of all persons to be removed from task
     * @param personDeleteNames Names of all persons to be removed from task
     */
    public AssignTaskCommand(Index taskIndex, Set<Index> personAddIndexes, Set<String> personAddNames,
                             Set<Index> personDeleteIndexes, Set<String> personDeleteNames) {
        requireAllNonNull(taskIndex, personAddIndexes, personAddNames, personDeleteIndexes, personDeleteNames);

        this.taskIndex = taskIndex;
        this.personAddIndexes.addAll(personAddIndexes);
        this.personAddNames.addAll(personAddNames);
        this.personDeleteIndexes.addAll(personDeleteIndexes);
        this.personDeleteNames.addAll(personDeleteNames);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownTaskList = model.getFilteredTaskList();
        if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToModify = lastShownTaskList.get(taskIndex.getZeroBased());

        List<Teammate> lastShownPersonsList = model.getFilteredPersonList();

        Set<Contact> contactsToAdd = getAllContacts(personAddIndexes, personAddNames, lastShownPersonsList);
        Set<Contact> contactsToDelete = getAllContacts(personDeleteIndexes, personDeleteNames, lastShownPersonsList);

        Set<Contact> contactSetToModify = new HashSet<>(taskToModify.getAssignedContacts());
        for (Contact contactToAdd : contactsToAdd) {
            if (contactSetToModify.contains(contactToAdd)) {
                alreadyAddedContacts.add(contactToAdd);
            }
            contactSetToModify.add(contactToAdd);
        }
        for (Contact contactToAdd : contactsToDelete) {
            if (!contactSetToModify.contains(contactToAdd)) {
                notAddedContacts.add(contactToAdd);
            }
            contactSetToModify.remove(contactToAdd);
        }

        Task editedTask = new Task(
                taskToModify.getTitle(),
                taskToModify.getCompleted(),
                taskToModify.getDeadline(),
                taskToModify.getProject(),
                contactSetToModify
        );

        model.setTask(taskToModify, editedTask);

        if (!invalidIndexes.isEmpty() || !invalidNames.isEmpty()
                || !alreadyAddedContacts.isEmpty() || !notAddedContacts.isEmpty()) {
            throw new CommandException(buildErrorMessage() + MESSAGE_PARTIAL_SUCCESS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskIndex.getOneBased()));
    }

    private Set<Contact> personIndexesToContacts(Set<Index> personIndexes, List<Teammate> teammateList) {
        Set<Contact> assignedContacts = new HashSet<>();
        for (Index personIndex : personIndexes) {
            if (personIndex.getZeroBased() >= teammateList.size()) {
                invalidIndexes.add(personIndex);
                continue;
            }
            Contact contactToAssign =
                new Contact(teammateList.get(personIndex.getZeroBased()).getName().fullName);
            assignedContacts.add(contactToAssign);
        }
        return assignedContacts;
    }

    private Set<Contact> personNamesToContacts(Set<String> personNames, List<Teammate> teammateList) {
        Set<Contact> assignedContacts = new HashSet<>();
        for (String personName : personNames) {
            String matchingPersonsName = Contact.corrNameInPersonsList(teammateList, personName);
            if (matchingPersonsName.isEmpty()) {
                invalidNames.add(personName);
                continue;
            }
            Contact contactToAssign = new Contact(matchingPersonsName);
            assignedContacts.add(contactToAssign);
        }
        return assignedContacts;
    }

    private Set<Contact> getAllContacts(Set<Index> personIndexes, Set<String> personNames, List<Teammate> teammateList) {
        Set<Contact> contactsFromIndex = personIndexesToContacts(personIndexes, teammateList);
        Set<Contact> contactsFromName = personNamesToContacts(personNames, teammateList);
        contactsFromIndex.addAll(contactsFromName);
        return contactsFromIndex;
    }

    private String buildErrorMessage() {
        StringBuilder errorMessage = new StringBuilder();
        for (Index invalidIndex : invalidIndexes) {
            String currMessage = String.format(Messages.MESSAGE_INVALID_PERSON_INDEX_CUSTOM,
                    invalidIndex.getOneBased());
            errorMessage.append(currMessage);
            errorMessage.append("\n");
        }
        for (String invalidName : invalidNames) {
            String currMessage = String.format(Messages.MESSAGE_INVALID_PERSON_NAME, invalidName);
            errorMessage.append(currMessage);
            errorMessage.append("\n");
        }
        for (Contact contact : alreadyAddedContacts) {
            String currMessage = String.format(MESSAGE_REPEATED_CONTACT, contact.toString());
            errorMessage.append(currMessage);
            errorMessage.append("\n");
        }
        for (Contact contact : notAddedContacts) {
            String currMessage = String.format(MESSAGE_CONTACT_DOES_NOT_EXIT, contact.toString());
            errorMessage.append(currMessage);
            errorMessage.append("\n");
        }
        return errorMessage.toString();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignTaskCommand)) {
            return false;
        }

        // state check
        AssignTaskCommand e = (AssignTaskCommand) other;
        return taskIndex.equals(e.taskIndex) && setIndexEquals(personAddIndexes, e.personAddIndexes)
                && setIndexEquals(personDeleteIndexes, e.personDeleteIndexes)
                && personAddNames.equals(e.personAddNames)
                && personDeleteNames.equals(e.personDeleteNames);
    }

    private boolean setIndexEquals(Set<Index> firstSet, Set<Index> secondSet) {
        if (firstSet.size() != secondSet.size()) {
            return false;
        }

        Set<Integer> firstSetValues = firstSet.stream().map(Index::getZeroBased).collect(Collectors.toSet());
        Set<Integer> secondSetValues = secondSet.stream().map(Index::getZeroBased).collect(Collectors.toSet());

        return firstSetValues.equals(secondSetValues);
    }

}
