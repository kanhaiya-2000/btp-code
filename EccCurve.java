import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.util.ArrayList;
import java.security.SecureRandom;

public class EccCurve {
    private static BigInteger p, a, b, n;
    private static ECPoint G;
    private static SecureRandom random;

    public static void main(String[] args) {
        // Define the elliptic curve parameters
        // define the parameters of the elliptic curve y^2 = x^3 + 7
        // over the finite field of integers modulo p, where p is a large prime.
        p = new BigInteger("fffffffffffffffffffffffffffffffeffffffffffffffff", 16);
        a = new BigInteger("0", 16);
        b = new BigInteger("7", 16);
        n = new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16);
        G = new ECPoint.Fp(new ECDomainParameters(p, a, b, n, null),
                new BigInteger("55066263022277343669578718895168534326250603453777594175500187360389116729240"),
                new BigInteger("32670510020758816978083085130507043184471273380659243275938904335757337482424"));

        // Generate a random private key
        random = new SecureRandom();
        ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keyGenParams = new ECKeyGenerationParameters(new ECDomainParameters(p, a, b, n, G),
                random);
        keyPairGenerator.init(keyGenParams);
        ECPrivateKeyParameters privateKey = (ECPrivateKeyParameters) keyPairGenerator.generateKeyPair().getPrivate();

        // Generate the corresponding public key
        ECPublicKeyParameters publicKey = new ECPublicKeyParameters(G.multiply(privateKey.getD()),
                new ECDomainParameters(p, a, b, n, G));

    }

    public ArrayList<ECPoint> getRandoms(int howmany) {
        // Generate two random points on the curve
        // ECPoint randomPoint1 = G.multiply(new BigInteger(n.bitLength(), random));
        // ECPoint randomPoint2 = G.multiply(new BigInteger(n.bitLength(), random));

        ArrayList<ECPoint> points;
        for (int i = 0; i < howmany; i++) {
            ECPoint randomPoint = G.multiply(new BigInteger(n.bitLength(), random));
            points.add(randomPoint);
            System.out.println("Random point " + i + ":" + randomPoint);
        }

    }
}
