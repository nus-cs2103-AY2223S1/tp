package seedu.rc4hdb.logic.commands.modelcommands;

import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

public class HideCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResidentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getResidentBook(), new UserPrefs());
    }

    @Test
    public void execute_hideCommand_returnsModelWithCorrectVisibleFields() {
        List<String> visibleFields = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);
        visibleFields.removeAll(List.of("room", "phone", "matric"));
        expectedModel.setVisibleFields(visibleFields);
        expectedModel.setHiddenFields(List.of("room", "phone", "matric"));
        assertCommandSuccess(new HideCommand(List.of("room", "phone", "matric")), model,
                HideCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hideCommandOnModelWithValidHiddenFields_returnsModelWithCorrectHiddenFields() {
        model.setHiddenFields(List.of("phone", "email"));
        model.setVisibleFields(List.of("name", "index", "matric", "room", "gender", "tags", "house"));

        List<String> baseList = new ArrayList<>(List.of("name", "gender", "index"));
        List<String> fieldsToHide = new ArrayList<>(baseList);
        fieldsToHide.addAll(List.of("phone", "email"));
        List<String> allFields = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);
        allFields.removeAll(fieldsToHide);

        expectedModel.setHiddenFields(fieldsToHide);
        expectedModel.setVisibleFields(allFields);

        assertCommandSuccess(new HideCommand(baseList), model, HideCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hideCommandOnModelWithInvalidHiddenFields_throwsCommandException() {
        model.setHiddenFields(List.of("phone", "email"));
        model.setVisibleFields(List.of("name", "index", "matric", "room", "gender", "tags", "house"));

        List<String> baseList = new ArrayList<>(List.of("name", "gender", "index"));
        List<String> fieldsToHide = new ArrayList<>(baseList);
        fieldsToHide.addAll(List.of("phone", "email"));
        List<String> allFields = new ArrayList<>(ResidentFields.LOWERCASE_FIELDS);
        allFields.removeAll(fieldsToHide);

        expectedModel.setHiddenFields(fieldsToHide);
        expectedModel.setVisibleFields(allFields);

        assertCommandFailure(new HideCommand(fieldsToHide), model, HideCommand.INVALID_SUBSET);
    }

    @Test
    public void execute_hideCommandWithGlobalFieldList_throwsCommandException() {
        assertCommandFailure(new HideCommand(ResidentFields.LOWERCASE_FIELDS),
                model, HideCommand.CANNOT_HIDE_ALL_COLUMNS);
    }
}
