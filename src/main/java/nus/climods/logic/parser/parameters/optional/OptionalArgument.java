package nus.climods.logic.parser.parameters.optional;

import org.apache.commons.cli.Option;

public class OptionalArgument {
    protected Option option;

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
