package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing task commands.
 */
public class TaskCommandTestUtil {

    public static final String VALID_TITLE_HOMEWORK = "Do homework";
    public static final String VALID_TITLE_WORKSHOP = "Attend workshop";
    public static final String VALID_DEADLINE_HOMEWORK = "1 December 2022";
    public static final String VALID_DEADLINE_WORKSHOP = "1 January 2023";
    public static final String VALID_PROJECT_HOMEWORK = "CS2103";
    public static final String VALID_PROJECT_WORKSHOP = "CS";
    public static final String VALID_CONTACT_HOMEWORK = "1";
    public static final String VALID_CONTACT_WORKSHOP = "2";

    public static final String TITLE_DESC_HOMEWORK = " " + PREFIX_TITLE + VALID_TITLE_HOMEWORK;
    public static final String TITLE_DESC_WORKSHOP = " " + PREFIX_TITLE + VALID_TITLE_WORKSHOP;
    public static final String DEADLINE_DESC_HOMEWORK = " " + PREFIX_DEADLINE + VALID_DEADLINE_HOMEWORK;
    public static final String DEADLINE_DESC_WORKSHOP = " " + PREFIX_DEADLINE + VALID_DEADLINE_WORKSHOP;
    public static final String PROJECT_DESC_HOMEWORK = " " + PREFIX_PROJECT + VALID_PROJECT_HOMEWORK;
    public static final String PROJECT_DESC_WORKSHOP = " " + PREFIX_PROJECT + VALID_PROJECT_WORKSHOP;
    public static final String CONTACT_DESC_HOMEWORK = " " + PREFIX_CONTACT + VALID_CONTACT_HOMEWORK;
    public static final String CONTACT_DESC_WORKSHOP = " " + PREFIX_CONTACT + VALID_CONTACT_WORKSHOP;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + " "; // empty string not allowed for title
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "-11/12"; // '-' not allowed in deadlines
    public static final String INVALID_PROJECT_DESC = " " + PREFIX_PROJECT + "&test"; // &' not allowed in projects
    public static final String INVALID_CONTACT_DESC = " " + PREFIX_CONTACT; // empty string not allowed for contact

    public static final EditTaskCommand.EditTaskDescriptor DESC_HOMEWORK;
    public static final EditTaskCommand.EditTaskDescriptor DESC_WORKSHOP;

    static {
        DESC_HOMEWORK = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_HOMEWORK)
                .withDeadline(VALID_DEADLINE_HOMEWORK).withProject(VALID_PROJECT_HOMEWORK).build();
        DESC_WORKSHOP = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_WORKSHOP)
                .withDeadline(VALID_DEADLINE_WORKSHOP).withProject(VALID_PROJECT_WORKSHOP).build();
    }

}
