package designpattern.behavioral.command;

public class SpeechRecogniser {
    private Command upCommand;
    private Command downCommand;

    public void setCommands(Command upCommand, Command downCommand) {
        this.upCommand = upCommand;
        this.downCommand = downCommand;
    }

    public void hearUpSpoken() {
        upCommand.execute();
    }

    public void hearDownSpoken() {
        downCommand.execute();
    }
}
