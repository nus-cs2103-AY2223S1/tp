package seedu.address.model.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalQuestions.Q1;
import static seedu.address.testutil.TypicalQuestions.Q2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.question.exceptions.DuplicateQuestionException;
import seedu.address.model.question.exceptions.QuestionNotFoundException;
import seedu.address.testutil.QuestionBuilder;

public class UniqueQuestionListTest {

    private final UniqueQuestionList uniqueQuestionList = new UniqueQuestionList();

    @Test
    public void contains_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.contains(null));
    }

    @Test
    public void contains_questionNotInList_returnsFalse() {
        assertFalse(uniqueQuestionList.contains(Q1));
    }

    @Test
    public void contains_questionInList_returnsTrue() {
        uniqueQuestionList.add(Q1);
        assertTrue(uniqueQuestionList.contains(Q1));
    }

    @Test
    public void contains_questionWithSameIdentityFieldsInList_returnsTrue() {
        uniqueQuestionList.add(Q1);
        Question editedQ1 = new QuestionBuilder(Q1).withImportantTag(true)
                .build();
        assertTrue(uniqueQuestionList.contains(editedQ1));
    }

    @Test
    public void add_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.add(null));
    }

    @Test
    public void add_duplicateQuestion_throwsDuplicateQuestionException() {
        uniqueQuestionList.add(Q1);
        assertThrows(DuplicateQuestionException.class, () -> uniqueQuestionList.add(Q1));
    }

    @Test
    public void setQuestion_nullTargetQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestion(null, Q1));
    }

    @Test
    public void setQuestion_nullEditedQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestion(Q1, null));
    }

    @Test
    public void setQuestion_targetQuestionNotInList_throwsQuestionNotFoundException() {
        assertThrows(QuestionNotFoundException.class, () -> uniqueQuestionList.setQuestion(Q1, Q1));
    }

    @Test
    public void setQuestion_editedQuestionIsSameQuestion_success() {
        uniqueQuestionList.add(Q1);
        uniqueQuestionList.setQuestion(Q1, Q1);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(Q1);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestion_editedQuestionHasSameIdentity_success() {
        uniqueQuestionList.add(Q1);
        Question editedQ1 = new QuestionBuilder(Q1).withImportantTag(true)
                .build();
        uniqueQuestionList.setQuestion(Q1, editedQ1);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(editedQ1);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestion_editedQuestionHasDifferentIdentity_success() {
        uniqueQuestionList.add(Q1);
        uniqueQuestionList.setQuestion(Q1, Q2);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(Q2);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestion_editedQuestionHasNonUniqueIdentity_throwsDuplicateQuestionException() {
        uniqueQuestionList.add(Q1);
        uniqueQuestionList.add(Q2);
        assertThrows(DuplicateQuestionException.class, () -> uniqueQuestionList.setQuestion(Q1, Q2));
    }

    @Test
    public void remove_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.remove(null));
    }

    @Test
    public void remove_questionDoesNotExist_throwsQuestionNotFoundException() {
        assertThrows(QuestionNotFoundException.class, () -> uniqueQuestionList.remove(Q1));
    }

    @Test
    public void remove_existingQuestion_removesQuestion() {
        uniqueQuestionList.add(Q1);
        uniqueQuestionList.remove(Q1);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_nullUniqueQuestionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestions((UniqueQuestionList) null));
    }

    @Test
    public void setQuestions_uniqueQuestionList_replacesOwnListWithProvidedUniqueQuestionList() {
        uniqueQuestionList.add(Q1);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(Q2);
        uniqueQuestionList.setQuestions(expectedUniqueQuestionList);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestions((List<Question>) null));
    }

    @Test
    public void setQuestions_list_replacesOwnListWithProvidedList() {
        uniqueQuestionList.add(Q1);
        List<Question> questionList = Collections.singletonList(Q2);
        uniqueQuestionList.setQuestions(questionList);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(Q2);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_listWithDuplicateQuestions_throwsDuplicateQuestionException() {
        List<Question> listWithDuplicateQuestions = Arrays.asList(Q1, Q1);
        assertThrows(DuplicateQuestionException.class, () ->
                uniqueQuestionList.setQuestions(listWithDuplicateQuestions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueQuestionList.asUnmodifiableObservableList().remove(0));
    }
}
