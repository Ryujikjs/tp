package seedu.bigpp.parser;

import seedu.bigpp.command.Command;
import seedu.bigpp.command.commoncommand.BackCommand;
import seedu.bigpp.command.commoncommand.ByeCommand;
import seedu.bigpp.command.viewercommand.ViewerDeleteCommand;
import seedu.bigpp.command.viewercommand.ViewerViewCommand;
import seedu.bigpp.ui.UI;

import seedu.bigpp.command.viewercommand.ViewerAddCommand;

public class Parser {

    public Command parseCommand(String userInput) {

        // try common commands first
        if (userInput.equals("back")) {
            return new BackCommand();
        }

        if (userInput.equals("bye")) {
            return new ByeCommand();
        }

        // once common commands are tried, try menu specific commands
        switch (UI.getUiState()) {

        case PCVIEWER:
            return parseViewerCommand(userInput);

        case PCBUILDER:
            return parseBuilderCommand(userInput);

        default:
            return null;
        }
    }

    private Command parseViewerCommand(String userInput) {
        String commandWord = userInput.split(" ")[0];
        String arguments = userInput.split(" ")[1];

        commandWord = commandWord.toLowerCase();
        switch (commandWord) {
        case "add":
            return new ViewerAddCommand(arguments);
        case "delete":
            return new ViewerDeleteCommand(arguments);
        case "view":
            return new ViewerViewCommand(arguments);
        default:
            return null;
        }
    }

    private Command parseBuilderCommand(String userInput) {
        String commandWord = userInput.split(" ")[0];

        commandWord = commandWord.toLowerCase();
        return null;
    }
}
