package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.TextArea;

public class FrmListado extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextArea txtSalida;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmListado frame = new FrmListado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmListado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnListado.setBounds(324, 29, 89, 23);
		contentPane.add(btnListado);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		TextArea txtSalida = new TextArea();
		txtSalida.setBounds(0, 58, 424, 175);
		contentPane.add(txtSalida);
		
	}
	
	
	void registrar() {
		//leer los campos
		String usuario = txtUsuario.getText();
		
		//validación 
		// select * from tb_ xx where usr = ? an cla = ?
		// 1. establecer conexion -> que unidad de persistencia usarás
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion02");
		// permite que las entidades trabajen con la unidad..
		EntityManager em = fabrica.createEntityManager();
		
		List<Usuario> lstUsuario = em.createQuery("Select  u from u where u.nom_usua like concat('%', : xtipo, '%'", Usuario.class).
				setParameter("xtipo" , usuario).
				getResultList();
		for(Usuario u : lstUsuario) {
			System.out.println("Codigo... :" + u.getCod_usua());
			System.out.println("Nombre....: " + u.getNom_usua());
			System.out.println("tipo.... : " + u.getIdtipo());
		}
		
		
		
		/*List<usuario> lstUsuarios =  em.createQuery("select  u from Usuario u where u.idtipo = :xtipo ", Usuario.class).
				setParameter("xmail", usuario).
				getSingleResult();*/
		
		/* try {
			Usuario u = em.createQuery("select  u from Usuario u where u.correo = :xmail and u.clave = :xcla", Usuario.class).
					setParameter("xmail", usuario).
					getSingleResult();
			System.out.println(u);
			JOptionPane.showMessageDialog(null, "Bienvanido " + u.getNom_usua());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error" + e);
			
		}*/
				
	}
}
