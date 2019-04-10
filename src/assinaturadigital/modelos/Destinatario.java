package assinaturadigital.modelos;

import java.security.*;

/**
 *
 * @author Luan M.
 */
public class Destinatario {
    
    private PublicKey chavePublica;

    /*
    * Construtor
    */
    public Destinatario(PublicKey chavePublica) {
        this.chavePublica = chavePublica;
    }
    
    /*
    * Métodos
    */
    
    public boolean verificarAssinatura(byte[] arquivo, byte[] assinatura) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException{
        
        Signature signature = Signature.getInstance("DSA");
        signature.initVerify(chavePublica);
        signature.update(arquivo);
        
        return signature.verify(assinatura);
    }
    
    /*
    * Getter e Setter
    */

    public PublicKey getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(PublicKey chavePublica) {
        this.chavePublica = chavePublica;
    }
    
    
    
    
    
}
