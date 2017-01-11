package designpattern.behavioral.command;

public class VolumeDownCommand implements Command {

    private Radio radio;

    public VolumeDownCommand(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.volumeDown();
    }

    @Override
    public void undo() {
        radio.volumnUp();
    }
}
