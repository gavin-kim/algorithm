package designpattern.behavioral.command;

public class ElectricWindow {
    private boolean open;

    public ElectricWindow() {
        open = false;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClosed() {
        return !open;
    }

    public void openWindow() {
        if (!open) {
            open = true;
            System.out.println("Window is now open");
        }
    }

    public void closeWindow() {
        if (open) {
            open = false;
            System.out.println("Window is now closed");
        }
    }
}
