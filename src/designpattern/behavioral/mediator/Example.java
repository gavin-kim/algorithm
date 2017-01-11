package designpattern.behavioral.mediator;

/**
 * Purpose: Define an object that encapsulates how a set of objects interact.
 *          Mediator promotes loose coupling and lets you vary their interaction independently
 *
 *          Mediator knows about the role of each component and takes
 *          responsibility for managing their interaction
 *
 *          mediator <---> Ignition
 *                   <---> Gearbox
 *                   <---> Accelerator
 *                   <---> Brake
 *
 *          Mediator has Component's reference.
 *          Component also has Mediator's reference.
 *
 *          User      -> invokes a method in Mediator
 *          Mediator  -> handles components (Ignition, Gearbox, Accelerator, Brake)
 *          Component -> return something to Mediator
 *          Mediator  -> show the value from components graphically
 */

public class Example {

    public static void main(String[] args) {
        EngineManagementSystem mediator = new EngineManagementSystem();

        new Ignition(mediator);
        new Gearbox(mediator);
        new Accelerator(mediator);
        new Brake(mediator);


        mediator.ignitionTurnedOn();
        System.out.println();
        mediator.acceleratorPressed();
        System.out.println();
        mediator.brakePressed();
    }
}
