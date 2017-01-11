package designpattern.structural.composite;

/**
 * Purpose: Compose objects into tree structures to represent part-whole hierarchies.
 *          Lets clients treat individual objects and compositions of object uniformly.
 *
 *          The Composite pattern enables us to treat both parts and assembled item
 *          as if they are the same and processed in a consistent manner.
 *
 *
 *          Item - Part
 *               - Assembly (has Parts)
 */

public class Example {

    public static void main(String[] args) {
        Item nut = new Part("Nut", 5);
        Item bolt = new Part("Bolt", 9);
        Item panel = new Part("Panel", 35);

        Item gizmo = new Assembly("gizmo");
        gizmo.addItem(nut);
        gizmo.addItem(bolt);
        gizmo.addItem(panel);

        Item widget = new Assembly("Widget");
        widget.addItem(gizmo);
        widget.addItem(nut);

    }
}
