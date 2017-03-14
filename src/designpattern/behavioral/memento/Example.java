package designpattern.behavioral.memento;

/**
 * Purpose: Without violating encapsulation, capture and externalise an object's
 *          internal state so that it can be restored to this state later.
 *
 *          Approach 1: using package-private visibility
 *                      for encapsulation and data-hiding OUTSIDE PACKAGE!
 *
 *                      Speedometer             => Memento (in the same package)
 *                      -currentSpeed(getter)      -speedometer
 *                      previousSpeed(no getter)   -currentSpeed
 *                                                 -previousSpeed
 *                                                 restore()
 *
 *          Disadvantage: need a pair of classes in their package to access directly
 *
 *
 *
 *          Approach 2: object serialization (memento store WHOLE Object in the file)
 *                      also encapsulation and data-hiding in any other class
 *
 *                      Speedometer             => Memento (in the same package)
 *                      -currentSpeed(getter)      store()  : serialize object to a file
 *                      -previousSpeed(getter)     restore(): deserialize a file to the object
 *
 *          Disadvantage: much slower and someone can read serialized file
 *
 *
 */

public class Example {

    public static void main(String[] args) {

        System.out.println("Approach1: package-private visibility\n");
        test_package_private();

        System.out.println("Approach2: Object Serialization\n");
        test_serialize();
    }

    public static void test_package_private() {
        Speedometer speedo = new Speedometer();

        speedo.setCurrentSpeed(50);
        speedo.setCurrentSpeed(100);
        System.out.println("Current speed: " + speedo.getCurrentSpeed());
        System.out.println("Previous speed: " + speedo.previousSpeed);

        // Save the state of speedometer -> memento object
        SpeedometerMemento memento = new SpeedometerMemento(speedo);
        System.out.println("Store current state in the memento\n");

        // Change the state of speedometer
        speedo.setCurrentSpeed(80);
        System.out.println("After setting to 80...");
        System.out.println("Current speed: " + speedo.getCurrentSpeed());
        System.out.println("Previous speed: " + speedo.previousSpeed);

        // Restore the state of speedometer:
        //        memento object has reference to speedometer and state.
        System.out.println("\nNow restoring state...");
        memento.restoreState();
        System.out.println("Current speed: " + speedo.getCurrentSpeed());
        System.out.println("Previous speed: " + speedo.previousSpeed);
    }

    public static void test_serialize() {
        Speedometer2 speedo = new Speedometer2();

        speedo.setCurrentSpeed(50);
        speedo.setCurrentSpeed(100);
        System.out.println("Current speed: " + speedo.getCurrentSpeed());
        System.out.println("Previous speed: " + speedo.getPreviousSpeed());

        // Save the state of speedometer -> memento object
        SpeedometerMemento2 memento = new SpeedometerMemento2(speedo);
        System.out.println("Store current state in the memento\n");

        // Change the state of speedometer
        speedo.setCurrentSpeed(80);
        System.out.println("After setting to 80...");
        System.out.println("Current speed: " + speedo.getCurrentSpeed());
        System.out.println("Previous speed: " + speedo.getPreviousSpeed());

        // Restore the state of speedometer:
        // memento object has reference to speedometer and state.
        System.out.println("\nNow restoring state...");
        speedo = memento.restoreState();
        System.out.println("Current speed: " + speedo.getCurrentSpeed());
        System.out.println("Previous speed: " + speedo.getPreviousSpeed());
    }
}
