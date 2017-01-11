package designpattern.behavioral.command;

public class Radio {
    public static final int MIN_VOLUME = 0;
    public static final int MAX_VOLUME = 10;
    public static final int DEFAULT_VOLUME = 5;

    private boolean on = false;
    private int volume = DEFAULT_VOLUME;

    public Radio() {
    }

    public boolean isOn() {
        return on;
    }

    public int getVolume() {
        return volume;
    }

    public void turnOn() {
        on = true;
        System.out.println("Radio is turned on, volume: " + volume);
    }

    public void turnOff() {
        on = false;
        System.out.println("Radio is turned off");
    }

    public void volumnUp() {
        if (on) {
            if (volume < MAX_VOLUME) {
                volume++;
                System.out.println("Volume turned up to level " + volume);
            }
        }
    }

    public void volumeDown() {
        if (on) {
            if (volume > MIN_VOLUME) {
                volume--;
                System.out.println("Volume turned down to level " + volume);
            }
        }
    }

}
