package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import modelo.Galeria;
import usuarios.Artista;
import usuarios.Comprador;
import usuarios.Usuario;

public class DialogRegistrarPintura extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAlto;
	private JTextField txtAncho;
	private static RegistroPiezasAdmin registros;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			DialogRegistrarPintura dialog = new DialogRegistrarPintura(registros);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogRegistrarPintura(RegistroPiezasAdmin frameP) {
		
		registros = frameP;
		
		setBounds(100, 100, 450, 411);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(253, 255, 252));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Especificaciones adicionales");
			lblNewLabel.setForeground(new Color(30, 163, 177));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(20, 0, 231, 39);
			contentPanel.add(lblNewLabel);
		}
		{
			txtAlto = new JTextField();
			txtAlto.setToolTipText("Alto de la pintura (Ej: 55)");
			txtAlto.setText("Ingrese el alto de la pintura");
			txtAlto.setBorder(new LineBorder(new Color(30, 163, 177), 2, true));
			txtAlto.setBackground(new Color(253, 255, 252));
			txtAlto.setBounds(41, 76, 268, 29);
			contentPanel.add(txtAlto);
			txtAlto.setColumns(10);
		}
		{
			txtAncho = new JTextField();
			txtAncho.setToolTipText("Ancho de la pintura (Ej: 63)");
			txtAncho.setText("Ingrese el ancho de la pintura");
			txtAncho.setBorder(new LineBorder(new Color(30, 163, 177), 2));
			txtAncho.setBackground(new Color(253, 255, 252));
			txtAncho.setColumns(10);
			txtAncho.setBounds(41, 115, 268, 29);
			contentPanel.add(txtAncho);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(30, 163, 177));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(new Color(30, 163, 177));
				okButton.setBackground(new Color(253, 255, 252));
				okButton.setActionCommand("OK5");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(30, 163, 177));
				cancelButton.setBackground(new Color(253, 255, 252));
				cancelButton.setActionCommand("Cancel4");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void registroCancelado(){
		DialogsAdvertencias canceladoDialog = new DialogsAdvertencias();
		canceladoDialog.setVisible(true);
		
	}
	
	

	
	public void registroAceptado(){
		
		if (  (txtAlto.getText()).isEmpty() || (txtAncho.getText()).isEmpty()  ){
			
			DialogsAdvertencias fechaDialog = new DialogsAdvertencias();
			fechaDialog.cambiarAdvertencia("Campo vacío", "Uno o algunos de los campos se encuentran vacíos.");
			fechaDialog.setVisible(true);
			
		}
		
		else if(    Galeria.esNumero((txtAlto.getText()) ) == false || Galeria.esNumero((txtAncho.getText()) ) == false  ){
			DialogsAdvertencias numerosDialog = new DialogsAdvertencias();
			numerosDialog.cambiarAdvertencia("Campo incorrecto", "Uno o algunos de los campos que deberían ser números, no lo son.");
			numerosDialog.setVisible(true);
			
		}
		
		else {
			enviarDatos();
		}
			
		
		
	
		
	}
	
	public void enviarDatos(){
		
	
		String titulo = registros.getTxtTitulo().getText();
		int valor = Integer.parseInt( registros.getTxtValor().getText() );
		String fechaCreacion = registros.getTxtFechaDeCreacin().getText();
		String lugarCreacion = registros.getTxtFechaDeCreacin().getText();
		
		Comprador dueño =((Comprador)registros.galeriaInicio.obtenerUsuarioPorLogin("fake"));
		
		HashMap<String, Integer> ventas = new HashMap<String, Integer>();
		ArrayList<Usuario>  dueños = new ArrayList<Usuario>();
		List<String> autoresNombres = registros.getListAutores().getSelectedValuesList();
		
		
		ArrayList<Artista>  autores = new ArrayList<Artista>();
		
		
		for (String autor : autoresNombres) {
		Artista artista = (Artista)registros.galeriaInicio.obtenerUsuarioPorNombre(autor);
			autores.add(artista);
		}
		
		Double alto = Double.parseDouble(txtAlto.getText());
		Double ancho = Double.parseDouble(txtAncho.getText());
		
		
		registros.galeriaInicio.crearPintura(titulo, valor, fechaCreacion, 
				lugarCreacion, dueño, autores, dueños, ventas, "123", false, true, true, true, "0", alto, ancho);
		registros.galeriaInicio.salvarGaleria("GaleriaAnterior.json");
		
		
		
		DialogsAdvertencias aceptacionDialog = new DialogsAdvertencias();
		aceptacionDialog.cambiarAdvertencia("Aceptación", "Su pieza ha sido registrada con éxito");
		aceptacionDialog.setVisible(true);
		setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if("OK5".equals(comando)) {
			registroAceptado();
			
		}else if("Cancel4".equals(comando)) {
			registroCancelado();
		}
		}
		
		
	}

