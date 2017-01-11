package designpattern.structural.adapter;

public class EightTrackPlayerAdapter implements EightTrackPlayer, CassettePlayer {
    @Override
    public void play() {
        System.out.println("Play");
    }

    @Override
    public void stop() {
        System.out.println("Stop");
    }

    @Override
    public void fastForward() {
        forward();
    }

    @Override
    public void reverse() {
        System.out.println("Reverse not supported");
    }

    @Override
    public void forward() {

    }
}



