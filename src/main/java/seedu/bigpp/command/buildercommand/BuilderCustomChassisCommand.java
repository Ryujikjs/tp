package seedu.bigpp.command.buildercommand;

import seedu.bigpp.component.chassis.Chassis;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

public class BuilderCustomChassisCommand extends BuilderCustomComponentCommand {

    public BuilderCustomChassisCommand(String arguments) {
        super(arguments);
    }

    public int getExpectedArgumentLength() {
        return 5;
    }

    public String addNewComponent(String[] argumentList, DataStorage dataStorage, String name, String brand)
            throws PPException {
        float price = 0;

        try {
            price = Float.parseFloat(argumentList[2]);

            // Check if all the values are positive
            if (price < 0) {
                throw new PPException("price should be positive");
            }

        } catch (NumberFormatException e) {
            throw new PPException(
                    "price should be a float");
        }

        String size = argumentList[3].trim();
        if (size.equals("")) {
            throw new PPException("Please enter a valid size for the custom component");
        }
        Chassis chassis = new Chassis(name, brand, price, size);

        dataStorage.pcList.get(UI.pcBuilderMenu.getPCIndex()).setChassis(chassis);

        return "Chassis added:" + chassis.getName();
    }
}