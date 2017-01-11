package designpattern.creational.prototype;

/**
 * Purpose: Specify the kinds of objects to create using a prototypical instance
 *          and create new objects by copying the prototype.
 *
 *          Manager class
 *          private Saloon, Coupe, Sport, BoxVan, Pickup;
 *
 *          Vehicle createSaloon() {
 *              return (Vehicle) saloon.clone();
 *          }
 *
 *          Lazy version doesn't initialize object when it's created
 */

public class Example {

    public static void main(String[] args) {

        // This object has all Vehicle objects and return clones
        VehicleManager manager = new VehicleManager();

        Coupe coupe = (Coupe) manager.createCoupe();
        BoxVan boxVan = (BoxVan) manager.createBoxVan();


    }
}
