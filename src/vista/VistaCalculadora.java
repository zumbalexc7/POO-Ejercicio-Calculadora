/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Alex Zumba
 */
public class VistaCalculadora extends JFrame {

    private JPanel panelNumeros;
    private JPanel panelOperaciones;
    private JButton boton1;
    private JTextField cuadroOperaciones;
    private String operacion;
    private double resultado;
    private boolean nuevaOperacion;

    public VistaCalculadora() {
        super();
        setTitle("         **********CALCULADORA**********");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());

        cuadroOperaciones = new JTextField("0", 80);

        cuadroOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
        cuadroOperaciones.setFont(new Font("Times", Font.ROMAN_BASELINE, 25));
        cuadroOperaciones.setHorizontalAlignment(JTextField.RIGHT);
        cuadroOperaciones.setEditable(false);
        cuadroOperaciones.setBackground(Color.yellow);
        panel.add("North", cuadroOperaciones);

        panelNumeros = new JPanel();
        panelNumeros.setLayout(new GridLayout(4, 3));
        panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));

        for (int i = 9; i >= 0; i--) {
            botonNumerico("" + i);

        }

        botonNumerico(".");

        panel.add("Center", panelNumeros);

        panelOperaciones = new JPanel();
        panelOperaciones.setLayout(new GridLayout(6, 1));
        panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));

        botonOperacion("+");
        botonOperacion("-");
        botonOperacion("*");
        botonOperacion("/");
        botonOperacion("=");
        botonOperacion("C");

        panel.add("East", panelOperaciones);

        validate();
    }

    public void botonNumerico(String digito) {
        boton1 = new JButton();
        boton1.setText(digito);
        boton1.setForeground(Color.black);
        boton1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evento) {
                boton1 = (JButton) evento.getSource();
                numeroPulsado(boton1.getText());
            }
        });

        panelNumeros.add(boton1);
    }

    public void botonOperacion(String operacion) {
        boton1 = new JButton(operacion);
        boton1.setForeground(Color.red);
        boton1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evento) {
                boton1 = (JButton) evento.getSource();
                operacionPulsado(boton1.getText());
            }
        });
        panelOperaciones.add(boton1);

    }

    public void numeroPulsado(String digito) {
        if (cuadroOperaciones.getText().equals("0") || nuevaOperacion) {
            cuadroOperaciones.setText(digito);
        } else {
            cuadroOperaciones.setText(cuadroOperaciones.getText() + digito);

        }
        nuevaOperacion = false;
    }

    public void operacionPulsado(String tecla) {
        if (tecla.equals("=")) {
            calcularResultado();
        } else if (tecla.equals("C")) {
            resultado = 0;
            cuadroOperaciones.setText("");
            nuevaOperacion = true;

        } else {
            operacion = tecla;
            if ((resultado > 0) && !nuevaOperacion) {
                calcularResultado();
            } else {
                resultado = new Double(cuadroOperaciones.getText());
            }
        }
        nuevaOperacion = true;
    }

    public void calcularResultado() {
        if (operacion.equals("+")) {
            resultado += new Double(cuadroOperaciones.getText());

        } else if (operacion.equals("-")) {
            resultado -= new Double(cuadroOperaciones.getText());

        }
        if (operacion.equals("*")) {
            resultado *= new Double(cuadroOperaciones.getText());

        }
        if (operacion.equals("/")) {
            resultado /= new Double(cuadroOperaciones.getText());

        }
        cuadroOperaciones.setText("" + resultado);
        operacion = "";
    }

}
