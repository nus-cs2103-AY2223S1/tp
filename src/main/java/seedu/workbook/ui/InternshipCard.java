package seedu.workbook.ui;


import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Stage;
import seedu.workbook.model.internship.util.StageUtil;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipListCard.fxml";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    //Independent tips window residing in each internship list card controller
    private TipsWindow tipsWindow;

    private final Stage internshipStage;

    @FXML
    private HBox cardPane;
    @FXML
    private Label company;
    @FXML
    private Label role;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label stage;
    @FXML
    private Label dateTime;
    @FXML
    private FlowPane tags;
    @FXML
    private Button tipsButton;


    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        this.internshipStage = internship.getStage();
        boolean hasNoTips = internshipStage.hasNoTips();

        if (hasNoTips) {
            tipsButton.setVisible(false);
        }

        id.setText(displayedIndex + ". ");
        company.setText(internship.getCompany().name);
        role.setText(internship.getRole().value);
        email.setText(internship.getEmail().value);
        stage.setText(internshipStage.value);
        dateTime.setText(internship.getDateTime().value);
        internship.getLanguageTags().stream()
                .sorted(Comparator.comparing(languageTag -> languageTag.tagName))
                .forEach(languageTag -> tags.getChildren().add(new LanguageTagLabel(languageTag.tagName)));
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        tipsWindow = new TipsWindow();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipCard)) {
            return false;
        }

        // state check
        InternshipCard card = (InternshipCard) other;
        return id.getText().equals(card.id.getText())
                && internship.equals(card.internship);
    }

    /**
     * Opens the tip window depending on the internship stage.
     */
    @FXML
    private void showTips() {
        //Check if tips window is showing.
        if (!tipsWindow.isShowing()) {
            //Do not populate tips window for stages that have no stage-specific tips.
            if (this.internshipStage.hasNoTips()) {
                return;
            }

            //Set header of tips to be name of stage.
            tipsWindow.setTipsHeader(this.internshipStage.value);

            List<String> tips = StageUtil.getStageSpecificTips(this.internshipStage);

            tipsWindow.populateTips(tips);
            tipsWindow.show();
        } else {
            tipsWindow.focus();
        }
    }
}
