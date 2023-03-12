package seedu.bigpp;

import seedu.bigpp.command.Command;
import seedu.bigpp.command.commoncommand.ByeCommand;
import seedu.bigpp.command.commoncommand.DoNothingCommand;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.parser.Parser;
import seedu.bigpp.ui.UI;

public class BigPP {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new BigPP().run();
    }

    public void run() {
        DataStorage.loadAll();
        UI.showWelcome();
        UI.updateUI(true);
        runLoopUntilExit();
    }

    /**
     * Runs the program until the user issues the exit command.
     */
    private void runLoopUntilExit() {
        Command command = new DoNothingCommand();
        do {
            String userInput = UI.getInput();
            try {
                command = new Parser().parseCommand(userInput);
            } catch (Exception e) {
                UI.showResult(e.getMessage());
                continue;
            }
            String result = command.executeCommand();
            UI.updateUI(false);
            UI.showResult(result);
        } while (!(command instanceof ByeCommand));
    }

}
