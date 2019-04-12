package assinaturadigital;

import assinaturadigital.modelos.Destinatario;
import assinaturadigital.modelos.Remetente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan M.
 */
public class AssinaturaDigital {

    public static void main(String[] args) {
       
        FileInputStream fis = null;
        try {
            
            Remetente remetente = new Remetente();
            
            File arquivo = new File("arquivo.txt");
            fis = new FileInputStream(arquivo);
            
            byte[] arquivoBytes = new byte[(int) arquivo.length()];
            fis.read(arquivoBytes);
            
            byte[] assinatura = remetente.gerarAssinatura(arquivoBytes);
   
            Destinatario destinatario = new Destinatario(remetente.getChavePublica());
  
            if(destinatario.verificarAssinatura(arquivoBytes, assinatura))
                System.out.println("Assinatura validada com sucesso!");
            else
                System.out.println("Não foi possível validar a assinatura do arquivo.");
    
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AssinaturaDigital.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InvalidKeyException | SignatureException | NoSuchAlgorithmException ex) {
            Logger.getLogger(AssinaturaDigital.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(AssinaturaDigital.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        
    }
    
}
