package example;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

public class Credentials {

    public VerificationKey newSecretKey() {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        return new VerificationKey(key.getVerificationCode(), key.getKey());
    }

    public boolean authorize(String secret, String verificationCode) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        return gAuth.authorize(secret, Integer.parseInt(verificationCode));
    }
}
