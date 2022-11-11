package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PatientType;
import seedu.address.model.tag.Medication;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NEXT_OF_KIN,
                        PREFIX_PATIENT_TYPE, PREFIX_HOSPITAL_WING, PREFIX_FLOOR_NUMBER,
                        PREFIX_WARD_NUMBER, PREFIX_MEDICATION, PREFIX_UPCOMING_APPOINTMENT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_NEXT_OF_KIN).isPresent()) {
            editPersonDescriptor.setNextOfKin(ParserUtil.parseNextOfKin(
                    argMultimap.getValue(PREFIX_NEXT_OF_KIN).get()));
        }
        if (argMultimap.getValue(PREFIX_PATIENT_TYPE).isPresent()) {
            editPersonDescriptor.setPatientType(ParserUtil.parsePatientType(
                    argMultimap.getValue(PREFIX_PATIENT_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_HOSPITAL_WING).isPresent()) {
            editPersonDescriptor.setHospitalWing(ParserUtil.parseHospitalWing(
                    argMultimap.getValue(PREFIX_HOSPITAL_WING).get()));
        }
        if (argMultimap.getValue(PREFIX_FLOOR_NUMBER).isPresent()) {
            editPersonDescriptor.setFloorNumber(ParserUtil.parseFloorNumber(
                    argMultimap.getValue(PREFIX_FLOOR_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_WARD_NUMBER).isPresent()) {
            editPersonDescriptor.setWardNumber(ParserUtil.parseWardNumber(
                    argMultimap.getValue(PREFIX_WARD_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_UPCOMING_APPOINTMENT).isPresent()) {
            editPersonDescriptor.setUpcomingAppointment(ParserUtil.parseUpcomingAppointment(
                    argMultimap.getValue(PREFIX_UPCOMING_APPOINTMENT).get()));
        }

        /*
        If the changed person's patient type outpatient, it cannot have Hospital Wing, Floor Number or Ward Number.
         */
        if ((editPersonDescriptor.getPatientType().isPresent()
                && !editPersonDescriptor.getPatientType().get().isInpatient())
                && (editPersonDescriptor.getHospitalWing().isPresent()
                || editPersonDescriptor.getFloorNumber().isPresent()
                || editPersonDescriptor.getWardNumber().isPresent())) {
            throw new ParseException(String.format(PatientType.DEPENDENCY_CONSTRAINTS, EditCommand.MESSAGE_USAGE));
        }

        /*
        If the changed person's patient type inpatient, it must have Hospital Wing, Floor Number or Ward Number.
         */
        if ((editPersonDescriptor.getPatientType().isPresent()
                && editPersonDescriptor.getPatientType().get().isInpatient())
                && (editPersonDescriptor.getHospitalWing().isEmpty()
                || editPersonDescriptor.getFloorNumber().isEmpty()
                || editPersonDescriptor.getWardNumber().isEmpty())) {
            throw new ParseException(String.format(PatientType.DEPENDENCY_CONSTRAINTS, EditCommand.MESSAGE_USAGE));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_MEDICATION)).ifPresent(editPersonDescriptor::setMedications);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Medication>> parseTagsForEdit(Collection<String> medications) throws ParseException {
        assert medications != null;

        if (medications.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> medicationSet = medications.size() == 1 && medications.contains("")
                ? Collections.emptySet() : medications;
        return Optional.of(ParserUtil.parseMedications(medicationSet));
    }

}
