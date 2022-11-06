package seedu.boba.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.boba.commons.core.Messages;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;

/**
 * Contains integration tests (interaction with the BobaBotModel) for {@code FindCommand}.
 */
public class CalculateCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
    private BobaBotModel expectedBobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());


}

