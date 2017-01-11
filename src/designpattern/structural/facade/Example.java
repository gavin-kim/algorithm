package designpattern.structural.facade;

/**
 * Intent: To provide a unified interface to a set of interfaces in a subsystem
 *         defines a higher-level interface for the subclasses
 *
 * Advantages
 *
 * 1. Unification: Facade class defines a single point of access
 * 2. Abstraction and Simplification: Facade interface is a level of abstraction
 *                                    above that of the wrapped interface
 * 3. Decreased coupling: Facade class decouples clients from the details of a subsystem.
 *                        Clients that access the services of a subsystem through a Facade class
 *                        are buffered from changes in the subsystem.
 *
 * Facade class doesn't encapsulate but rather, "Wraps" an existing subsystem
 *
 * Film, Aperture, Shutter are sub class
 *
 * Facade class use those sub classes inside the class
 */

public class Example {

    public static void main(String[] args) {
        client1();
        client2();
    }

    /** use Facade interface */
    public static void client1() {
        PointAndShootFacade pointAndShootCamera = new PointAndShootFacade();
        pointAndShootCamera.takePicture();
    }

    /** use the raw camera interface */
    public static void client2() {
        Shutter shutter = new Shutter();
        Aperture aperture = new Aperture();
        Film film = new Film();

        int filmSpeed = film.getFilmSpeed();
        if (filmSpeed <= 200) {
            shutter.setSpeed( 1.0 / 30.0);
            aperture.setSize(5.6);
        } else if (filmSpeed >= 400) {
            shutter.setSpeed(1.0 / 60.0);
            aperture.setSize(8);

        } else {
            shutter.setSpeed(1.0 / 45.0);
            aperture.setSize(5.6);
        }

        shutter.trigger();
    }
}
