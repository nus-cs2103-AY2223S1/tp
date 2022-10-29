package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.HealthContact;
import seedu.address.testutil.TypicalAppointments;
import seedu.address.testutil.TypicalPatients;

public class JsonSerializableHealthContactTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableHealthContactTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsHealthContact.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientHealthContact.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientHealthContact.json");
    private static final Path TYPICAL_APPOINTMENTS_FILE =
            TEST_DATA_FOLDER.resolve("typicalAppointmentsHealthContact.json");
    private static final Path INVALID_APPOINTMENT_FILE =
            TEST_DATA_FOLDER.resolve("invalidAppointmentHealthContact.json");
    private static final Path DUPLICATE_APPOINTMENT_FILE =
            TEST_DATA_FOLDER.resolve("duplicateAppointmentHealthContact.json");
    public static final Path APPOINTING_PATIENT_NOT_EXIST_APPOINTMENT_FILE =
            TEST_DATA_FOLDER.resolve("appointingPatientNotExistHealthContact.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializableHealthContact dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
                JsonSerializableHealthContact.class).get();
        HealthContact healthContactFromFile = dataFromFile.toModelType();
        HealthContact typicalPatientsHealthContact = TypicalPatients.getTypicalPatientsHealthContact();
        assertEquals(healthContactFromFile, typicalPatientsHealthContact);
    }

    @Test
    public void toModelType_typicalAppointmentsFile_success() throws Exception {
        JsonSerializableHealthContact dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPOINTMENTS_FILE,
                JsonSerializableHealthContact.class).get();
        HealthContact healthContactFromFile = dataFromFile.toModelType();
        HealthContact typicalAppointmentsHealthContact = TypicalAppointments.getTypicalAppointmentsHealthContact();
        assertEquals(healthContactFromFile, typicalAppointmentsHealthContact);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHealthContact dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializableHealthContact.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidAppointmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHealthContact dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_FILE,
                JsonSerializableHealthContact.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableHealthContact dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
                JsonSerializableHealthContact.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHealthContact.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableHealthContact dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPOINTMENT_FILE,
                JsonSerializableHealthContact.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHealthContact.MESSAGE_DUPLICATE_APPOINTMENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_appointingPatientNotExist_throwsIllegalValueException() throws Exception {
        JsonSerializableHealthContact dataFromFile =
                JsonUtil.readJsonFile(APPOINTING_PATIENT_NOT_EXIST_APPOINTMENT_FILE,
                JsonSerializableHealthContact.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHealthContact.MESSAGE_APPOINTING_PATIENT_NOT_EXIST,
                dataFromFile::toModelType);
    }
}
