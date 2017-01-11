package designpattern.behavioral.command;

public class WindowUpCommand implements Command {
    private ElectricWindow electricWindow;

    public WindowUpCommand(ElectricWindow electricWindow) {
        this.electricWindow = electricWindow;
    }

    @Override
    public void execute() {
        electricWindow.openWindow();
    }

    @Override
    public void undo() {
        electricWindow.closeWindow();
    }
}
