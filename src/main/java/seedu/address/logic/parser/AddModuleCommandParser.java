package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE_ZOOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_ZOOM;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.nusmodules.NusModulesParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignmentdetails.AssignmentDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    private final NusModulesParser nusModulesParser;

    public AddModuleCommandParser(NusModulesParser nusModulesParser) {
        this.nusModulesParser = nusModulesParser;
    }
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args,
                PREFIX_MODULE, PREFIX_LECTURE, PREFIX_TUTORIAL,
                    PREFIX_LECTURE_ZOOM, PREFIX_TUTORIAL_ZOOM, PREFIX_ASSIGNMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get());
        String moduleTitle = nusModulesParser.getModuleTitle(moduleCode.moduleCode);
        moduleCode.setModuleTitle(moduleTitle);
        LectureDetails lecture = ParserUtil.parseLectureDetails(argMultimap.getValue(PREFIX_LECTURE).orElse(null));
        TutorialDetails tutorial = ParserUtil.parseTutorialDetails(argMultimap.getValue(PREFIX_TUTORIAL)
                .orElse(null));
        ZoomLink lectureZoom = ParserUtil.parseZoomLink(argMultimap.getValue(PREFIX_LECTURE_ZOOM).orElse(null));
        ZoomLink tutorialZoom = ParserUtil.parseZoomLink(argMultimap.getValue(PREFIX_TUTORIAL_ZOOM).orElse(null));
        Set<AssignmentDetails> assignmentList =
            ParserUtil.parseAssignmentDetails(argMultimap.getAllValues(PREFIX_ASSIGNMENT));

        Module module = new Module(moduleCode, lecture, tutorial, lectureZoom, tutorialZoom, assignmentList);

        return new AddModuleCommand(module);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
