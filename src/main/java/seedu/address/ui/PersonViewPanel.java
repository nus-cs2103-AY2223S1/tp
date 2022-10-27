package seedu.address.ui;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;

/**
 * Panel which has the detailed view of a patient.
 */
public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";

    private final Person person;

    private CommandTextEditor commandTextEditor;

    @FXML
    private Label name;

    @FXML
    private Label email;

    @FXML
    private Label phone;

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
        name.setOnMouseClicked(this::editField);

        email.setText(person.getEmail().toString());
        phone.setText(person.getPhone().toString());
        ContactCard contactCard = new ContactCard(person);
        nokName.setText("Name: " + person.getNextOfKin().getNextOfKinName());
        nokRelationship.setText("Relationship: " + person.getNextOfKin().getNextOfKinRelationship());
        nokContact.setText("Contact: " + person.getNextOfKin().getNextOfKinContact());
        if (person.getMedications().size() > 0) {
            medications.getChildren().add(new Label(person.getMedicationString()));
        }
    }

    private void setHospitalisationDetails() {
        patientType.setText(person.getPatientType().toString());
        person.getHospitalWing().ifPresentOrElse(hw -> hospitalWing.setText(hw.toString()), () ->
                hospitalWing.setVisible(false));
        person.getFloorNumber().ifPresentOrElse(fn -> floorNumber.setText(fn.toString()), () ->
                floorNumber.setVisible(false));
        person.getWardNumber().ifPresentOrElse(wn -> wardNumber.setText(wn.toString()), () ->
                wardNumber.setVisible(false));
    }

    private void setAppointmentDetails() {
        upcomingAppointment.setText(person.getUpcomingAppointment().get().toString());
        ObservableList<PastAppointment> pastAppointmentsObservableList =
                new ObservableListWrapper<>(person.getPastAppointments());
        pastAppointments.setItems(pastAppointmentsObservableList);
        pastAppointments.setCellFactory(item -> new PastAppointmentListViewCell());
    }

    private void editField(Event event) {
        System.out.println(event.getTarget());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PastAppointmentListViewCell extends ListCell<PastAppointment> {
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
     * Represents a function to change the command box text.
     */
    @FunctionalInterface
    public interface CommandTextEditor {
        /**
         * Sets the command box text.
         * @param str the string to set the command box to.
         * @see seedu.address.ui.CommandBox#setCommandTextField(String)
         */
        void setCommandText(String str);
    }
}
