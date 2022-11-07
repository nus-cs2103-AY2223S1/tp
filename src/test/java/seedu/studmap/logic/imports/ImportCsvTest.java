package seedu.studmap.logic.imports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.logic.imports.ImportCsv.CSV_WRONG_FORMAT;
import static seedu.studmap.logic.imports.ImportCsv.DUPLICATE_STUDENT;
import static seedu.studmap.logic.imports.ImportCsv.FILE_DOES_NOT_EXIST;
import static seedu.studmap.logic.imports.ImportCsv.FILE_IS_NULL;
import static seedu.studmap.logic.imports.ImportCsv.FILE_NOT_CSV;
import static seedu.studmap.logic.imports.ImportCsv.MISSING_COMPULSORY_ATTRIBUTE;
import static seedu.studmap.testutil.Assert.assertThrows;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.studmap.logic.imports.exceptions.ImportException;
import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.GitName;
import seedu.studmap.model.student.Module;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;
import seedu.studmap.model.student.StudentID;
import seedu.studmap.model.student.TeleHandle;

class ImportCsvTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportCsvTest");
    private final Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());
    private final ImportCsv importer = new ImportCsv();

    private File openFile(String filePath) {
        return addToTestDataPathIfNotNull(filePath).toFile();
    }

    private Path addToTestDataPathIfNotNull(String fileName) {
        return fileName != null
                ? TEST_DATA_FOLDER.resolve(fileName)
                : null;
    }

    @Test
    public void execute_importException() {

        assertThrows(ImportException.class, FILE_IS_NULL, () -> importer.execute(model, null));

        final File nullFile = new File(" ");
        assertThrows(ImportException.class, FILE_DOES_NOT_EXIST, () -> importer.execute(model, nullFile));

        try {
            final File nonCsvFile = File.createTempFile("temp", null);
            assertThrows(ImportException.class, FILE_NOT_CSV, () -> importer.execute(model, nonCsvFile));
        } catch (IOException e) {
            // Do nothing
        }

        final File wrongFormatFile = openFile("wrongFormat.csv");
        assertThrows(ImportException.class, CSV_WRONG_FORMAT, () -> importer.execute(model, wrongFormatFile));

        final File headerSize6File = openFile("headerSize6.csv");
        assertThrows(ImportException.class, CSV_WRONG_FORMAT, () -> importer.execute(model, headerSize6File));

        final File headerSize8File = openFile("headerSize8.csv");
        assertThrows(ImportException.class, CSV_WRONG_FORMAT, () -> importer.execute(model, headerSize8File));

    }

    @Test
    public void execute_importSuccess() {

        Model model = new ModelManager();
        String output;
        final File validFormatFile = openFile("validFormat.csv");

        try {
            output = importer.execute(model, validFormatFile);

            StringBuilder log = new StringBuilder();
            log.append(String.format(DUPLICATE_STUDENT, 9));
            log.append(String.format(MISSING_COMPULSORY_ATTRIBUTE, 10, ImportCsv.Attributes.NAME));
            log.append(String.format(MISSING_COMPULSORY_ATTRIBUTE, 11, ImportCsv.Attributes.STUDENTID));
            log.append(String.format(MISSING_COMPULSORY_ATTRIBUTE, 12, ImportCsv.Attributes.MODULE));

            assertEquals(log.toString(), output);

        } catch (ImportException e) {
            assert (false);
        }

        StudentData studentData1 = new StudentData();
        studentData1.setName(new Name("Silas Yeo"));
        studentData1.setId(new StudentID("E0773771"));
        studentData1.setModule(new Module("CS2106"));
        studentData1.setPhone(new Phone("84112213"));
        studentData1.setGitUser(new GitName("silasysy"));
        assertTrue(model.hasStudent(new Student(studentData1)));

        StudentData studentData2 = new StudentData();
        studentData2.setName(new Name("Sally"));
        studentData2.setId(new StudentID("E1288122"));
        studentData2.setModule(new Module("CS2106"));
        studentData2.setPhone(new Phone("94732221"));
        studentData2.setGitUser(new GitName("sallysys"));
        assertTrue(model.hasStudent(new Student(studentData2)));

        StudentData studentData3 = new StudentData();
        studentData3.setName(new Name("Sheyuan"));
        studentData3.setId(new StudentID("E2345229"));
        studentData3.setModule(new Module("CS2106"));
        studentData3.setPhone(new Phone("98771923"));
        studentData3.setGitUser(new GitName("piyopp"));
        studentData3.setTeleHandle(new TeleHandle("@piyopiyo"));
        assertTrue(model.hasStudent(new Student(studentData3)));

        StudentData studentData4 = new StudentData();
        studentData4.setName(new Name("Po Hsien"));
        studentData4.setId(new StudentID("E0998281"));
        studentData4.setModule(new Module("CS2103T"));
        studentData4.setPhone(new Phone("98882110"));
        studentData4.setEmail(new Email("popo@popo.popo"));
        studentData4.setGitUser(new GitName("popopo"));
        assertTrue(model.hasStudent(new Student(studentData4)));

        StudentData studentData5 = new StudentData();
        studentData5.setName(new Name("Po Taeto"));
        studentData5.setId(new StudentID("E0982113"));
        studentData5.setModule(new Module("CS2103T"));
        studentData5.setPhone(new Phone("96600302"));
        studentData5.setGitUser(new GitName("potatopo"));
        assertTrue(model.hasStudent(new Student(studentData5)));

        StudentData studentData6 = new StudentData();
        studentData6.setName(new Name("Salad Yo"));
        studentData6.setId(new StudentID("E0123111"));
        studentData6.setModule(new Module("CS2106"));
        studentData6.setPhone(new Phone("89123612"));
        studentData6.setGitUser(new GitName("saladydy"));
        assertTrue(model.hasStudent(new Student(studentData6)));

        StudentData studentData7 = new StudentData();
        studentData7.setName(new Name("Tom Aito"));
        studentData7.setId(new StudentID("E1728221"));
        studentData7.setModule(new Module("CS2106"));
        studentData7.setPhone(new Phone("88456973"));
        studentData7.setGitUser(new GitName("tomatoto"));
        assertTrue(model.hasStudent(new Student(studentData7)));

    }
}
