/*
 Public Key cryptography using the RSA algorithm.
*/
 
import java.security.*;
import javax.crypto.*;
 
public class PublicKeyRSAExample {
 
    public static void main (String[] args) throws Exception {
        //
        // Obter o texto a partir da linha de comandos
        if (args.length !=1) {
            System.err.println("Usage: java PublicKeyRSAExample text");
            System.exit(1);
        }
        byte[] plainText = args[0].getBytes("UTF8");
        //
        // Gerar uma chave RSA
        System.out.println( "\nGerar uma chave RSA" );
        
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        
        KeyPair key = keyGen.generateKeyPair();
        
        System.out.println( "Chave RSA gerada" );
        //Criar um objecto Cipher RSA (especificando o algoritmo, modo e padding)
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //
        // Mostra a informacao do Provider
        System.out.println( "\n" + cipher.getProvider().getInfo() );
        System.out.println( "\nSComeca a encriptacao" );
        //
        // Inicializa o objecto Cipher
        cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());      
        //
        // Encripta o texto com a chave pública
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println( "Fim da encriptacao: " );
        System.out.println( new String(cipherText, "UTF8") );
        System.out.println( "\nComeca a desencriptacao" );
        //
        // Inicializa o objecto Cipher.
        cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
        //
        // Desencripta o texto usando a chave privada
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println( "Fim da desencriptacao: " );
        System.out.println( new String(newPlainText, "UTF8") );
    }
}