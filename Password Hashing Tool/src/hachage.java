import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hachage {
    String MDP;
    String Nom;
    public  hachage(String MDP,String Nom){
        this.MDP=MDP;
        this.Nom=Nom;
    }
    public String hached() throws NoSuchAlgorithmException {
        String A="AHMED";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(A.getBytes(StandardCharsets.UTF_8));
        BigInteger bigInt= new BigInteger(1,bytes);
        return bigInt.toString(16);
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String Nom){
        this.Nom=Nom;
    }
}
