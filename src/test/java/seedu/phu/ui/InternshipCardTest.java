package seedu.phu.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import seedu.phu.model.internship.Internship;
import seedu.phu.testutil.InternshipBuilder;

public class InternshipCardTest extends GuiUnitTest {
    private static final int TEST_INDEX = 0;
    // ids to handle fxml query
    private static final String NAME_ID = "#name";
    private static final String INDEX_ID = "#id";
    private static final String POSITION_ID = "#position";
    private static final String APPLICATION_PROCESS_ID = "#applicationProcess";
    private static final String DATE_ID = "#date";
    private static final String TAGS_ID = "#tags";

    @Test
    public void initializeInternshipCard_success() {
        Internship internship = new InternshipBuilder().build();
        InternshipCard internshipCard = new InternshipCard(internship, TEST_INDEX);
        uiPartExtension.setUiPart(internshipCard);
        checkDisplay(internshipCard, internship, TEST_INDEX);
    }

    @Test
    public void equals() {
        Internship internship = new InternshipBuilder().build();
        InternshipCard internshipCard = new InternshipCard(internship, 0);

        // same object -> returns true
        assertTrue(internshipCard.equals(internshipCard));

        // same internship, same index -> returns true
        InternshipCard copy = new InternshipCard(internship, 0);
        assertTrue(internshipCard.equals(copy));

        // null -> returns false
        assertFalse(internshipCard.equals(null));

        // different types -> returns false
        assertFalse(internshipCard.equals(1));

        // same internship, different index -> returns true
        InternshipCard distinctInternshipCard = new InternshipCard(internship, 1);
        assertFalse(internshipCard.equals(distinctInternshipCard));

        // different internship, same index -> returns false
        Internship differentInternship = new InternshipBuilder().withName("randomCompany").build();
        InternshipCard anotherDistinctInternshipCard = new InternshipCard(differentInternship, 1);
        assertFalse(internshipCard.equals(anotherDistinctInternshipCard));
    }

    private void checkDisplay(InternshipCard actualInternship, Internship expectedInternship, int expectedIndex) {
        Node actualInternshipNode = actualInternship.getRoot();
        Label internshipId = getChildNode(actualInternshipNode, INDEX_ID);
        Label internshipName = getChildNode(actualInternshipNode, NAME_ID);
        Label internshipPosition = getChildNode(actualInternshipNode, POSITION_ID);
        Label internshipApplicationProcess = getChildNode(actualInternshipNode, APPLICATION_PROCESS_ID);
        Label internshipDate = getChildNode(actualInternshipNode, DATE_ID);
        FlowPane internshipTags = getChildNode(actualInternshipNode, TAGS_ID);

        assertEquals(internshipId.getText(), Integer.toString(expectedIndex) + ". ");
        assertEquals(internshipName.getText(), expectedInternship.getName().toString());
        assertEquals(internshipPosition.getText(), expectedInternship.getPosition().toString());
        assertEquals(internshipApplicationProcess.getText(), expectedInternship.getApplicationProcess().toString());
        assertEquals(internshipDate.getText(), expectedInternship.getDate().toDisplayFormat());
        assertEquals(internshipTags.getChildrenUnmodifiable().stream()
                        .map(Label.class::cast).collect(Collectors.toList()),
                expectedInternship.getTags().stream().map(tag -> tag.toString())
                        .sorted().collect(Collectors.toList()));
    }
}
