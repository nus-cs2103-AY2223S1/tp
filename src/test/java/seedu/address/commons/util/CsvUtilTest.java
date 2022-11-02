package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AARON;
import static seedu.address.testutil.TypicalPersons.ANDERSON;
import static seedu.address.testutil.TypicalPersons.BEN;
import static seedu.address.testutil.TypicalPersons.CABE;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.testutil.ProfessorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TeachingAssistantBuilder;

/**
 * Test the import and export functions found in CsvUtil
 */
class CsvUtilTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "CsvUtilTest");
    private static final Path NULL_NAME_IMPORT_FILE = TEST_DATA_FOLDER.resolve("NullName.csv");
    private static final Path NULL_PHONE_IMPORT_FILE = TEST_DATA_FOLDER.resolve("NullPhone.csv");
    private static final Path NULL_EMAIL_IMPORT_FILE = TEST_DATA_FOLDER.resolve("NullEmail.csv");
    private static final Path VALID_PROFESSOR_NO_OFFICE_HOUR_IMPORT_FILE =
            TEST_DATA_FOLDER.resolve("ValidProfessorNoOfficeHour.csv");
    private static final Path VALID_PROFESSOR_OFFICE_HOUR_IMPORT_FILE =
            TEST_DATA_FOLDER.resolve("ValidProfessorOfficeHour.csv");
    private static final Path VALID_STUDENT_IMPORT_FILE = TEST_DATA_FOLDER.resolve("ValidStudent.csv");
    private static final Path VALID_TA_IMPORT_FILE = TEST_DATA_FOLDER.resolve("ValidTa.csv");
    private static final Path VALID_STUDENT_NO_TAGS_IMPORT_FILE = TEST_DATA_FOLDER.resolve("ValidStudentNoTags.csv");
    private static final Path VALID_PROF_NO_TAGS_IMPORT_FILE = TEST_DATA_FOLDER.resolve("ValidProfNoTags.csv");

    @Test
    public void importCsv_validProfessorDetailsNoOfficeHour_returnsProfessor() {
        try {
            Professor professor = (Professor) CsvUtil.importCsv(VALID_PROFESSOR_NO_OFFICE_HOUR_IMPORT_FILE
                    .toFile()).get(0);
            assertEquals(ANDERSON, professor);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void importCsv_validProfessorDetailsOfficeHourOptional_returnsProfessor() {
        try {
            Professor professor = (Professor) CsvUtil.importCsv(VALID_PROFESSOR_OFFICE_HOUR_IMPORT_FILE
                    .toFile()).get(0);
            assertEquals(BEN, professor);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void importCsv_validStudentDetails_returnsStudent() {
        try {
            Student student = (Student) CsvUtil.importCsv(VALID_STUDENT_IMPORT_FILE.toFile()).get(0);
            assertEquals(AARON, student);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void importCsv_validTaDetails_returnTa() {
        try {
            TeachingAssistant ta = (TeachingAssistant) CsvUtil.importCsv(VALID_TA_IMPORT_FILE.toFile()).get(0);
            assertEquals(CABE, ta);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void importCsv_validStudentDetailsOptionalTag_returnsStudent() {
        StudentBuilder studentBuilder = new StudentBuilder();
        Student expected = studentBuilder.build();
        try {
            Student actual = (Student) CsvUtil.importCsv(VALID_STUDENT_NO_TAGS_IMPORT_FILE.toFile()).get(0);
            assertEquals(expected, actual);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void importCsv_validProfessorDetailsOptionalTag_returnsProfessor() {
        ProfessorBuilder professorBuilder = new ProfessorBuilder();
        Professor expected = (Professor) professorBuilder.withTags().build();
        try {
            Professor actual = (Professor) CsvUtil.importCsv(VALID_PROF_NO_TAGS_IMPORT_FILE.toFile()).get(0);
            assertEquals(expected, actual);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void importCsv_nullName_throwsCommandException() {
        assertThrows(CommandException.class, () -> CsvUtil.importCsv(NULL_NAME_IMPORT_FILE.toFile()));
    }
    @Test
    public void importCsv_nullPhone_throwsCommandException() {
        assertThrows(CommandException.class, () -> CsvUtil.importCsv(NULL_PHONE_IMPORT_FILE.toFile()));
    }
    @Test
    public void importCsv_nullEmail_throwsCommandException() {
        assertThrows(CommandException.class, () -> CsvUtil.importCsv(NULL_EMAIL_IMPORT_FILE.toFile()));
    }
}
