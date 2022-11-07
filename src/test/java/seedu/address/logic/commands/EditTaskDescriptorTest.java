package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_QUIZ;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_REPORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REPORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_2101;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_QUIZ);
        assertTrue(DESC_QUIZ.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_QUIZ.equals(DESC_QUIZ));

        // null -> returns false
        assertFalse(DESC_QUIZ.equals(null));

        // different types -> returns false
        assertFalse(DESC_QUIZ.equals(5));

        // different values -> returns false
        assertFalse(DESC_QUIZ.equals(DESC_REPORT));

        // different description -> returns false
        EditTaskDescriptor editedQuiz = new EditTaskDescriptorBuilder(DESC_QUIZ)
                .withDescription(VALID_DESCRIPTION_REPORT).build();
        assertFalse(DESC_QUIZ.equals(editedQuiz));

        // different deadline -> returns false
        editedQuiz = new EditTaskDescriptorBuilder(DESC_QUIZ).withDeadline(VALID_DEADLINE_REPORT).build();
        assertFalse(DESC_QUIZ.equals(editedQuiz));

        // different completion status -> returns false
        editedQuiz = new EditTaskDescriptorBuilder(DESC_QUIZ).withCompletionStatus(true).build();
        assertFalse(DESC_QUIZ.equals(editedQuiz));

        // different archival status -> returns false
        editedQuiz = new EditTaskDescriptorBuilder(DESC_QUIZ).withArchivalStatus(true).build();
        assertFalse(DESC_QUIZ.equals(editedQuiz));

        // different tags -> returns false
        editedQuiz = new EditTaskDescriptorBuilder(DESC_QUIZ).withTags(VALID_TAG_2101).build();
        assertFalse(DESC_QUIZ.equals(editedQuiz));
    }
}
