package designpattern.behavioral.chainofreposibility;

/**
 * Purpose: Avoid coupling the sender of a request to its receiver
 *          Chain the receiving objects and pass it along the chain
 *
 *
 *          Spam -> Sales -> Service -> Manager -> General
 *
 *          Spam(request)--------------------------------->
 */
public class Example {
    public static void main(String[] args) {
        String email = "I need my car repaired.";
        String email2 = "I need viagra";
        String email3 = "complain and buy";
        String email4 = "I love you";

        EmailHandler emailHandler = new SpamEmailHandler();
        emailHandler.setNextHandler(SalesEmailHandler.class)
                    .setNextHandler(ServiceEmailHandler.class)
                    .setNextHandler(ManagerEmailHandler.class)
                    .setNextHandler(GeneralEnquiriesEmailHandler.class);

        emailHandler.processHandler(email);
        emailHandler.processHandler(email2);
        emailHandler.processHandler(email3);
        emailHandler.processHandler(email4);
    }
}
