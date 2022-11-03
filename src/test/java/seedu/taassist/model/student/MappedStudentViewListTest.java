package seedu.taassist.model.student;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class MappedStudentViewListTest {

    private final ObservableList<Student> internalStudentList = FXCollections.observableArrayList(ALICE, BOB);

    @Test
    public void setTarget_firesUpdateChange_success() {
        MappedStudentViewList studentViews = new MappedStudentViewList(internalStudentList);

        // A bit hacky, but works.
        final ArrayList<ListChangeListener.Change<? extends StudentView>> triggeredChange = new ArrayList<>();
        studentViews.addListener((ListChangeListener.Change<? extends StudentView> change) -> {
            triggeredChange.add(change);
        });
        studentViews.setTarget(CS1231S, LAB_1);
        assertTrue(triggeredChange.size() == 1);

        ListChangeListener.Change<? extends StudentView> change = triggeredChange.get(0);
        assertTrue(!change.wasAdded() && !change.wasRemoved() && !change.wasReplaced() && change.wasUpdated()
                && !change.wasPermutated() && change.getFrom() == 0 && change.getTo() == internalStudentList.size());

    }

    @Test
    public void internalListModified_firesChange_success() {
        ObservableList<Student> otherInternalList = FXCollections.observableArrayList(ALICE);
        MappedStudentViewList studentViews = new MappedStudentViewList(otherInternalList);

        // A bit hacky, but works.
        final ArrayList<ListChangeListener.Change<? extends StudentView>> triggeredChange = new ArrayList<>();
        studentViews.addListener((ListChangeListener.Change<? extends StudentView> change) -> {
            triggeredChange.add(change);
        });
        otherInternalList.add(BOB);

        assertTrue(triggeredChange.size() == 1);

        ListChangeListener.Change<? extends StudentView> change = triggeredChange.get(0);
        assertTrue(change.next() && change.wasAdded() && !change.wasRemoved() && !change.wasRemoved()
                && !change.wasUpdated() && !change.wasPermutated());
    }

    @Test
    public void get_noTargetSet_success() {
        MappedStudentViewList studentViews = new MappedStudentViewList(internalStudentList);
        StudentView aliceView = new StudentView(ALICE);
        StudentView bobView = new StudentView(BOB);
        assertTrue(studentViews.get(0).equals(aliceView) && studentViews.get(1).equals(bobView));
    }

    @Test
    public void get_targetSet_success() {
        Student aliceWithSession = ALICE.updateGrade(CS1231S, LAB_1, 100.0);
        ObservableList<Student> otherInternalList = FXCollections.observableArrayList(aliceWithSession, BOB);
        MappedStudentViewList studentViews = new MappedStudentViewList(otherInternalList);
        studentViews.setTarget(CS1231S, LAB_1);

        StudentView aliceView = new StudentView(aliceWithSession).withSession(CS1231S, LAB_1);
        StudentView bobView = new StudentView(BOB).withSession(CS1231S, LAB_1);

        assertTrue(studentViews.get(0).equals(aliceView) && studentViews.get(1).equals(bobView));
    }
}
