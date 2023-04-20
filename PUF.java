import java.util.Random;

public class PUF {
  private final int numDelays;
  private final Random rng;

  public PUF(int numDelays) {
    this.numDelays = numDelays;
    this.rng = new Random();
  }

  public byte[] generateResponse(byte[] challenge) {
    // Convert challenge to a delay value
    int delay = 0;
    for (int i = 0; i < challenge.length; i++) {
      delay += (int)challenge[i];
    }

    // Generate a response using delays
    byte[] response = new byte[numDelays];
    for (int i = 0; i < numDelays; i++) {
      response[i] = (byte)(rng.nextBoolean() ? delay : -delay);
    }

    return response;
  }
}
