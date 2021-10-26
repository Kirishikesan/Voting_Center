package com.crypto.votingCenter.AES;

public class Test {

    public static void main(String[] args) {
        AES aes = new AES("746875766172616b616e746875766172746875766172616b616e746875766172");
        String ciphertext = aes.encrypt("486920486f772061726520796f75203f"); //Hi how are you ?
        System.out.println(ciphertext);
        String plaintext = aes.decrypt(ciphertext);
        System.out.println(plaintext);
        System.out.println(GCM.hexToAscii(plaintext));
    }
}
