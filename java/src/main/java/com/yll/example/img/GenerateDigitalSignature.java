package com.yll.example.img;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

public class GenerateDigitalSignature {
    private Signature signature;
    private byte[] digitalSignature;
    private final String dataPath = "README";
    private final String signaturePath = "signature";
    private final String publickeyPath = "publickey";

    private void signData() {
        try {
            // Get instance and initialize a KeyPairGenerator object.
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);

            // Get a PrivateKey from the generated key pair.
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            // Get an instance of Signature object and initialize it.
            Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
            signature.initSign(privateKey);

            // Supply the data to be signed to the Signature object
            // using the update() method and generate the digital
            // signature.
            byte[] bytes = Files.readAllBytes(Paths.get(dataPath));
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();

            // Save digital signature and the public key to a file.
            this.digitalSignature = digitalSignature;
            this.signature = signature;
            Files.write(Paths.get(signaturePath), digitalSignature);
            Files.write(Paths.get(publickeyPath), keyPair.getPublic().getEncoded());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    private void verifyData() {
//        boolean status = false;
//        try {
//
//            File file = new File(signaturePath);
//            if (!file.exists()) {
//                return;
//            }
//
//            FileReader fileReader = new FileReader(file);
//            byte[] signature = fileReader.read();
//            status = this.signature.verify(digitalSignature);
//        } catch (SignatureException e) {
//
//        } catch (FileNotFoundException e) {
//
//        } catch (IOException e) {
//
//        }
//        System.out.println();
//    }

    public static void main(String[] args) {

    }
}