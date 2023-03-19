package seedu.bigpp.parser;

import seedu.bigpp.command.Command;
import seedu.bigpp.command.buildercommand.BuilderCustomCpuCommand;
import seedu.bigpp.command.buildercommand.BuilderEditBudgetCommand;
import seedu.bigpp.command.buildercommand.BuilderEditNameCommand;
import seedu.bigpp.command.buildercommand.BuilderInfoCommand;
import seedu.bigpp.command.buildercommand.BuilderListComponentCommand;
import seedu.bigpp.command.buildercommand.BuilderSelectCommand;
import seedu.bigpp.command.buildercommand.BuilderUnselectCommand;
import seedu.bigpp.command.commoncommand.BackCommand;
import seedu.bigpp.command.commoncommand.ByeCommand;
import seedu.bigpp.command.commoncommand.UnrecognizedCommand;
import seedu.bigpp.command.viewercommand.ViewerAddCommand;
import seedu.bigpp.command.viewercommand.ViewerDeleteCommand;
import seedu.bigpp.command.viewercommand.ViewerEditCommand;
import seedu.bigpp.command.viewercommand.ViewerViewCommand;
import seedu.bigpp.ui.UI;
import static seedu.bigpp.component.ComponentType.CHASSIS_TYPE;
import static seedu.bigpp.component.ComponentType.CPU_TYPE;
import static seedu.bigpp.component.ComponentType.CPU_COOLER_TYPE;
import static seedu.bigpp.component.ComponentType.GPU_TYPE;
import static seedu.bigpp.component.ComponentType.MOTHERBOARD_TYPE;
import static seedu.bigpp.component.ComponentType.PSU_TYPE;
import static seedu.bigpp.component.ComponentType.RAM_TYPE;
import static seedu.bigpp.component.ComponentType.STORAGE_TYPE;

public class Parser {

    public Command parseCommand(String userInput) {

        String trimmedInput = userInput.trim();
        String[] inputList = trimmedInput.split(" ", 2);
        String arguments = "";

        if (inputList.length == 2) {
            arguments = inputList[1];
            arguments = arguments.trim();
        }
        String commandWord = inputList[0];
        commandWord = commandWord.toLowerCase();
        commandWord = commandWord.trim();

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
            return parseViewerCommand(commandWord, arguments);

        case PCBUILDER:
            return parseBuilderCommand(commandWord, arguments);

        default:
            return new UnrecognizedCommand("");
        }
    }

    private Command parseViewerCommand(String commandWord, String arguments) {

        switch (commandWord) {
        case "add":
            return new ViewerAddCommand(arguments);
        case "delete":
            return new ViewerDeleteCommand(arguments);
        case "view":
            return new ViewerViewCommand(arguments);
        case "edit":
            return new ViewerEditCommand(arguments);
        default:
            return new UnrecognizedCommand("");
        }
    }

    private Command parseBuilderCommand(String commandWord, String arguments) {

        switch (commandWord) {
        case "list":
            return new BuilderListComponentCommand(arguments);
        case "name":
            return new BuilderEditNameCommand(arguments);
        case "budget":
            return new BuilderEditBudgetCommand(arguments);
        case "select":
            return new BuilderSelectCommand(arguments);
        case "info":
            return new BuilderInfoCommand(arguments);
        case "unselect":
            return new BuilderUnselectCommand(arguments);
        case "custom":
            return parseCustomCommand(arguments);
        default:
            return new UnrecognizedCommand("");
        }
    }

    private Command parseCustomCommand(String arguments) {
        String[] inputList = arguments.split(" ", 2);
        String componentType = inputList[0];
        String attributes = "";
        if (inputList.length == 2) {
            attributes = inputList[1];
        }

        switch (componentType) {
        case CPU_TYPE:
            return new BuilderCustomCpuCommand(attributes);
        case GPU_TYPE:
            // return new UnrecognizedCommand();
        case RAM_TYPE:
            // return new UnrecognizedCommand();
        case STORAGE_TYPE:
            // return new UnrecognizedCommand();
        case PSU_TYPE:
            // return new UnrecognizedCommand();
        case MOTHERBOARD_TYPE:
            // return new UnrecognizedCommand();
        case CHASSIS_TYPE:
            // return new UnrecognizedCommand();
        case CPU_COOLER_TYPE:
            // return new UnrecognizedCommand();
        default:
            return new UnrecognizedCommand(
                    "Invalid component type!, valid types are "
                            + "(cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)");
        }
    }
}
