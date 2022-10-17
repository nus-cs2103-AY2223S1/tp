package seedu.address.model.message;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_HAPPY_BIRTHDAY;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_RECOMMEND_PRODUCT;

import org.junit.jupiter.api.Test;

class MessageListTest {

    @Test
    public void add_null_throwsNullPointerException() {
        MessageList expectedMessageList = new MessageList();
        assertThrows(NullPointerException.class, () -> expectedMessageList.add(null));
    }

    @Test
    public void add_validElement_addsSuccessfully() {
        MessageList expectedMessageList = new MessageList();
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.add(VALID_MESSAGE_HAPPY_BIRTHDAY);
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.add(VALID_MESSAGE_RECOMMEND_PRODUCT);
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));
    }

    @Test
    public void remove_null_throwsNullPointerException() {
        MessageList expectedMessageList = new MessageList();
        assertThrows(NullPointerException.class, () -> expectedMessageList.remove(null));
    }

    @Test
    public void remove_validElement_removesSuccessfully() {
        MessageList expectedMessageList = new MessageList();
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.add(VALID_MESSAGE_HAPPY_BIRTHDAY);
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.add(VALID_MESSAGE_RECOMMEND_PRODUCT);
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.remove(VALID_MESSAGE_HAPPY_BIRTHDAY);
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.remove(VALID_MESSAGE_RECOMMEND_PRODUCT);
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));
    }

    @Test
    public void contains_null_throwsNullPointerException() {
        MessageList expectedMessageList = new MessageList();
        assertThrows(NullPointerException.class, () -> expectedMessageList.contains(null));
    }

    @Test
    public void contains_validElement_containsSuccessfully() {
        MessageList expectedMessageList = new MessageList();
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.add(VALID_MESSAGE_HAPPY_BIRTHDAY);
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.add(VALID_MESSAGE_RECOMMEND_PRODUCT);
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.remove(VALID_MESSAGE_HAPPY_BIRTHDAY);
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertTrue(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));

        expectedMessageList.remove(VALID_MESSAGE_RECOMMEND_PRODUCT);
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertFalse(expectedMessageList.contains(VALID_MESSAGE_RECOMMEND_PRODUCT));
    }

    @Test
    public void equals_allCombinations_success() {
        MessageList messageList1 = new MessageList();
        MessageList messageList2 = new MessageList();
        MessageList messageList3 = new MessageList();
        MessageList messageList4 = new MessageList();

        messageList1.add(VALID_MESSAGE_HAPPY_BIRTHDAY);
        messageList1.add(VALID_MESSAGE_RECOMMEND_PRODUCT);

        messageList2.add(VALID_MESSAGE_HAPPY_BIRTHDAY);
        messageList2.add(VALID_MESSAGE_RECOMMEND_PRODUCT);

        messageList3.add(VALID_MESSAGE_HAPPY_BIRTHDAY);

        // Same messageList -> return true
        assertTrue(messageList1.equals(messageList1));

        // Same messages -> return true
        assertTrue(messageList1.equals(messageList2));

        // Different messages -> return false
        assertFalse(messageList1.equals(messageList3));

        // Empty messageList and non-empty messageList -> return false
        assertFalse(messageList1.equals(messageList4));
    }
}
