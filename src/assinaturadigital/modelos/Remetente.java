package assinaturadigital.modelos;

import java.security.*;

/**
 *
 * @author Luan M.
 */
public class Remetente {
    
    private PrivateKey chavePrivada;
    private PublicKey chavePublica;

    
    /*
    * Construtores
    */
    public Remetente() {
    }

    public Remetente(PrivateKey chavePrivada, PublicKey chavePublica) {
        this.chavePrivada = chavePrivada;
        this.chavePublica = chavePublica;
    }
    
    /*
    * Métodos
    */
    public byte[] gerarAssinatura(byte[] arquivo) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException{
        
        Signature signature = Signature.getInstance("DSA");
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        
        SecureRandom secureRandom = new SecureRandom();
        
        keyPairGenerator.initialize(512, secureRandom);
        
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        this.chavePublica = keyPair.getPublic();
        this.chavePrivada = keyPair.getPrivate();
        
        signature.initSign(chavePrivada);
        signature.update(arquivo);
        byte[] arquivoAssinado = signature.sign();

        return arquivoAssinado;
    }

    
    /*
    * Getters e Setters
    */
    public PrivateKey getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(PrivateKey chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public PublicKey getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(PublicKey chavePublica) {
        this.chavePublica = chavePublica;
    }
    
    
    
    
    
    
    
}
