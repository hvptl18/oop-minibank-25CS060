@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {}

public class NotificationTest {
    public static void main(String[] args) {
        Notifier email = msg -> System.out.println("Email: " + msg);
        Notifier sms = msg -> System.out.println("SMS: " + msg);
        Notifier urgentEmail = new Notifier() {
            public void send(String message) {
                System.out.println("URGENT Email: " + message);
            }
        };
        Urgent urgentMarker = (Urgent) urgentEmail;
        Notifier[] senders = { email, sms, urgentEmail };
        String message = "Meeting at 5 PM";
        System.out.println("Broadcasting once:");
        for (Notifier n : senders) {
            n.send(message);
        }
        System.out.println("\nBroadcasting urgent twice:");
        for (Notifier n : senders) {
            n.send(message);
            if (n instanceof Urgent) {
                n.send(message);
            }
        }
    }
}
