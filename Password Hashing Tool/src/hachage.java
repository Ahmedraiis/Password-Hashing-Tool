import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hachage {
    private String MDP;
    private String Nom;
    public  hachage(String MDP,String Nom){
        this.MDP=MDP;
        this.Nom=Nom;
    }
    public void hached() throws NoSuchAlgorithmException, IOException {
        gsoncode gson= new gsoncode();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(MDP.getBytes(StandardCharsets.UTF_8));
        BigInteger bigInt= new BigInteger(1,bytes);
        if(!gson.existe(Nom,bigInt)){
            gson.addtogson(Nom,bigInt);
        }
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String Nom){

        this.Nom=Nom;
    }
    public void setMDP(){

    }
}
