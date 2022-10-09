package nus.climods.logic.parser.parameters.optional;

import org.apache.commons.cli.Option;

/**
 * Class representing one OptionalArgument e.g --user
 * Currently uses commons.cli.Option internally to represent an option
 */
public class OptionalArgument {
    protected Option option;

    /**
     * Creates an OptionalArgument
     * @param shortOption Short option name
     * @param longOption Long option name
     */
    public OptionalArgument(String shortOption, String longOption) {
        option = Option
                .builder()
                .option(shortOption)
                .longOpt(longOption)
                .build();
    }

    protected Option getOption() {
        return option;
    }
}
