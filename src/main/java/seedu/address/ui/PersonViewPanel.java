package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLOOR_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOSPITAL_WING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPCOMING_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD_NUMBER;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;

/**
 * Panel which has the detailed view of a patient.
 */
public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";

    private final Person person;

    private final Image identificationImage = new Image(this.getClass()
            .getResourceAsStream("/images/ui/identification-card.png"));
    private final Image helplineImage = new Image(this.getClass().getResourceAsStream("/images/ui/help-line.png"));
    private final Image pillImage = new Image(this.getClass().getResourceAsStream("/images/ui/pills.png"));
    private final Image hospitalbedImage = new Image(this.getClass()
            .getResourceAsStream("/images/ui/hospital-bed.png"));
    private final Image calendarImage = new Image(this.getClass().getResourceAsStream("/images/ui/calendar.png"));
    private CommandTextEditor commandTextEditor;

    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private VBox nok;

    @FXML
    private Label nokName;
    @FXML
    private Label nokRelationship;
    @FXML
    private Label nokContact;
    @FXML
    private Label patientType;
    @FXML
    private Label hospitalWing;
    @FXML
    private Label floorNumber;
    @FXML
    private Label wardNumber;
    @FXML
    private Label upcomingAppointment;
    @FXML
    private ListView<PastAppointment> pastAppointments;
    @FXML
    private FlowPane medications;
    @FXML
    private ImageView identificationIcon;
    @FXML
    private ImageView helplineIcon;
    @FXML
    private ImageView pillIcon;
    @FXML
    private ImageView hospitalbedIcon;
    @FXML
    private ImageView calendarIcon;

    /**
     * Generates a Person View Panel.
     * @param person Person to generate the panel about.
     */
    public PersonViewPanel(Person person, CommandTextEditor commandTextEditor) {
        super(FXML);
        if (person == null) {
            this.person = null;
            return;
        }
        this.person = person;
        this.commandTextEditor = commandTextEditor;
        setPersonalDetails();
        setHospitalisationDetails();
        setAppointmentDetails();
    }

    private void setPersonalDetails() {
        name.setText(person.getName().fullName);
        name.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_NAME));
        email.setText(person.getEmail().toString());
        email.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_EMAIL));
        phone.setText(person.getPhone().toString());
        phone.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_PHONE));
        nokName.setText("Name: " + person.getNextOfKin().getNextOfKinName());
        nokRelationship.setText("Relationship: " + person.getNextOfKin().getNextOfKinRelationship());
        nokContact.setText("Contact: " + person.getNextOfKin().getNextOfKinContact());
        nok.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_NEXT_OF_KIN));
        if (person.getMedications().size() > 0) {
            medications.getChildren().add(new Label(person.getMedicationString()));
            medications.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_MEDICATION));
        }
        identificationIcon.setImage(identificationImage);
        helplineIcon.setImage(helplineImage);
        pillIcon.setImage(pillImage);
    }

    private void setHospitalisationDetails() {
        patientType.setText(person.getPatientType().toString());
        hospitalbedIcon.setImage(hospitalbedImage);
        patientType.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_PATIENT_TYPE));
        person.getHospitalWing().ifPresentOrElse(hw -> {
            hospitalWing.setText(hw.toString());
            hospitalWing.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_HOSPITAL_WING));
        }, () -> hospitalWing.setVisible(false));
        person.getFloorNumber().ifPresentOrElse(fn -> {
            floorNumber.setText(fn.toString());
            floorNumber.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_FLOOR_NUMBER));
        }, () -> floorNumber.setVisible(false));
        person.getWardNumber().ifPresentOrElse(wn -> {
            wardNumber.setText(wn.toString());
            wardNumber.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_WARD_NUMBER));
        }, () -> wardNumber.setVisible(false));
    }

    private void setAppointmentDetails() {
        person.getUpcomingAppointment().ifPresentOrElse(ua -> {
            upcomingAppointment.setText(ua.toString());
            upcomingAppointment.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_UPCOMING_APPOINTMENT));
        }, () -> upcomingAppointment.setVisible(false));
        ObservableList<PastAppointment> pastAppointmentsObservableList =
                new ObservableListWrapper<>(person.getPastAppointments());
        pastAppointments.setItems(pastAppointmentsObservableList);
        pastAppointments.setCellFactory(item -> new PastAppointmentListViewCell());
        calendarIcon.setImage(calendarImage);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PastAppointmentListViewCell extends ListCell<PastAppointment> {
        @Override
        protected void updateItem(PastAppointment pastAppointment, boolean empty) {
            super.updateItem(pastAppointment, empty);

            if (empty || pastAppointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PastAppointmentCard(pastAppointment, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Represents a function that can change the text in the command box.
     */
    @FunctionalInterface
    public interface CommandTextEditor {
        /**
         * Changes the command box text.
         *
         * @see seedu.address.ui.CommandBox#setCommandTextField(String)
         */
        void editText(Prefix prefix);
    }
}
