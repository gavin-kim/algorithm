package designpattern.structural.adapter;

/**
 * CassettePlayer has more features than EightTrackPlayer
 *
 ** EightPlayer:
 * void play();
 * void stop();
 * void forward();
 *
 ** CassettePlayer:
 * void play();
 * void stop();
 * void fastForward();
 * void reverse();
 *
 * EightTrackAdapter class: implements two class features
 *                          CassettePlayer can cover the object from the EightTrackAdapter
 *                          When EightTrack object, Some features are not supported.
 *                          When Cassette   object, All feature is supported.
 *
 * Difference between Decorator and Adapter
 *
 * Decorator shares the same interface then adds new features
 *
 * Adapter keeps the same basic features but implements another interface then add new features
 *
 *
 * Decorator: StandardEBook implements EBook interface then wrapped in other classes with new features
 * Adapter: EightTrackPlayerAdapter implements EightTrack and Cassette interfaces
 *
 *
 */

public class Example {
    public static void main(String[] args) {
        CassettePlayer eightTrackPlayer = new EightTrackPlayerAdapter();
        eightTrackPlayer.fastForward();
        eightTrackPlayer.reverse();
    }
}
