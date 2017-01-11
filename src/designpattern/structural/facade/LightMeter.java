package designpattern.structural.facade;

public class LightMeter {
    private int filmSpeed;

    public LightMeter(int filmSpeed) {
        this.filmSpeed = filmSpeed;
    }

    public double getRecommendedShutterSpeed() {
        return 1.0 / 60.0;
    }

    public double getRecommendedApertureSize() {
        return 5.6;
    }
}
