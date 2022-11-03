package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AccessDisplayFlags;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/** Command to rename a group/task/person */
public class RenameCommand extends Command {

    public static final String MESSAGE_USAGE = "rename :Renames a given item.\n"
        + "[t|u|g]/id [new name]";

    public static final String COMMAND_WORD = "rename";

    public static final String NO_INPUT = "No input item is chosen!";
    public static final String MESSAGE_DUPLICATE = "An item with the same name already exist!";
    public static final String OUT_OF_BOUNDS = "ID selected is out of bounds!";
    public static final String SUCCESS_MSG = "The item have been renamed!";
    public static final String INVALID_FORMAT = "The item cannot be renamed to such!";

    private DisplayItem itemToRename = null;
    private int renameType = 0;
    private final String newName;

    private final Index targetIndex;

    /**
     * Command to rename a displayitem
     */
    public RenameCommand(Index selectedIndex, int renameType, String newName) {
        this.renameType = renameType;
        this.targetIndex = selectedIndex;
        this.newName = newName;
    }

    /**
     * Constructor called when the user is expected to pass in the displayItem
     */
    public RenameCommand(String newName) {
        this.targetIndex = null;
        this.newName = newName;
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof DisplayItem)) {
            itemToRename = null;
            return this;
        }

        if (((DisplayItem) additionalData).getTypeFlag() == AccessDisplayFlags.GROUP) {
            renameType = 1;
        } else if (((DisplayItem) additionalData).getTypeFlag() == AccessDisplayFlags.PERSON) {
            renameType = 2;
        } else if (((DisplayItem) additionalData).getTypeFlag() == AccessDisplayFlags.TASK) {
            renameType = 3;
        } else {
            assert false; // this shouldnt be reached
        }

        itemToRename = (DisplayItem) additionalData;
        return this;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (renameType == 0) {
            throw new CommandException(NO_INPUT);
        }
        if (renameType == 1) {
            executeForGroup(model);
        } else if (renameType == 2) {
            executeForPerson(model);
        } else if (renameType == 3) {
            executeForTask(model);
        }

        return new CommandResult(SUCCESS_MSG);
    }

    void executeForTask(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();
        Task target;
        if (targetIndex == null && itemToRename == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        if (itemToRename == null) {

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            target = lastShownList.get(targetIndex.getZeroBased());
        } else {
            target = (Task) itemToRename;
        }

        Task tmp = new Task(newName, "dummy");
        tmp.setParent(target.getParent());

        if (model.hasTask(tmp)) {
            throw new CommandException(MESSAGE_DUPLICATE);
        }

        target.rename(newName);
        model.refresh();
    }

    void executeForPerson(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person target;
        if (targetIndex == null && itemToRename == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        if (itemToRename == null) {

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            target = lastShownList.get(targetIndex.getZeroBased());
        } else {
            target = (Person) itemToRename;
        }

        Person tmp = new Person(newName);
        tmp.setParent(model.getContextContainer());

        if (model.hasPerson(tmp)) {
            throw new CommandException(MESSAGE_DUPLICATE);
        }
        target.rename(newName);
        model.refresh();
    }

    void executeForGroup(Model model) throws CommandException {
        List<Group> lastShownList = model.getFilteredTeamList();
        Group target;
        if (targetIndex == null && itemToRename == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        if (itemToRename == null) {

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            target = lastShownList.get(targetIndex.getZeroBased());
        } else {
            target = (Group) itemToRename;
        }
        if (!Group.isValidGroupName(newName)) {
            throw new CommandException(INVALID_FORMAT);
        }
        Group tmp = new Group(newName);
        tmp.setParent(target.getParent());

        if (model.hasTeam(tmp)) {
            throw new CommandException(MESSAGE_DUPLICATE);
        }
        target.rename(newName);
        model.refresh();
    }

}
