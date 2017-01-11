package designpattern.behavioral.templatemethod;

public class Weather {
    private boolean toggle = true;

    public boolean frost() {
        toggle = !toggle;
        return toggle;
    }

    public boolean isWarmClimate() {
        return true;
    }

}
