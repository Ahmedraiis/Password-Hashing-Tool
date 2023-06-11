import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class gsoncode {

    private File gsonFile= new File("fichier.gson");
    public gsoncode() throws IOException {
        if (gsonFile.createNewFile()) {
            System.out.println("Fichier créé : " + gsonFile.getName());
        }
    }

    public void addtogson(String nom, BigInteger hashedCode) throws IOException {
        Gson gsonParser = new Gson();
        String jsonContent = new String(Files.readAllBytes(Paths.get(gsonFile.getAbsolutePath())));
        JsonArray jsonArray = gsonParser.fromJson(jsonContent, JsonArray.class);
        if (jsonArray == null) {
            jsonArray = new JsonArray();
        }
        JsonObject object = new JsonObject();
        object.addProperty("Username", nom);
        object.addProperty("password", hashedCode.toString());
        jsonArray.add(object);
        Files.write(Paths.get(gsonFile.getAbsolutePath()), gsonParser.toJson(jsonArray).getBytes());
    }

    public boolean existe(String nom, BigInteger hashedCode) throws IOException, NoSuchAlgorithmException {
        Gson gsonParser = new Gson();
        String jsonContent = new String(Files.readAllBytes(Paths.get(gsonFile.getAbsolutePath())));
        JsonArray jsonArray = gsonParser.fromJson(jsonContent, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Le nom d'utilisateur n'existe pas");
            return false;
        }

        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            if (jsonObject.get("Username").getAsString().equals(nom)) {
                System.out.println("L'utilisateur existe");
                String savedHashedCode = jsonObject.get("password").getAsString();
                if (savedHashedCode.equals(hashedCode.toString())) {
                    System.out.println("Le mot de passe est correct");
                    return true;
                } else {
                    while (true) {
                        System.out.println("Le mot de passe est incorrect. Veuillez réessayer :");
                        Scanner scanner = new Scanner(System.in);
                        String inputPassword = scanner.nextLine();
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] hashedBytes = digest.digest(inputPassword.getBytes(StandardCharsets.UTF_8));
                        BigInteger hashedInputCode = new BigInteger(1, hashedBytes);
                        if (savedHashedCode.equals(hashedInputCode.toString())) {
                            System.out.println("Le mot de passe est correct");
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("Le nom d'utilisateur n'existe pas");
        return false;
    }
}
