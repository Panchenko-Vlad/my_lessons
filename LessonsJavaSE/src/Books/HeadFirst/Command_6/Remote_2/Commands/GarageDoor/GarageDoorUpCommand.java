package Books.HeadFirst.Command_6.Remote_2.Commands.GarageDoor;

import Books.HeadFirst.Command_6.Remote_2.Commands.Command;

public class GarageDoorUpCommand implements Command {
	GarageDoor garageDoor;

	public GarageDoorUpCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}

	public void execute() {
		garageDoor.up();
	}
}
