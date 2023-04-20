import java.io.*;
import java.net.*;

public class Gateway {

    // Socket information for communicating with gateway
    private static final String ADDRESS = "localhost";
    private static final int USER_PORT = 8081;
    private static GatewaySocket socket;
    public static void handleReceivedMessage(String msg){
        String type = msg.substring(0,2);
        String response = msg.substring(3);
        if(type == "u1"){
            // registration request
            processChallange(response);
        }
    }

    private static void processChallange(String u){
        String[] challenge = generateChallenge(u);
        String message2 = prepareMessage2(challenge);
        sendToUser(message2);
    }
    private static void processSecrets(){
        String[] secrets = generateSecrets(challenge[4], challenge[5], response);
        String message4 = prepareMessage4(secrets[0], secrets[1]);
        sendToUser(message4);
    }
    public static void main(String[] args) throws Exception{

        try {
            socket = new GatewaySocket();
            socket.start(USER_PORT,(msg)->{
                System.out.println("Gateway received: "+msg);
                handleReceivedMessage(msg);
            });
            // Step 2: Gateway generates challenge
           

            // Step 4: Gateway generates secrets and sends message
           

            // Step 5: User and gateway store information
            // userStoreInformation(biometricComponents[0], biometricComponents[1],
            // challenge[2], challenge[3], secrets[0], secrets[1]);
            // gatewayStoreInformation(challenge[2], challenge[3], userId, challenge[0],
            // challenge[1], secrets[0], secrets[1]);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void sendToUser(String message) {
        socket.send(message);
    }

    private static String[] generateChallenge(String u) {
        // TODO: implement
        return null;
    }

    private static String prepareMessage2(String[] challenge) {
        // TODO: implement
        return null;
    }

    private static String[] generateSecrets(String Z_G, String W_H, String response) {
        // TODO: implement
        return null;
    }

    private static String prepareMessage4(String R1_SG, String beta) {
        // TODO: implement
        return null;
    }

    // private static void userStoreInformation(String B1, String B2, String G,
    // String H,
}
