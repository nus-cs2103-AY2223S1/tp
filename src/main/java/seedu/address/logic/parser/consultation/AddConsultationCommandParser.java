package seedu.address.logic.parser.consultation;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.logic.commands.consultation.AddConsultationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationModule;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.consultation.ConsultationVenue;
import seedu.address.model.datetime.DatetimeRange;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE, PREFIX_TIMESLOT,
                        PREFIX_DESCRIPTION);

        ParserUtil.assertPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE, PREFIX_TIMESLOT,
                PREFIX_DESCRIPTION);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddConsultationCommand.MESSAGE_USAGE));
        }

        ConsultationName name = ParserUtil.parseConsultationName(argMultimap.getValue(PREFIX_NAME).get());
        ConsultationModule module = ParserUtil.parseConsultationModule(argMultimap.getValue(PREFIX_MODULE).get());
        ConsultationVenue venue = ParserUtil.parseConsultationVenue(argMultimap.getValue(PREFIX_VENUE).get());
        ConsultationDescription description = ParserUtil.parseConsultationDescription(
                argMultimap.getValue(PREFIX_DESCRIPTION).get());

        DatetimeRange timeslot = ParserUtil.parseDatetimeRange(
                argMultimap.getValue(PREFIX_TIMESLOT).get());


        Consultation consultation = new Consultation(name, module, venue, timeslot, description);

        return new AddConsultationCommand(consultation);
    }
}
