package seedu.address.logic.commands;



/*
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
    @Test
    public void equals() {
        NameContainsKeywordsPredicatePatient firstPredicate =
                new NameContainsKeywordsPredicatePatient(Collections.singletonList("first"));
        NameContainsKeywordsPredicatePatient secondPredicate =
                new NameContainsKeywordsPredicatePatient(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicatePatient predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicatePatient predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }
*/

    /*
    private NameContainsKeywordsPredicatePatient preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicatePatient(Arrays.asList(userInput.split("\\s+")));
    }
}
*/
