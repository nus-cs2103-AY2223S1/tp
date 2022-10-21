package seedu.address.logic.parser.task;

import static seedu.address.testutil.TypicalTasks.TASK_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {

    private final AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TASK_ONE).build();
    }
}
