package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Point;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;

import logica.Logica;

public class ventanaPrincipal 
{

	private JPanel panel;
	private int cantVertices;
	private JFrame frmProgramaParaCalcular;
	private List<Point> coordenadasVertices;
	private List<int[]> arcos;
	private int verticesMarcados;
	private Logica algoritmo;
	private HashSet<Integer> cliqueMaxima;


	public ventanaPrincipal(int numVertices) 
	{
		this.cantVertices = numVertices;
		this.coordenadasVertices = new ArrayList<>();
		this.arcos = new ArrayList<>();
		this.cliqueMaxima = new HashSet<>();
		this.verticesMarcados = 0;
		this.algoritmo = new Logica(numVertices);
		this.arcos = new ArrayList<>();
		this.cliqueMaxima = new HashSet<>();
		this.algoritmo = new Logica(numVertices);
		initialize();
	}

	private void initialize() 
	{
		frmProgramaParaCalcular = new JFrame();
		frmProgramaParaCalcular.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ventanaPrincipal.class.getResource("/interfaz/img_interfaz/img_carita.png")));
		frmProgramaParaCalcular.setTitle("Programa para calcular cliques máximas");
		frmProgramaParaCalcular.setBounds(100, 100, 895, 576);
		frmProgramaParaCalcular.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProgramaParaCalcular.getContentPane().setLayout(null);



		panel = new JPanel() 
		{
			
			
			@Override
			protected void paintComponent(Graphics g) 
			{
			    super.paintComponent(g);


			    Graphics2D g2d = (Graphics2D) g;

			    g.setFont(new Font("Arial", Font.BOLD, 12));

			    for (int i = 0; i < coordenadasVertices.size(); i++) 
			    {
			        Point p = coordenadasVertices.get(i);
			        if (cliqueMaxima.contains(i)) {
			            g.setColor(new Color(0, 128, 0));
			        } else {
			            g.setColor(new Color(108,70,117)); 
			        }
			        g.fillOval(p.x - 15, p.y - 15, 30, 30); 
			        g.setColor(Color.WHITE); 
			        g.drawString(String.valueOf(i), p.x - 5, p.y + 5);
			    }

			    g.setColor(Color.BLACK);
			    for (int i = 0; i < coordenadasVertices.size(); i++) 
			    {
			        Point p = coordenadasVertices.get(i);
			        int peso = algoritmo.getPeso(i);
			        g.drawString("P: (" + peso + ")", p.x + 20, p.y + 5);
			    }

			    g2d.setStroke(new BasicStroke(2));

			    for (int[] arco : arcos) {
			        Point p1 = coordenadasVertices.get(arco[0]);
			        Point p2 = coordenadasVertices.get(arco[1]);
			        if (cliqueMaxima.contains(arco[0]) && cliqueMaxima.contains(arco[1])) 
			        {
			            g.setColor(new Color(0, 128, 0)); 
			        } else {
			            g.setColor(Color.BLACK);
			        }
			        Point punto1 = calcularPuntoEnBorde(p1, p2, 15);
			        Point punto2 = calcularPuntoEnBorde(p2, p1, 15);
			        g.drawLine(punto1.x, punto1.y, punto2.x, punto2.y);
			    }
			}



		};

		panel.setBounds(58, 151, 606, 363);
		panel.setBackground(Color.WHITE);
		frmProgramaParaCalcular.getContentPane().add(panel);
		panel.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (verticesMarcados < cantVertices) {
					
					JTextField pesoField = new JTextField(5);
					JPanel inputPanel = new JPanel(new GridLayout(0, 1));
					inputPanel.add(new JLabel("Por favor, indica el peso de este vértice:"));
					inputPanel.add(pesoField);

					int result = JOptionPane.showConfirmDialog(null, inputPanel, "Agregar Vertice",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (result == JOptionPane.OK_OPTION) 
					{
						try 
						{
							int peso = Integer.parseInt(pesoField.getText());
							int x = e.getX();
							int y = e.getY();
							coordenadasVertices.add(new Point(x, y));
							algoritmo.agregarPeso(verticesMarcados, peso);
							verticesMarcados++;
							panel.repaint();
						} 
						catch (NumberFormatException ex) 
						{
							JOptionPane.showMessageDialog(null, "El valor ingresado no es válido");
						}
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog(null, "Ya se alcanzó la totalidad de vértices a marcar");
				}
			}
		});


		JButton btn_crearArco = new JButton("Crear arco");
		btn_crearArco.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (verticesMarcados < cantVertices)
					JOptionPane.showMessageDialog(null, "Faltan vértices por marcar");
				else 
				{
					JTextField origenField = new JTextField(3);
					JTextField destinoField = new JTextField(3);

					JPanel inputPanel = new JPanel(new GridLayout(0, 1));
					inputPanel.add(new JLabel("Ingrese Origen del Arco: "));
					inputPanel.add(origenField);
					inputPanel.add(new JLabel("Ingrese Destino del Arco: "));
					inputPanel.add(destinoField);

					int result = JOptionPane.showConfirmDialog(null, inputPanel, "Crear Arco",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) 
					{
						try 
						{
							int origen = Integer.parseInt(origenField.getText());
							int destino = Integer.parseInt(destinoField.getText());

							if (algoritmo.esArcoValido(origen, destino)) 
							{
								arcos.add(new int[] { origen, destino });
								algoritmo.agregarArco(origen, destino);
								panel.repaint();
							} else 
							{
								JOptionPane.showMessageDialog(null, "Vértices no válidos");
							}
						} 
						catch (NumberFormatException f)
						{
							JOptionPane.showMessageDialog(null, "Datos no válidos");
						}

					}
				}
			}
		});
		
		
		btn_crearArco.setBackground(new Color(255, 204, 204));
		btn_crearArco.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		btn_crearArco.setBounds(674, 174, 141, 41);
		frmProgramaParaCalcular.getContentPane().add(btn_crearArco);

		JButton btn_borrarArco = new JButton("<html><div style='text-align: center;'> Deshacer arco</div></html>");
		btn_borrarArco.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (algoritmo.cantidadArcos() < 0)
					JOptionPane.showMessageDialog(null, "No hay arcos para eliminar");
				else {

					JTextField origenField = new JTextField(5);
					JTextField destinoField = new JTextField(5);

					JPanel inputPanel = new JPanel(new GridLayout(0, 1));
					inputPanel.add(new JLabel("Origen:"));
					inputPanel.add(origenField);
					inputPanel.add(new JLabel("Destino:"));
					inputPanel.add(destinoField);

					int result = JOptionPane.showConfirmDialog(null, inputPanel, "Eliminar Arco",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (result == JOptionPane.OK_OPTION) 
					{
						try {
							int origen = Integer.parseInt(origenField.getText());
							int destino = Integer.parseInt(destinoField.getText());

							boolean arcoExiste = false;
							for (int i = 0; i < arcos.size(); i++) {
								int[] arista = arcos.get(i);
								if ((arista[0] == origen && arista[1] == destino)
										|| (arista[0] == destino && arista[1] == origen)) 
								{
									arcos.remove(i);
									arcoExiste = true;
									break;
								}
							}

							if (arcoExiste) 
							{
								algoritmo.borrarArco(origen, destino);
								panel.repaint();
							} 
							else 
							{
								JOptionPane.showMessageDialog(null, "El arco no existe");
							}
						} catch (NumberFormatException f) {
							JOptionPane.showMessageDialog(null, "Hay errores en el ingreso de datos");
						}
					}

				}
			}
		});
		
		btn_borrarArco.setBackground(new Color(255, 204, 204));
		btn_borrarArco.setBounds(674, 225, 141, 41);
		btn_borrarArco.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		frmProgramaParaCalcular.getContentPane().add(btn_borrarArco);

		JButton btn_calcularMaxClique = new JButton(
				"<html><div style='text-align: center;'> calcular MaxClique</div></html>");
		btn_calcularMaxClique.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			    if (verticesMarcados == cantVertices && algoritmo.cantidadArcos() > 0) 
			    {
			        try 
			        {
			            cliqueMaxima = new HashSet<>();
			            cliqueMaxima = algoritmo.resolverCliqueMaxima();
			            panel.repaint();
			        } 
			        catch (IllegalArgumentException ex) 
			        {
			            JOptionPane.showMessageDialog(null, "El grafo no tiene una clique máxima.");
			        }
			    } 
			    else 
			    {
			        JOptionPane.showMessageDialog(null,
			                "Por favor, primero debe ubicar todos los vértices y debe haber al menos 1 arco.");
			    }
			}

		});
		btn_calcularMaxClique.setVerticalAlignment(SwingConstants.TOP);
		btn_calcularMaxClique.setBackground(new Color(255, 204, 204));
		btn_calcularMaxClique.setBounds(674, 327, 141, 45);
		btn_calcularMaxClique.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		frmProgramaParaCalcular.getContentPane().add(btn_calcularMaxClique);

		JButton btn_cambiarPeso = new JButton("Cambiar Peso");
		btn_cambiarPeso.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

				if (verticesMarcados == cantVertices) 
				{
					JTextField verticeField = new JTextField(5);
					JTextField pesoField = new JTextField(5);

					JPanel inputPanel = new JPanel(new GridLayout(0, 1));
					inputPanel.add(new JLabel("Vertice:"));
					inputPanel.add(verticeField);
					inputPanel.add(new JLabel("Peso:"));
					inputPanel.add(pesoField);

					int result = JOptionPane.showConfirmDialog(null, inputPanel, "Agregar Peso Vertice",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (result == JOptionPane.OK_OPTION) 
					{
						try {
							int vertice = Integer.parseInt(verticeField.getText());
							int peso = Integer.parseInt(pesoField.getText());

							if (vertice >= 0 && vertice < cantVertices) 
							{
								algoritmo.cambiarPeso(vertice, peso);
								panel.repaint();
							} 
							else 
							{
								JOptionPane.showMessageDialog(null, "Existe error con el vértice ingresado");
							}
						} 
						catch (NumberFormatException f) 
						{
							JOptionPane.showMessageDialog(null, "Existe error con los datos ingresados");
						}
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog(null,
							"Se podrán cambiar los pesos una vez que se ingresen todos los vértices");
				}

			}
		});
		btn_cambiarPeso.setBackground(new Color(255, 204, 204));
		btn_cambiarPeso.setBounds(674, 276, 141, 41);
		btn_cambiarPeso.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		frmProgramaParaCalcular.getContentPane().add(btn_cambiarPeso);

		JButton btn_volverMenu = new JButton("Volver al menú");
		btn_volverMenu.setBackground(new Color(255, 204, 204));
		btn_volverMenu.setBounds(674, 473, 158, 41);
		btn_volverMenu.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		frmProgramaParaCalcular.getContentPane().add(btn_volverMenu);
		btn_volverMenu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				{
					ventanaInicial menu = new ventanaInicial();
					menu.setVisible(true);
					frmProgramaParaCalcular.dispose();
				}
			}
		});

		JLabel lbl_imgProfe = new JLabel("");
		lbl_imgProfe.setIcon(new ImageIcon(ventanaPrincipal.class.getResource("/interfaz/img_interfaz/img_profe.png")));
		lbl_imgProfe.setBounds(58, 28, 121, 99);
		frmProgramaParaCalcular.getContentPane().add(lbl_imgProfe);


		JLabel lbl_instrucciones = new JLabel(
				"<html><div style='text-align: center;'> Hacé un click en el recuadro por cada vértice del grafo, recordá que la cantidad permitida es la mencionada en la pantalla anterior. Luego presioná los botones en orden descendente para crear las conexiones del grafo y su clique máxima</div></html>");
		lbl_instrucciones.setForeground(new Color(0, 0, 0));
		lbl_instrucciones.setBackground(new Color(255, 204, 204));
		lbl_instrucciones.setOpaque(true);
		lbl_instrucciones.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 15));
		lbl_instrucciones.setBounds(201, 28, 540, 99);
		frmProgramaParaCalcular.getContentPane().add(lbl_instrucciones);

		JLabel backgroundLabel = new JLabel();
		backgroundLabel
				.setIcon(new ImageIcon(ventanaPrincipal.class.getResource("/interfaz/img_interfaz/img_fondo.png")));
		backgroundLabel.setBounds(0, 0, 895, 576);
		frmProgramaParaCalcular.getContentPane().add(backgroundLabel);
	}

	public void setVisible(boolean b) 
	{
		frmProgramaParaCalcular.setVisible(b);
	}

	private Point calcularPuntoEnBorde(Point centro, Point exterior, int radio) 
	{
		double dx = exterior.x - centro.x;
		double dy = exterior.y - centro.y;
		double distancia = Math.sqrt(dx * dx + dy * dy);
		double escala = radio / distancia;
		int x = (int) (centro.x + dx * escala);
		int y = (int) (centro.y + dy * escala);
		return new Point(x, y);
	}

	class MyPanel extends JPanel 
	{
		@Override
		protected void paintComponent(Graphics g) 
		{
			super.paintComponent(g);

			for (int i = 0; i < coordenadasVertices.size(); i++) 
			{
				Point p = coordenadasVertices.get(i);

				if (cliqueMaxima.contains(i)) 
				{
					g.setColor(Color.BLUE);
				} else {
					g.setColor(Color.BLACK);
				}

				g.drawOval(p.x - 15, p.y - 15, 30, 30);
				g.drawString(String.valueOf(i), p.x - 5, p.y + 5);

				int peso = algoritmo.getPeso(i);
				g.drawString("P: " + peso, p.x - 40, p.y - 10);

			}

			for (int[] arco : arcos) 
			{
				Point p1 = coordenadasVertices.get(arco[0]);
				Point p2 = coordenadasVertices.get(arco[1]);

				if (cliqueMaxima.contains(arco[0]) && cliqueMaxima.contains(arco[1])) 
				{
					g.setColor(Color.GREEN);
				} else {
					g.setColor(Color.BLACK);
				}

				Point punto1 = calcularPuntoEnBorde(p1, p2, 15);
				Point punto2 = calcularPuntoEnBorde(p2, p1, 15);
				g.drawLine(punto1.x, punto1.y, punto2.x, punto2.y);
			}
		}

	}
}
