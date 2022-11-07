package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.team.TaskName;

class TaskNameConverterTest {
    private TaskNameConverter converter = new TaskNameConverter();

    @Test
    public void convert_validTaskName_success() throws Exception {
        String validTaskName = VALID_TASK_NAME;
        TaskName taskName = converter.convert(validTaskName);
        assertEquals(new TaskName(validTaskName), taskName);
    }

    @Test
    public void convert_invalidTaskName_throwsTypeConversionException() {
        String inValidTaskName = " ";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(inValidTaskName));
    }
}