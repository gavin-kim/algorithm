package designpattern.behavioral.state;

public class Call extends PhoneState {
    @Override
    public void sideKeyUp() {
        System.out.println("Increase voice volumn");
    }

    @Override
    public void sideKeyDown() {
        System.out.println("Decrease voice volumn");
    }

    @Override
    public void END_Key(CellPhone cellphone) {
        cellphone.changeState(new Standby());
    }
}
