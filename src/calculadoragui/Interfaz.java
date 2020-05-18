package calculadoragui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Implementa la interfaz grafica de la aplicacion y le añade funcionalidad
 * 
 * @author Francisco Javier Cruz Redondo
 * @version 0.1
 * @since 0.1
 */
public class Interfaz implements ActionListener {

    JTextField campoSuperior, campoInferior;
    Panel panelNorte, panelBotonesMemoria, panelBotonesOperacion;
    JPanel panelSur, panelBotonesNumeros;
    JButton mc, mr, ms, mMas, mMenos, numeros[], operaciones[];
    String oper[] = {"R", "C", "+", "/", "-", "*", "="}, textoAuxiliar = "";
    float operando1, operando2, resultado, memoria;//variables para las operaciones
    int tipOp; //para controlar el tipo de operacion que se realiza
    boolean t = false;//control sobre escribir un nuevo numero despues de alguna operacion cambia a true cuando se ha realizado una operacion

    /**
     * Define la interfaz grafica de la aplicacion
     * 
     */
    public Interfaz() {

        JFrame jfMain = new JFrame("Calculator");
        jfMain.setLayout(new BorderLayout(4, 4));

        norte();
        sur();

        jfMain.add(panelNorte, BorderLayout.NORTH);
        jfMain.add(panelSur, BorderLayout.CENTER);

        jfMain.setLocation(100, 80);
        jfMain.setResizable(false);
        jfMain.setVisible(true);
        jfMain.setSize(300, 380);
        jfMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Define el panel superior de la interfaz que mostrara las operaciones
     * 
     */
    public void norte() {

        panelNorte = new Panel(null);

        campoSuperior = new JTextField("");
        campoInferior = new JTextField("0");

        campoSuperior.setHorizontalAlignment(JTextField.RIGHT);
        campoInferior.setHorizontalAlignment(JTextField.RIGHT);

        //Quitar bordes a los campos de texto
        campoSuperior.setBorder(BorderFactory.createLineBorder(Color.white));
        campoInferior.setBorder(BorderFactory.createLineBorder(Color.white));

        //desabilitando los campos de texto
        campoSuperior.setEditable(false);
        campoInferior.setEditable(false);

        campoSuperior.setBackground(Color.white);
        campoInferior.setBackground(Color.white);

        panelNorte.add(campoSuperior);
        panelNorte.add(campoInferior);

        campoSuperior.setBounds(35, 10, 200, 15);
        campoInferior.setBounds(35, 25, 200, 30);

        panelNorte.setSize(270, 47);
        panelNorte.setVisible(true);

    }

    /**
     * Define el panel inferior de la interfaz que contendra los distintos botones
     * 
     */
    public void sur() {

        panelSur = new JPanel(new BorderLayout(6, 50));
        panelSur.setLayout(new BorderLayout(4, 4));

        botMem();
        botNum();
        botOpe();

        panelSur.add(panelBotonesMemoria, BorderLayout.NORTH);
        panelSur.add(panelBotonesNumeros, BorderLayout.CENTER);
        panelSur.add(panelBotonesOperacion, BorderLayout.EAST);

        panelSur.setSize(270, 330);
    }

    /**
     * Define la seccion del panel inferior que contendra los botones de memoria
     * 
     */
    public void botMem() {

        panelBotonesMemoria = new Panel(null);

        mc = new JButton("MC");
        mr = new JButton("MR");
        ms = new JButton("MS");
        mMas = new JButton("M+");
        mMenos = new JButton("M-");

        mc.setFont(new Font("Arial", Font.BOLD, 11));
        mr.setFont(new Font("Arial", Font.BOLD, 11));
        ms.setFont(new Font("Arial", Font.BOLD, 11));
        mMas.setFont(new Font("Arial", Font.BOLD, 11));
        mMenos.setFont(new Font("Arial", Font.BOLD, 11));

        mc.setMargin(new Insets(1, 1, 1, 1));
        mr.setMargin(new Insets(1, 1, 1, 1));
        ms.setMargin(new Insets(1, 1, 1, 1));
        mMas.setMargin(new Insets(1, 1, 1, 1));
        mMenos.setMargin(new Insets(1, 1, 1, 1));

        mc.setBounds(35, 0, 33, 33);
        mr.setBounds(78, 0, 33, 33);
        ms.setBounds(121, 0, 33, 33);
        mMas.setBounds(164, 0, 33, 33);
        mMenos.setBounds(207, 0, 33, 33);

        panelBotonesMemoria.add(mc);
        panelBotonesMemoria.add(mr);
        panelBotonesMemoria.add(ms);
        panelBotonesMemoria.add(mMas);
        panelBotonesMemoria.add(mMenos);

        mc.addActionListener(this);
        mr.addActionListener(this);
        ms.addActionListener(this);
        mMas.addActionListener(this);
        mMenos.addActionListener(this);

        panelBotonesMemoria.setSize(270, 45);
        panelBotonesMemoria.setVisible(true);
    }

    /**
     * Define la seccion del panel inferior que contendra los botones numericos
     * 
     */
    public void botNum() {

        panelBotonesNumeros = new JPanel(null);

        int nx3 = 121, nx2 = 121, nx1 = 121, n3y = 0, n2y = 43, n1y = 86;
        numeros = new JButton[11];

        //*****************************************
        //bloque para crear los botones, añadirlos y asignar numeros
        for (int i = 0; i <= 10; i++) {

            if (i <= 9) {
                numeros[i] = new JButton("" + i);
                panelBotonesNumeros.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);
            } else {
                numeros[i] = new JButton(".");
                panelBotonesNumeros.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);
            }
        }

        //******************************************
        //bloque para posicionar botones
        for (int i = 10; i >= 0; i--) {

            if (i == 10) {
                numeros[i].setBounds(121, 129, 35, 35);
            } else {
                if (i <= 9 && i >= 7) {
                    numeros[i].setBounds(nx3, n3y, 35, 35);
                    nx3 -= 43;
                } else if (i <= 6 && i >= 4) {
                    n3y += 43;
                    numeros[i].setBounds(nx2, n2y, 35, 35);
                    nx2 -= 43;
                } else if (i <= 3 && i >= 1) {
                    n3y += 43;
                    numeros[i].setBounds(nx1, n1y, 35, 35);
                    nx1 -= 43;
                } else if (i == 0) {
                    numeros[i].setBounds(35, 129, 78, 35);
                }
            }
        }

        panelBotonesNumeros.setSize(170, 150);
        panelBotonesNumeros.setVisible(true);
    }

    /**
     * Define la seccion del panel inferior que contendra los botones de operaciones
     * 
     */
    public void botOpe() {

        panelBotonesOperacion = new Panel(null);

        int c = 0, x = 0, y = 0;

        operaciones = new JButton[7];

        for (int i = 0; i <= 6; i++) {
            if (c <= 1) {

                operaciones[i] = new JButton(oper[i]);
                panelBotonesOperacion.add(operaciones[i]);

                operaciones[i].setBounds(x, y, 30, 35);

                operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                operaciones[i].addActionListener(this);
                x += 33;
                c++;
            } else {
                if (i == 6) {
                    x = 0;
                    y += 43;
                    operaciones[i] = new JButton(oper[i]);
                    panelBotonesOperacion.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 65, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x += 33;
                    c++;
                } else {
                    c = 0;
                    x = 0;
                    y += 43;
                    operaciones[i] = new JButton(oper[i]);
                    panelBotonesOperacion.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 30, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x += 33;
                    c++;
                }
            }

        }

        panelBotonesOperacion.setVisible(true);
        panelBotonesOperacion.setSize(120, 200);
    }

    /**
     * Comprueba si una cadena contiene solo numeros
     * 
     * @param ax cadena a comprobar
     * @return boolean
     */
    public boolean isN(String ax) {

        try {
            int n = Integer.parseInt(ax);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * Procesa una accion en funcion del tipo de boton pulsado
     * 
     * @param e accion a procesar
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String op = "";

        if (isN(e.getActionCommand())) { 
            pulsaNumero(e);
        } else {//cuando se oprime el resto de botones

            if (e.getActionCommand().equals("R")) {
                pulsaRaiz();
            }
            if (e.getActionCommand().equals("C")) {                 
                pulsaC();
            }
            if (e.getActionCommand().equals("MC")) {                
                pulsaMC();
            }
            if (e.getActionCommand().equals("MR")) {                
                pulsaMR();
            }
            if (e.getActionCommand().equals("MS")) {                
                pulsaMS();
            }
            if (e.getActionCommand().equals("M+")) {                
                pulsaMPlus();
            }
            if (e.getActionCommand().equals("M-")) {                
                pulsaMMinus();
            }
            if (e.getActionCommand().equals(".")) {                
                pulsaDecimal();
            }
            if (e.getActionCommand().equals("+")) {
                pulsaSuma();
            }
            if (e.getActionCommand().equals("-")) {
                pulsaResta();
            }
            if (e.getActionCommand().equals("*")) {
                pulsaProducto();
            }
            if (e.getActionCommand().equals("/")) {
                pulsaCociente();
            }
            if (e.getActionCommand().equals("=") && !campoInferior.getText().equals("")) {
                pulsaIgual();
            }
        }
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton =
     * 
     * @throws NumberFormatException
     */
    public void pulsaIgual() throws NumberFormatException {
        t = true;
        if (tipOp == 1) {
            operacionSuma();
        } else if (tipOp == 2) {
            operacionResta();
        }
        if (tipOp == 3) {
            operacionProducto();
        }
        if (tipOp == 4) {
            operacionCociente();
        }
    }

    /**
     * Realiza la operacion cociente
     * 
     * @throws NumberFormatException
     */
    public void operacionCociente() throws NumberFormatException {
        //operacion para el cociente
        if (Float.parseFloat(campoInferior.getText()) != 0) {
            tipOp = 0;
            textoAuxiliar = "";
            textoAuxiliar += campoSuperior.getText() + campoInferior.getText();
            campoSuperior.setText(textoAuxiliar);
            operando2 = Float.parseFloat(campoInferior.getText());
            resultado = operando1 / operando2;
            campoInferior.setText(String.valueOf(resultado));
        }
    }

    /**
     * Realiza la operacion producto
     * 
     * @throws NumberFormatException
     */
    public void operacionProducto() throws NumberFormatException {
        //operacion para la multiplicacion
        tipOp = 0;
        textoAuxiliar = "";
        textoAuxiliar += campoSuperior.getText() + campoInferior.getText();
        campoSuperior.setText(textoAuxiliar);
        operando2 = Float.parseFloat(campoInferior.getText());
        resultado = operando1 * operando2;
        campoInferior.setText(String.valueOf(resultado));
    }

    /**
     * Realiza la operacion resta
     * 
     * @throws NumberFormatException
     */
    public void operacionResta() throws NumberFormatException {
        //operacion para la resta
        tipOp = 0;
        textoAuxiliar = "";
        textoAuxiliar += campoSuperior.getText() + campoInferior.getText();
        campoSuperior.setText(textoAuxiliar);
        operando2 = Float.parseFloat(campoInferior.getText());
        resultado = operando1 - operando2;
        campoInferior.setText(String.valueOf(resultado));
    }

    /**
     * Realiza la operacion suma
     * 
     * @throws NumberFormatException
     */
    public void operacionSuma() throws NumberFormatException {
        //operacion para la suma
        tipOp = 0;
        textoAuxiliar = "";
        textoAuxiliar += campoSuperior.getText() + campoInferior.getText();
        campoSuperior.setText(textoAuxiliar);
        operando2 = Float.parseFloat(campoInferior.getText());
        resultado = operando1 + operando2;
        campoInferior.setText(String.valueOf(resultado));
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton M-
     * 
     * @throws NumberFormatException
     */
    public void pulsaMMinus() throws NumberFormatException {
        //restar valor de la pantalla con el valor de la memoria
        memoria -= Float.parseFloat(campoInferior.getText());
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton M+
     * 
     * @throws NumberFormatException
     */
    public void pulsaMPlus() throws NumberFormatException {
        //sumar valor de la pantalla con el valor de la memoria
        memoria += Float.parseFloat(campoInferior.getText());
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton Ms
     * 
     * @throws NumberFormatException
     */
    public void pulsaMS() throws NumberFormatException {
        //guardar un valor en la memoria
        ms.setForeground(Color.red);
        memoria = Float.parseFloat(campoInferior.getText());
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton Mr
     * 
     */
    public void pulsaMR() {
        //para mostrar valor almacenado en la memoria
        campoSuperior.setText("");
        campoInferior.setText(String.valueOf(memoria));
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton Mc
     * 
     */
    public void pulsaMC() {
        //para limpiar la memoria de la calculadora
        ms.setForeground(Color.black);
        campoSuperior.setText("");
        campoInferior.setText("0");
        memoria = 0;
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton r
     * 
     * @throws NumberFormatException
     */
    public void pulsaRaiz() throws NumberFormatException {
        campoSuperior.setText("");
        Float a = Float.parseFloat(campoInferior.getText());
        campoInferior.setText("" + Math.sqrt(a));
    }

    /**
     * Procedimiento a realizar en caso de pulsar algun numero
     * 
     * @param e
     */
    public void pulsaNumero(ActionEvent e) {
        //cuando se oprimen numeros
        
        if (campoSuperior.getText().equals("")) {
            textoAuxiliar += e.getActionCommand();
            campoInferior.setText(textoAuxiliar);
        } else {
            if (tipOp == 0) {
                if (t) {
                    textoAuxiliar = "";
                    
                    campoSuperior.setText(campoInferior.getText());
                    textoAuxiliar += e.getActionCommand();
                    campoInferior.setText(textoAuxiliar);
                    t = false;
                } else {
                    textoAuxiliar = "";
                    textoAuxiliar += campoInferior.getText() + e.getActionCommand();
                    campoInferior.setText(textoAuxiliar);
                }
            } else {
                textoAuxiliar = "";
                textoAuxiliar += campoInferior.getText() + e.getActionCommand();
                campoInferior.setText(textoAuxiliar);
            }
        }
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton .
     * 
     */
    public void pulsaDecimal() {
        //usar el punto para los decimales
        textoAuxiliar = "";
        if (numeros[10].isEnabled()) {
            numeros[10].setEnabled(false);
            textoAuxiliar = campoInferior.getText() + ".";
            campoInferior.setText(textoAuxiliar);
        }
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton C
     * 
     */
    public void pulsaC() {
        //para reiniciar valores y limpiar pantalla
        tipOp = 0;
        operando1 = 0;
        operando2 = 0;
        resultado = 0;
        campoSuperior.setText("");
        campoInferior.setText("0");
        textoAuxiliar = "";
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton +
     * 
     * @throws NumberFormatException
     */
    public void pulsaSuma() throws NumberFormatException {
        //boton suma
        numeros[10].setEnabled(true);
        textoAuxiliar = "";
        if (tipOp == 1) {

        } else if (tipOp == 0) {//validacion para no chocar con otras operaciones
            if (campoSuperior.getText().equals("")) {
                operando1 = Float.parseFloat(campoInferior.getText());
                textoAuxiliar += campoSuperior.getText() + campoInferior.getText();
                campoSuperior.setText(textoAuxiliar + " + ");
                campoInferior.setText("");
                tipOp = 1;
            } else {
                if (!t) {//validacion para nueva operacion
                    operando1 = Float.parseFloat(campoInferior.getText());
                    textoAuxiliar += campoInferior.getText();
                    campoSuperior.setText(textoAuxiliar + " + ");
                    campoInferior.setText("");
                    tipOp = 1;
                } else {//usar otras operaciones con la suma
                    operando1 = Float.parseFloat(campoInferior.getText());
                    textoAuxiliar += campoSuperior.getText();
                    campoSuperior.setText(textoAuxiliar + " + ");
                    campoInferior.setText("");
                    tipOp = 1;
                }
            }
        }
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton /
     * 
     * @throws NumberFormatException
     */
    public void pulsaCociente() throws NumberFormatException {
        //cuando se decide dividir
        numeros[10].setEnabled(true);
        textoAuxiliar = "";
        if (tipOp == 4) {

        } else if (tipOp == 0) {//validacion para no chocar con otras operaciones
            if (campoSuperior.getText().equals("")) {
                operando1 = Float.parseFloat(campoInferior.getText());
                textoAuxiliar += campoSuperior.getText() + campoInferior.getText();
                campoSuperior.setText(textoAuxiliar + " / ");
                campoInferior.setText("");
                tipOp = 4;
            } else {
                if (!t) {//validacion para nueva operacion
                    operando1 = Float.parseFloat(campoInferior.getText());
                    textoAuxiliar += campoInferior.getText();
                    campoSuperior.setText(textoAuxiliar + " / ");
                    campoInferior.setText("");
                    tipOp = 4;
                } else {//usar otras operaciones con la suma
                    operando1 = Float.parseFloat(campoInferior.getText());
                    textoAuxiliar += campoSuperior.getText();
                    campoSuperior.setText(textoAuxiliar + " / ");
                    campoInferior.setText("");
                    tipOp = 4;
                }
            }
        }
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton -
     * 
     * @throws NumberFormatException
     */
    public void pulsaResta() throws NumberFormatException {
        //cuando se decide restar
        numeros[10].setEnabled(true);
        textoAuxiliar = "";
        if (tipOp == 2) {

        } else if (tipOp == 0) {//validacion para no chocar con otras operaciones
            if (campoSuperior.getText().equals("")) {
                operando1 = Float.parseFloat(campoInferior.getText());
                textoAuxiliar += campoSuperior.getText() + campoInferior.getText();
                campoSuperior.setText(textoAuxiliar + " - ");
                campoInferior.setText("");
                tipOp = 2;
            } else {
                if (!t) {//validacion para nueva operacion
                    operando1 = Float.parseFloat(campoInferior.getText());
                    textoAuxiliar += campoInferior.getText();
                    campoSuperior.setText(textoAuxiliar + " - ");
                    campoInferior.setText("");
                    tipOp = 2;
                } else {//usar otras operaciones con la suma
                    operando1 = Float.parseFloat(campoInferior.getText());
                    textoAuxiliar += campoSuperior.getText();
                    campoSuperior.setText(textoAuxiliar + " - ");
                    campoInferior.setText("");
                    tipOp = 2;
                }
            }
        }
    }

    /**
     * Procedimiento a realizar en caso de pulsar el boton *
     * 
     * @throws NumberFormatException
     */
    public void pulsaProducto() throws NumberFormatException {
        //cuando se decide multiplicar
        numeros[10].setEnabled(true);
        textoAuxiliar = "";
        if (tipOp == 3) {

        } else if (tipOp == 0) {//validacion para no chocar con otras operaciones
            if (campoSuperior.getText().equals("")) {
                operando1 = Float.parseFloat(campoInferior.getText());
                textoAuxiliar += campoSuperior.getText() + campoInferior.getText();
                campoSuperior.setText(textoAuxiliar + " * ");
                campoInferior.setText("");
                tipOp = 3;
            } else {
                if (!t) {//validacion para nueva operacion
                    operando1 = Float.parseFloat(campoInferior.getText());
                    textoAuxiliar += campoInferior.getText();
                    campoSuperior.setText(textoAuxiliar + " * ");
                    campoInferior.setText("");
                    tipOp = 3;
                } else {//usar otras operaciones con la suma
                    operando1 = Float.parseFloat(campoInferior.getText());
                    textoAuxiliar += campoSuperior.getText();
                    campoSuperior.setText(textoAuxiliar + " * ");
                    campoInferior.setText("");
                    tipOp = 3;
                }
            }
        }
    }
}
