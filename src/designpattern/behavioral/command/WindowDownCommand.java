package designpattern.behavioral.command;

public class WindowDownCommand implements Command {
    private ElectricWindow electricWindow;

    public WindowDownCommand(ElectricWindow electricWindow) {
        this.electricWindow = electricWindow;
    }

    @Override
    public void execute() {
        electricWindow.closeWindow();
    }

    @Override
    public void undo() {
        electricWindow.openWindow();
    }
}
