package designpattern.behavioral.chainofreposibility;

public class ManagerEmailHandler extends AbstractEmailHandler {


    @Override
    protected String[] getMatchingWords() {
        return new String[]{"complain", "bad"};
    }

    @Override
    protected void handleHere(String email) {
        System.out.println("Manager: " + email);
    }
}
