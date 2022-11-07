package seedu.modquik.logic.parser.consultation;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.modquik.logic.commands.consultation.AddConsultationCommand;
import seedu.modquik.logic.parser.ArgumentMultimap;
import seedu.modquik.logic.parser.ArgumentTokenizer;
import seedu.modquik.logic.parser.Parser;
import seedu.modquik.logic.parser.ParserUtil;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.commons.Venue;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.consultation.ConsultationDescription;
import seedu.modquik.model.consultation.ConsultationName;
import seedu.modquik.model.datetime.DatetimeCommonUtils;
import seedu.modquik.model.datetime.DatetimeRange;

/**
 * Parses input arguments and creates a new AddConsultationCommand object
 */
public class AddConsultationCommandParser implements Parser<AddConsultationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddConsultationCommand
     * and returns an AddConsultationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddConsultationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE, PREFIX_DATE_DAY, PREFIX_TIME,
                PREFIX_DESCRIPTION);

        ParserUtil.assertAllPrefixesPresent(
                argMultimap, AddConsultationCommand.MESSAGE_USAGE,
                PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE, PREFIX_DATE_DAY, PREFIX_TIME, PREFIX_DESCRIPTION);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddConsultationCommand.MESSAGE_USAGE));
        }

        ConsultationName name = ConsultationParserUtil.parseConsultationName(
                argMultimap.getValue(PREFIX_NAME).get());
        ModuleCode module = ParserUtil.parseModuleCode(
                argMultimap.getValue(PREFIX_MODULE).get());
        Venue venue = ParserUtil.parseVenue(
                argMultimap.getValue(PREFIX_VENUE).get());
        ConsultationDescription description = ConsultationParserUtil.parseConsultationDescription(
                argMultimap.getValue(PREFIX_DESCRIPTION).get());

        String date = argMultimap.getValue(PREFIX_DATE_DAY).get();
        String timeslotString = argMultimap.getValue(PREFIX_TIME).get();
        DatetimeRange timeslot = DatetimeCommonUtils.parseDatetimeRange(date, timeslotString);


        Consultation consultation = new Consultation(name, module, venue, timeslot, description);

        return new AddConsultationCommand(consultation);
    }
}
