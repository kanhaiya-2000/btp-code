import java.io.*;
import java.net.*;

public class UserDevice {

    // Socket information for communicating with gateway
    private static final String GATEWAY_ADDRESS = "localhost";
    private static final int GATEWAY_PORT = 8080;
    private static ClientSocket socket;

    public static void handleReceivedMessage(String msg) {

    }

    private static void processResponseForChallange() {
        String response = generateResponse(challenge[0], challenge[1]);
        System.out.print("Enter biometric impression: ");
        String biometricImpression = reader.readLine();
        String[] biometricComponents = processBiometricImpression(biometricImpression);
        String message3 = prepareMessage3(challenge[2], challenge[3], response, biometricComponents[0]);
        sendToGateway(message3);
    }

    public static void main(String[] args) {

        try {
            // Step 1: User registration
            socket = new ClientSocket();
            socket.start(GATEWAY_ADDRESS, GATEWAY_PORT, (msg) -> {
                System.out.println("User received: " + msg);
                handleReceivedMessage(msg);
            });
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your unique user identity (UID): ");
            String userId = reader.readLine();
            String registrationRequest = generateRegistrationRequest(userId);
            sendToGateway(registrationRequest);

            // Step 3: User generates response and sends message

            // Step 5: User and gateway store information
            // userStoreInformation(biometricComponents[0], biometricComponents[1],
            // challenge[2], challenge[3], secrets[0], secrets[1]);
            // gatewayStoreInformation(challenge[2], challenge[3], userId, challenge[0],
            // challenge[1], secrets[0], secrets[1]);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String generateRegistrationRequest(String userId) {
        // TODO: implement
        return "u1_" + userId;
    }

    private static void sendToGateway(String message) {
        socket.send(message);
    }

    private static String generateResponse(String C_U, String G_H) {
        // TODO: implement
        return null;
    }

    private static String[] processBiometricImpression(String biometricImpression) {
        // TODO: implement
        return null;
    }

    private static String prepareMessage3(String G, String H, String response, String B1) {
        // TODO: implement
        return null;
    }

    // private static void userStoreInformation(String B1, String B2, String G,
    // String H,
}
