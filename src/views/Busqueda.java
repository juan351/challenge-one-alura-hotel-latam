package views;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.BusquedaController;
import models.Huesped;
import models.Listable;
import models.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private BusquedaController busquedaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		this.busquedaController = new BusquedaController();
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		
		

		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		JScrollPane scrollPaneReservas = new JScrollPane(tbReservas);
		scrollPaneReservas.setToolTipText("Reservas");
		panel.addTab("Reservas", null, scrollPaneReservas, null);
		
		JScrollPane scrollPaneHuespedes = new JScrollPane(tbHuespedes);
		scrollPaneHuespedes.setToolTipText("Huéspedes");
		panel.addTab("Huespedes", null, scrollPaneHuespedes, null);
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				buscarRegistros(txtBuscar.getText());

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		
		JLabel lblEditar = new JLabel("MODIFICAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
		
         @Override
         public void mouseClicked(MouseEvent e) {
        	 
          	 String objetoAModificar = "";
        	 
        	 
        	 if(tbReservas.getSelectedRowCount() == 1) {
        		 
        		
        		 objetoAModificar = "reserva";
        		 
        	 } else if(tbHuespedes.getSelectedRowCount() == 1) {
     
        		 objetoAModificar = "huesped";
        	 }
        	 
        	 int confirmado = JOptionPane.showConfirmDialog(null, "¿Modificar los datos de la fila seleccionada?");
			
        	 if (JOptionPane.OK_OPTION == confirmado) {
        		 
        		         		 
        		 switch(objetoAModificar) {
        		 
        		 case "reserva":
        			 
        			 
        			 Reserva reserva = new Reserva((Integer) tbReservas.getValueAt(tbReservas.getSelectedRow(), 0),
        					 (Date) tbReservas.getValueAt(tbReservas.getSelectedRow(), 1),
        					 (Date) tbReservas.getValueAt(tbReservas.getSelectedRow(), 2),
        					 Double.valueOf((String) tbReservas.getValueAt(tbReservas.getSelectedRow(), 3)),
        					 (String) tbReservas.getValueAt(tbReservas.getSelectedRow(), 4)
        					 );
        			 
        			 busquedaController.modificarReserva(reserva);
        			 JOptionPane.showMessageDialog(null, "Reserva modificada con éxito.");
        			 break;
        			   
        		 
        		 case "huesped":
        			 
        			 Huesped huesped = new Huesped((Integer) tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 0),
        					 (String) tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 1),
        					 (String) tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 2),
        					 (Date) tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 3),
        					 (String) tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 4),
        					 (String) tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 5),
        					 Integer.valueOf((String) tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 1))
        					 );
        			 
        			 busquedaController.modificarHuesped(huesped);
        			 JOptionPane.showMessageDialog(null, "Modificación exitosa.");
        			 break;
        			 
        		default:
        			 
        		         		 
        	}       		 
					
					
							
					
					
			}
         }
			
		});
		
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int confirmado = JOptionPane.showConfirmDialog(null, "¿Eliminar reserva?");
				Integer id_reserva = null;

				if (JOptionPane.OK_OPTION == confirmado) {
					try {
						
						if(tbReservas.getSelectedRowCount() == 1) {
							
							id_reserva = (Integer) tbReservas.getValueAt(tbReservas.getSelectedRow(), 0);
							
						}
						
						
						
						
					} catch (Exception e2) {
						
						try {
							
							if(tbHuespedes.getSelectedRowCount() == 1) {
								
								id_reserva = (Integer) tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 6);
								
							}
							
							
							
						} catch (Exception e3) {
							JOptionPane.showMessageDialog(contentPane, "Error: " + e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
						
						
						
					}
					
				}
				
				if(id_reserva != null) {
					busquedaController.eliminarHuespedYReserva(id_reserva);
					JOptionPane.showMessageDialog(null, "Reserva eliminada con éxito.");
					modelo.setRowCount(0);
					modeloH.setRowCount(0);
					
					
				}
					

			}
		});
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	
	
	private void buscarRegistros(String criterioBusqueda) {
		
		try {
            
			modelo.setRowCount(0);
			modeloH.setRowCount(0);

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
		
		
		
		
		
		
		List<List<Listable>> listaHuespedesYReservas = busquedaController.buscarHuespedesYReservas(criterioBusqueda);
		
		listaHuespedesYReservas.forEach(fila -> {
			
			modeloH.addRow(
		               new Object[] {
		            		   ((Huesped) fila.get(0)).getId(),
		            		   ((Huesped) fila.get(0)).getNombre(),
		            		   ((Huesped) fila.get(0)).getApellido(),
		            		   ((Huesped) fila.get(0)).getFecha_nacimiento(),
		            		   ((Huesped) fila.get(0)).getNacionalidad(),
		            		   ((Huesped) fila.get(0)).getTelefono(),
		            		   ((Huesped) fila.get(0)).getId_reserva()
		                });
			
			modelo.addRow(
		               new Object[] {
		            		   ((Reserva) fila.get(1)).getId(),
		            		   ((Reserva) fila.get(1)).getFecha_entrada(),
		            		   ((Reserva) fila.get(1)).getFecha_salida(),
		            		   ((Reserva) fila.get(1)).getValor(),
		            		   ((Reserva) fila.get(1)).getForma_pago()
		                });
			
		});
		

	}
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
