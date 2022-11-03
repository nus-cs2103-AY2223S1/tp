package seedu.taassist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalStudents.DANIEL;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.StudentModuleData;

class JsonAdaptedStudentModuleDataTest {

    @Test
    public void toModelType_validStudentModuleData_returnsStudentModuleData() throws IllegalValueException {
        StudentModuleData data = DANIEL.getModuleDataList().get(0);
        JsonAdaptedStudentModuleData jsonAdaptedStudentModuleData = new JsonAdaptedStudentModuleData(data);
        assertEquals(data, jsonAdaptedStudentModuleData.toModelType());
    }

    @Test
    public void toModelType_invalidModuleName_throwsIllegalValueException() {
        JsonAdaptedStudentModuleData jsonAdaptedStudentModuleData =
                new JsonAdaptedStudentModuleData("+CS1231S", null);
        assertThrows(IllegalValueException.class, ModuleClass.MESSAGE_CONSTRAINTS,
                jsonAdaptedStudentModuleData::toModelType);
    }

    @Test
    public void toModelType_nullModuleName_throwsIllegalValueException() {
        JsonAdaptedStudentModuleData jsonAdaptedStudentModuleData =
                new JsonAdaptedStudentModuleData(null, null);
        assertThrows(IllegalValueException.class, JsonAdaptedStudentModuleData.MISSING_NAME_MESSAGE,
                jsonAdaptedStudentModuleData::toModelType);
    }
}
