package seedu.address.logic.commands;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;
import seedu.address.model.task.Task;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CommandUtil {

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */


    public static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        UUID id = editPersonDescriptor.getId().orElse(personToEdit.getId());
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Remark updatedRemark = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());
        Set<Tag> newTags = editPersonDescriptor.getTags().orElse(new HashSet<>());
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(personToEdit.getTags());
        if (newTags.size() > 0) {
            updatedTags.removeAll(newTags);
        }

        return new Person(id, updatedName, updatedPhone, updatedEmail, updatedAddress, updatedRemark, updatedTags);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    public static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline());

        Boolean updatedIsDone = taskToEdit.getCompletionStatus();
        Boolean updatedIsArchived = taskToEdit.getArchivalStatus();

        // Set<Tag> updatedTags = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());

        Set<Tag> newTags = editTaskDescriptor.getTags().orElse(new HashSet<>());
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(taskToEdit.getTags());
        if (newTags.size() > 0) {
            updatedTags.addAll(newTags);
        }

        // Id cannot be updated
        Id id = taskToEdit.getId();

        return new Task(updatedDescription, updatedDeadline, updatedIsDone, updatedIsArchived, updatedTags, id);
    }


}
