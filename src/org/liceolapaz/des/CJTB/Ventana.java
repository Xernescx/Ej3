package org.liceolapaz.des.CJTB;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends JFrame {

	JTextArea textoArea = new JTextArea();
	File archivo = null;

	private int archivoGuardado = 0;

	public Ventana() {
		setTitle("Documento nuevo"); // Titulo
		setDefaultCloseOperation(EXIT_ON_CLOSE); // La X cierra
		setSize(1024, 768); // Tamano
		setResizable(true); //
		setLocationRelativeTo(null); // Centrado
		URL url = getClass().getResource("/lapiz.png"); // Icono de la ventana
		setIconImage(new ImageIcon(url).getImage()); // Colocar la imagen
		crearMenu();
		crearTextarea();

	}

	private void crearTextarea() {
		textoArea.setBackground(Color.BLACK);
		textoArea.setBorder(new LineBorder(Color.DARK_GRAY, 10));
		textoArea.setForeground(Color.magenta);
		textoArea.setCaretColor(Color.WHITE);
		Font fuente = new Font("Calibri", 3, 40);
		textoArea.setFont(fuente);
		add(textoArea);
			}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar(); // barra menu 
		JMenu menuPartida = new JMenu("Archivo"); // Nombre de la barra
		menuPartida.setMnemonic(KeyEvent.VK_P); // Control rapido
		JMenuItem nuevo = new JMenuItem("Nuevo");
		nuevo.setIcon(new ImageIcon(getClass().getResource("/nuevo.png")));
		nuevo.setMnemonic(KeyEvent.VK_N);
		nuevo.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		nuevo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mensaje = "El Texto que no haya guardado se perderá. ¿Quiere crear un nuevo documento?";
				int respuesta = JOptionPane.showConfirmDialog(nuevo, mensaje, "Nuevo documento",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					textoArea.setText(null);
					crearTextarea();
					setTitle("Documento nuevo");
					archivoGuardado = 0;
				} else {

				}
			}
		});
		JMenuItem abrir = new JMenuItem("Abrir");
		textoArea.setText(null);
		abrir.setIcon(new ImageIcon(getClass().getResource("/abrir.png")));
		abrir.setMnemonic(KeyEvent.VK_B);
		abrir.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
		abrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mensaje = "El Texto que no haya guardado se perderá. ¿Quiere abrir documento?";
				int respuesta = JOptionPane.showConfirmDialog(abrir, mensaje, "Abrir documento",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
				
					textoArea.setText(null);
					archivoGuardado = 1;
					abrirArchivo();
					
				}else {
					
				}
			}
		
			private void abrirArchivo() {

				String ruta = JOptionPane.showInputDialog("Introduzca la ruta del fichero");
				String texto = new String();
				if(ruta != null){
					try {
				

				FileReader fr = new FileReader(ruta);
				BufferedReader br = new BufferedReader(fr);
				String s;
				while((s = br.readLine()) != null) {

				texto += s + "\r\n";

				}//textoArea.setText(texto);
				setTitle(archivo.getName());
				br.close();

				} catch(java.io.FileNotFoundException fnfex) {

				System.out.println("Archivo no encontrado: " + fnfex);

				} catch(java.io.IOException ioex) {}
			}else {
				
			}
			}
		});
		JMenuItem guarda = new JMenuItem("Guardar");
		guarda.setIcon(new ImageIcon(getClass().getResource("/guardar.png")));
		guarda.setMnemonic(KeyEvent.VK_G);
		guarda.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		guarda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (archivoGuardado == 0) {
					guardarcomo();
					
					
				} else {

					guardarFichero(textoArea.getText(), archivo);

				}
			}
		});
		JMenuItem guardaComo = new JMenuItem("Guardar Como");
		guardaComo.setIcon(new ImageIcon(getClass().getResource("/guardarcomo.png")));
		guardaComo.setMnemonic(KeyEvent.VK_U);
		guardaComo.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		guardaComo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				guardarcomo();
				
				
				
				
			}
		});
		JMenuItem salir = new JMenuItem("Salir");
		salir.setIcon(new ImageIcon(getClass().getResource("/salir.png")));
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mensaje = "El Texto que no haya guardado se perderá. ¿Quiere Salir?";
				int YES = JOptionPane.showConfirmDialog(nuevo, mensaje, "Salir", JOptionPane.YES_NO_OPTION);
				if (YES == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
						
				}
			}
		});

		menuPartida.add(nuevo);
		menuPartida.add(abrir);
		menuPartida.add(guarda);
		menuPartida.add(guardaComo);
		menuPartida.add(salir);
		menuBar.add(menuPartida);
		setJMenuBar(menuBar);
	}

	protected void guardarcomo() {
		JFileChooser guardar = new JFileChooser();
		guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT", "txt");
		
		guardar.showSaveDialog(null);
		if(guardar.getSelectedFile() != null) {
			guardar.setFileFilter(filter); 
			archivoGuardado = 1;
		archivo = guardar.getSelectedFile() ;
		guardarFichero(textoArea.getText(), archivo);
		}
	}
		

	public void guardarFichero(String cadena, File archivo) {
		
		try {
			FileWriter fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(textoArea.getText());
			pw.close();
			bw = new BufferedWriter(new FileWriter("escribeme.txt", true));
			pw = new PrintWriter(bw);
			pw.print(textoArea.getText());
			int i = 2;
			pw.println(i);
			pw.close();
			setTitle(archivo.getName());
		} catch (java.io.IOException   ioex ) {
		
		}
	}
}
