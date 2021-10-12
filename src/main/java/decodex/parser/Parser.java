package decodex.parser;

import decodex.commands.Command;
import decodex.commands.DataCommand;
import decodex.commands.ExitCommand;
import decodex.commands.ListCommand;
import decodex.commands.ResetCommand;
import decodex.commands.SelectCommand;
import decodex.data.exception.ParserException;

import java.util.Arrays;

/**
 * Parses and validates the user input.
 */
public class Parser {

    /**
     * Specifies the index of the tokens where specific parameters can be found.
     */
    private static final int COMMAND_INDEX = 0;
    private static final int MODULE_NAME_INDEX_IN_TOKENS = 0;


    /**
     * Specifies the starting index where the arguments can be found.
     */
    private static final int STARTING_ARGUMENTS_INDEX = 1;

    /**
     * Specifies the valid length of the tokens and used to check validity of tokens.
     */
    private static final int VALID_TOKENS_LENGTH_FOR_COMMAND = 1;

    /**
     * Returns the type of command that user has entered.
     *
     * @param userInput The input specified by the user.
     * @return The type of command.
     */
    public String getCommandType(String userInput) throws ParserException {
        String strippedUserInput = userInput.stripLeading();
        if (strippedUserInput.isEmpty()) {
            throw new ParserException(ParserException.MISSING_COMMAND_TYPE_MESSAGE);
        }

        String[] tokens = strippedUserInput.split(" ");

        boolean isInvalidTokensLength = tokens.length < VALID_TOKENS_LENGTH_FOR_COMMAND;

        if (isInvalidTokensLength) {
            throw new ParserException(ParserException.MISSING_COMMAND_TYPE_MESSAGE);
        }

        String commandType = tokens[COMMAND_INDEX];
        assert !commandType.isEmpty() : "Command Type should not be empty";
        return commandType;
    }

    public String getModuleName(String userInput) throws ParserException {
        String strippedUserInput = userInput.stripLeading();
        String[] tokens = strippedUserInput.split(" ", -1);

        String[] argumentTokens = Arrays.stream(Arrays.copyOfRange(tokens, STARTING_ARGUMENTS_INDEX, tokens.length))
                .filter(value -> !value.isBlank())
                .toArray(size -> new String[size]);
        if (argumentTokens.length == 0) {
            throw new ParserException("[-] Module name is missing");
        }
        return argumentTokens[MODULE_NAME_INDEX_IN_TOKENS];
    }

    public String getInputString(String userInput) throws ParserException {
        String strippedUserInput = userInput.stripLeading();
        String[] tokens = strippedUserInput.split(" ", -1);

        String argumentString = String.join(" ",
                Arrays.copyOfRange(tokens, STARTING_ARGUMENTS_INDEX, tokens.length));
        if (argumentString.isEmpty()) {
            throw new ParserException("[-] Your input is empty, please do \"input i am a string\"");
        }
        return argumentString;
    }

    /**
     * Parses the user input provided by user and returns its respective command. Returns a null object if the command
     * is invalid.
     *
     * @param userInput The user input specified by the user.
     * @return The respective Command object.
     */
    public Command parseCommand(String userInput) throws ParserException {
        Command command;
        String commandType = getCommandType(userInput);

        switch (commandType) {
        case ExitCommand.COMMAND_WORD:
            command = craftExitCommand();
            break;
        case DataCommand.COMMAND_WORD:
            command = craftDataCommand(userInput);
            break;
        case ListCommand.COMMAND_WORD:
            command = craftListCommand();
            break;
        case ResetCommand.COMMAND_WORD:
            command = craftResetCommand();
            break;
        case SelectCommand.COMMAND_WORD:
            command = craftSelectCommand(userInput);
            break;
        default:
            throw new ParserException("[x] Unknown command, please enter a valid command");
        }
        return command;
    }

    /**
     * Crafts and returns the Exit Command.
     *
     * @return The Exit command.
     */
    private Command craftExitCommand() {
        return new ExitCommand();
    }

    /**
     * Crafts and returns the Data Command given the user input.
     *
     * @param userInput The user input specified by the user.
     * @return The Data command
     * @throws ParserException ParserException
     */
    private Command craftDataCommand(String userInput) throws ParserException {
        String inputData = getInputString(userInput);
        return new DataCommand(inputData);
    }

    /**
     * Crafts and returns the List Command.
     *
     * @return The List command.
     */
    private Command craftListCommand() {
        return new ListCommand();
    }

    /**
     * Crafts and returns the Reset Command.
     *
     * @return The Reset command.
     */
    private Command craftResetCommand() {
        return new ResetCommand();
    }

    /**
     * Crafts and returns the Select Command.
     *
     * @param userInput The user input specified by the user.
     * @return The Select command.
     * @throws ParserException ParserException
     */
    private Command craftSelectCommand(String userInput) throws ParserException {
        String moduleName = getModuleName(userInput);
        return new SelectCommand(moduleName);
    }

}
