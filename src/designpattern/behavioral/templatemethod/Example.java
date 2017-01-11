package designpattern.behavioral.templatemethod;

/**
 * The Template Method shows how to eliminate repetition in algorithm structure
 *
 * Decide how to declare each method (e.g. JdbcTemplate)
 *
 *
 * Abstract       : Have unique implementation in most subclasses
 * Concrete       : Share the same implementation in all subclasses (default)
 * Concrete(empty): Need only a small fraction of subclasses
 * Non-Polymorphic: invariant(never changing) steps in the algorithm
 *                  reusable -> protected, otherwise -> private
 *
 *                  Refer to Vegetable Class (Template)
 *
 * Template method: grow() invoke 5 methods in order, they always happen the same way
 *                  (routine: digHole() -> plantSeeds() -> mulch() -> (if) -> water() -> prune())
 *                  Tomato and Pumpkin Class implement abstract methods for their purposes
 *
 */

public class Example {
    public static void main(String[] args) {
        Tomato tomato = new Tomato();
        tomato.grow();

        Pumpkin pumpkin = new Pumpkin();
        pumpkin.grow();
    }
}
