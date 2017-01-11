package designpattern.behavioral.command;

/**
 * Purpose: Encapsulate a request as an object,
 *          thereby letting you parameterize clients with different requests,
 *          queue or log requests and support undoable operations
 *
 *          Reference: java.lang.Runnable, new Thread(() -> { ...... }).start();
 *
 *          Command object means Middle-man object
 *
 *          new SpeechRecogniser(Command).execute();
 *
 *          Command - VolumeCommand
 *                    WindowCommand
 *
 */
public class Example {

    public static void main(String[] args) {

        Radio radio = new Radio();
        radio.turnOn();

        Command volumeUpCommand = new VolumeUpCommand(radio);
        Command volumeDownCommand = new VolumeDownCommand(radio);

        ElectricWindow window = new ElectricWindow();

        Command windowUpCommand = new WindowUpCommand(window);
        Command windowDownCommand = new WindowDownCommand(window);

        SpeechRecogniser speechRecogniser = new SpeechRecogniser();

        speechRecogniser.setCommands(volumeUpCommand, volumeDownCommand);
        speechRecogniser.hearDownSpoken();
        speechRecogniser.hearUpSpoken();

        speechRecogniser.setCommands(windowUpCommand, windowDownCommand);
        speechRecogniser.hearUpSpoken();
        speechRecogniser.hearDownSpoken();

    }
}

