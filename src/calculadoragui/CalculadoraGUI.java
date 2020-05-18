package calculadoragui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Clase principal de la aplicacion
 * 
 * @author Francisco Javier Cruz Redondo
 * @version 0.1
 * @since 0.1
 */
public class CalculadoraGUI {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
     
         //obtenemos el tipo de interfaz de nuestro Sistema operativo.
         String actualName = UIManager.getCrossPlatformLookAndFeelClassName();
         
      try {
            UIManager.setLookAndFeel(actualName);
            Interfaz w = new Interfaz();
                // TODO code application logic here
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
