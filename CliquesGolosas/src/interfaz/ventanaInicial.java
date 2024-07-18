package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class ventanaInicial {

    private JFrame frmProgramaParaCalcular;
    private JTextField textField;

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ventanaInicial window = new ventanaInicial();
                    window.frmProgramaParaCalcular.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ventanaInicial() 
    {
        initialize();
    }

   
    private void initialize() 
    {
        frmProgramaParaCalcular = new JFrame();
        frmProgramaParaCalcular.setIconImage(Toolkit.getDefaultToolkit().getImage(ventanaInicial.class.getResource("/interfaz/img_interfaz/img_carita.png")));
        frmProgramaParaCalcular.setTitle("Programa para calcular cliques máximas");
        frmProgramaParaCalcular.setBounds(100, 100, 895, 576);
        frmProgramaParaCalcular.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmProgramaParaCalcular.getContentPane().setLayout(null);
        
        JButton cancelar_button = new JButton("cancelar");
        cancelar_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        cancelar_button.setForeground(new Color(255, 204, 255));
        cancelar_button.setBackground(new Color(153, 0, 153));
        cancelar_button.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 13));
        cancelar_button.setBounds(476, 364, 115, 36);
        frmProgramaParaCalcular.getContentPane().add(cancelar_button);
        
        JButton continuar_button = new JButton("continuar");
        continuar_button.setForeground(new Color(255, 204, 255));
        continuar_button.setBackground(new Color(153, 0, 153));
        continuar_button.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 13));
        continuar_button.setBounds(271, 364, 115, 36);
        frmProgramaParaCalcular.getContentPane().add(continuar_button);
        continuar_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                try {
                    int numVertices = Integer.parseInt(input);
                    if (numVertices > 1) {
                        ventanaPrincipal ventanaPrincipal = new ventanaPrincipal(numVertices);
                        ventanaPrincipal.setVisible(true);
                        frmProgramaParaCalcular.dispose(); // Aquí se cierra la ventana actual
                    } else {
                        JOptionPane.showMessageDialog(null, "El número debe ser mayor a 1.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Los datos aceptados son únicamente números mayores a 1");
                }
            }
        });
        
        textField = new JTextField();
        textField.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
        textField.setBounds(417, 264, 39, 28);
        frmProgramaParaCalcular.getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("<html><div style='text-align: center;'> Para comenzar te pedimos que ingreses la cantidad de vértices que tendrá el grafo seguido de continuar, luego te indicaremos el paso a paso </div></html>");
        lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
        frmProgramaParaCalcular.getContentPane().add(lblNewLabel_1);
        lblNewLabel_1.setOpaque(true); 
        lblNewLabel_1.setBackground(new Color(255, 204, 255)); 
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setFont(new Font("MS Reference Sans Serif", Font.BOLD | Font.ITALIC, 17));
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBounds(123, 182, 607, 126);
        

        JLabel img_fondo = new JLabel("");
        img_fondo.setIcon(new ImageIcon(ventanaInicial.class.getResource("/interfaz/img_interfaz/img_fondo.png")));
        img_fondo.setBounds(0, 0, 881, 539);
        frmProgramaParaCalcular.getContentPane().add(img_fondo);
        
        JLabel lblNewLabel_2 = new JLabel("<html><div style='text-align: center;'>¡Hola! ¡Calculemos juntos la clique máxima de un grafo!</div></html>");
        lblNewLabel_2.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 23));
        lblNewLabel_2.setOpaque(true); 
        lblNewLabel_2.setBackground(new Color(255, 204, 255));
        lblNewLabel_2.setBounds(185, 93, 477, 67);
        img_fondo.add(lblNewLabel_2);
    }

	public void setVisible(boolean b) {
		frmProgramaParaCalcular.setVisible(b);
		
	}
}
