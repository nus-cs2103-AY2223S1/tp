package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CRITICAL_ILLNESS_INSURANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISABILITY_INSURANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEALTH_INSURANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIFE_INSURANCE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InsuranceCommand;
import seedu.address.logic.commands.InsuranceCommand.EditInsuranceDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CriticalIllnessInsurance;
import seedu.address.model.person.DisabilityInsurance;
import seedu.address.model.person.HealthInsurance;
import seedu.address.model.person.LifeInsurance;

/**
 * Parses input arguments and creates a new InsuranceCommand object
 */
public class InsuranceCommandParser implements Parser<InsuranceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InsuranceCommand
     * and returns an InsuranceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InsuranceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_HEALTH_INSURANCE, PREFIX_DISABILITY_INSURANCE,
                        PREFIX_CRITICAL_ILLNESS_INSURANCE, PREFIX_LIFE_INSURANCE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InsuranceCommand.MESSAGE_USAGE), pe);
        }

        EditInsuranceDescriptor editInsuranceDescriptor = new EditInsuranceDescriptor();

        if (argMultimap.getValue(PREFIX_HEALTH_INSURANCE).isPresent()) {
            if (!argMultimap.getValue(PREFIX_HEALTH_INSURANCE).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InsuranceCommand.MESSAGE_USAGE));
            }
            editInsuranceDescriptor.setHealthInsurance(new HealthInsurance(true));
        }
        if (argMultimap.getValue(PREFIX_DISABILITY_INSURANCE).isPresent()) {
            if (!argMultimap.getValue(PREFIX_DISABILITY_INSURANCE).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InsuranceCommand.MESSAGE_USAGE));
            }
            editInsuranceDescriptor.setDisabilityInsurance(new DisabilityInsurance(true));
        }
        if (argMultimap.getValue(PREFIX_CRITICAL_ILLNESS_INSURANCE).isPresent()) {
            if (!argMultimap.getValue(PREFIX_CRITICAL_ILLNESS_INSURANCE).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InsuranceCommand.MESSAGE_USAGE));
            }
            editInsuranceDescriptor.setCriticalIllnessInsurance(new CriticalIllnessInsurance(true));
        }
        if (argMultimap.getValue(PREFIX_LIFE_INSURANCE).isPresent()) {
            if (!argMultimap.getValue(PREFIX_LIFE_INSURANCE).get().trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InsuranceCommand.MESSAGE_USAGE));
            }
            editInsuranceDescriptor.setLifeInsurance(new LifeInsurance(true));
        }

        return new InsuranceCommand(index, editInsuranceDescriptor);
    }
}
