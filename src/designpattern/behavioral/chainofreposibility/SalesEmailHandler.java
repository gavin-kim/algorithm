package designpattern.behavioral.chainofreposibility;

public class SalesEmailHandler extends AbstractEmailHandler {


    @Override
    protected String[] getMatchingWords() {
        return new String[]{"buy", "purchase"};
    }

    @Override
    protected void handleHere(String email) {
        System.out.println("Sales: " + email);
    }
}
