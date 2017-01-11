package designpattern.structural.bridge;

/**
 * Purpose: Decouple an abstraction from its implementation so that each may vary independently
 *          The Bridge is the communication layer between your code and the other system.
 *
 *          (e.g. JDBC Driver bridge <- inject a driver depends on DB system.)
 *
 *
 * Control can be designed and plugged into a Vehicle without affecting other systems
 * Engine can be designed and plugged into a Control without affecting other systems
 *
 * Vehicle has Control interface
 * Control has Engine interface
 *
 * Actual objects for Control and Engine can be swapped by Dependency Injection
 *
 *
 * Bridge pattern use Dependency Injection. Dependency Injection is not a pattern, Design guideline.
 */

public class Example {

    public static void main(String[] args) {

        Engine engine = new StandardEngine(1300);

        System.out.println("standardControl");
        StandardControls standardControls = new StandardControls(engine);
        standardControls.ingnitionOn();
        standardControls.accelerate();
        standardControls.brake();
        standardControls.ignitionOff();


        System.out.println("SportControl");
        SportControls sportControls = new SportControls(engine);
        sportControls.ingnitionOn();
        sportControls.accelerate();
        sportControls.accelerateHard(); // new feature
        sportControls.brake();
        sportControls.ignitionOff();
    }
}
