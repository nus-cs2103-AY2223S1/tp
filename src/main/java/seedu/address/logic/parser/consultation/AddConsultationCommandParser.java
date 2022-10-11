package seedu.address.logic.parser.consultation;

import seedu.address.logic.commands.consultation.AddConsultationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.*;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

public class AddConsultationCommandParser  implements Parser<AddConsultationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddConsultationCommand
     * and returns an AddConsultationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddConsultationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE, PREFIX_TIMESLOT);

        ParserUtil.assertPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE, PREFIX_TIMESLOT);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultationCommand.MESSAGE_USAGE));
        }

        ConsultationName name = ParserUtil.parseConsultationName(argMultimap.getValue(PREFIX_NAME).get());
        ConsultationModule module = ParserUtil.parseConsultationModule(argMultimap.getValue(PREFIX_MODULE).get());
        ConsultationVenue venue = ParserUtil.parseConsultationVenue(argMultimap.getValue(PREFIX_VENUE).get());
        ConsultationTimeslot timeslot = ParserUtil.parseConsultationTimeslot(argMultimap.getValue(PREFIX_TIMESLOT).get());
        ConsultationDescription description = ParserUtil.parseConsultationDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Consultation Consultation = new Consultation(name, module, venue, timeslot, description);

        return new AddConsultationCommand(Consultation);
    }
}
