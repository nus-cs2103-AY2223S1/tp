package tracko.logic.commands;

public class FindItemCommandTest {

//    private Model model = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
//    private Model expectedModel = new ModelManager(getTrackOWithTypicalOrders(), new UserPrefs());
//
//    @Test
//    public void equals() {
//        NameContainsKeywordsPredicate firstPredicate =
//                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
//        NameContainsKeywordsPredicate secondPredicate =
//                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
//
//        FindOrderCommand findFirstCommand = new FindOrderCommand(firstPredicate);
//        FindOrderCommand findSecondCommand = new FindOrderCommand(secondPredicate);
//
//        // same object -> returns true
//        assertTrue(findFirstCommand.equals(findFirstCommand));
//
//        // same values -> returns true
//        FindOrderCommand findFirstCommandCopy = new FindOrderCommand(firstPredicate);
//        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(findFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(findFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(findFirstCommand.equals(findSecondCommand));
//    }
//
//    @Test
//    public void execute_zeroKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindOrderCommand command = new FindOrderCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    @Test
//    public void execute_multipleKeywords_multiplePersonsFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
//        FindOrderCommand command = new FindOrderCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(ORDER_3, ORDER_5, ORDER_6), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
}
