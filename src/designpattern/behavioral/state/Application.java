package designpattern.behavioral.state;

public class Application extends PhoneState {
    @Override
    public void sideKeyUp() {
        System.out.println("Scroll up");
    }

    @Override
    public void sideKeyDown() {
        System.out.println("Scroll down");
    }

    @Override
    public void END_Key(CellPhone cellphone) {
        cellphone.changeState(new Standby());
    }
}
