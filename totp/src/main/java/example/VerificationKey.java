package example;

public class VerificationKey {
    int verificationCode;
    String key;

    public VerificationKey() {
    }

    public VerificationKey(int verificationCode, String key) {
        this.verificationCode = verificationCode;
        this.key = key;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public String getKey() {
        return key;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
