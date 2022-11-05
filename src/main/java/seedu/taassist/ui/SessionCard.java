package seedu.taassist.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.taassist.model.session.Session;

/**
 * An UI component that displays information of a {@code Session}.
 */
public class SessionCard extends UiPart<Region> {

    private static final String FXML = "SessionCard.fxml";

    public final Session session;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;

    @FXML
    private Label sessionDate;

    /**
     * Creates a {@code SessionCode} with the given {@code Session} and index to display.
     */
    public SessionCard(Session session) {
        super(FXML);
        this.session = session;
        name.setText(session.getSessionName());
        sessionDate.setText(session.getDate().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SessionCard)) {
            return false;
        }

        // state check
        SessionCard card = (SessionCard) other;
        return session.equals(card.session);
    }
}
