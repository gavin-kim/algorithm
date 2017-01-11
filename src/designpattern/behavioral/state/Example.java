package designpattern.behavioral.state;

/**
 * Intent: able to change its state based on internal and external events
 *
 * For example, cell phones have limited space for physical keys,
 * the same keys are often mapped to different functions depending on the current state
 * or mode of the phone (standby, talking, application running, etc.)
 *
 *
 * CellPhone has a property to store its state (PhoneState)
 *
 * PhoneState - Application
 *            - Call
 *            - Standby
 *
 * CellPhone has all method.
 * Depending on states. methods work differently.
 * some method is supported or some is not.
 *
 * Singleton can be applied with State design pattern, instead of creating a new state instance
 *
 * public void launchApp(CellPhone cellPhone) {
 *     cellPhone.changeState(Standby.instance()); // singleton state
 * }
 *
 */
public class Example {

    public static void main(String[] args) {

        CellPhone cellPhone = new CellPhone();

        cellPhone.sideKeyUp();
        cellPhone.launchApp();
        cellPhone.sideKeyUp();

        cellPhone.SND_Key();
        cellPhone.END_Key();
        cellPhone.sideKeyUp();
    }
}
