package designpattern.behavioral.command;

public class VolumeUpCommand implements Command {

    private Radio radio;

    public VolumeUpCommand(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.volumnUp();
    }

    @Override
    public void undo() {
        radio.volumeDown();
    }
}
