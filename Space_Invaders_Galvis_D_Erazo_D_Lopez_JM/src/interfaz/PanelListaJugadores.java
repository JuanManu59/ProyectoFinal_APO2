package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mundo.Jugador;
import mundo.Puntaje;

public class PanelListaJugadores extends JPanel implements ListSelectionListener {

	/**
	 * Descripci�n: Jlista de los puntajes de los jugadores logeados
	 */
	private JList<Puntaje> listaJugadores;
	/**
	 * Descripci�n: Modelo de lista que permite mostrar la lista en el panel
	 */
	private DefaultListModel listModel;

	/**
	 * Descripci�n: Relacion con la interfaz principal
	 */
	private InterfazInicio principal;
	/**
	 * Descripci�n: JScrollPane que se implementa en la lista
	 */
	private JScrollPane scrollMostrar;

	/**
	 * Descripci�n: Construye el panel de la lista de los jugadores
	 * 
	 * @param relacion
	 *            Es la relacion con el panelPuntaje
	 * @param mejoresPuntajes
	 *            Un arreglo de tipo Puntaje que contiene los mejores 10 puntajes
	 * @param principal
	 *            Es la relacion con la interfaz principal
	 */
	public PanelListaJugadores(PanelPuntaje relacion, Puntaje[] mejoresPuntajes, InterfazInicio principal) {
		this.principal = principal;

		listaJugadores = new JList<>();

		listModel = new DefaultListModel();
		listaJugadores = new JList<Puntaje>(listModel);

		listaJugadores.setFont(new Font("OCR A Extended", Font.BOLD, 20));

		scrollMostrar = new JScrollPane(this.listaJugadores);
		scrollMostrar.setPreferredSize(new Dimension(400, 550));
		scrollMostrar.setBackground(Color.BLACK);
		listaJugadores.setBackground(Color.ORANGE);
		add(scrollMostrar);

		listaJugadores.addListSelectionListener(this);

		refrescarListaJugadores(mejoresPuntajes);

	}

	/**
	 * Descripci�n: Permite actulzar la lista de los jugadores con los mejores 10
	 * puntajes
	 * 
	 * @param mejoresPuntajes
	 *            un arreglo de tipo Puntaje con los mejores 10 puntajes
	 */
	public void refrescarListaJugadores(Puntaje [] mejoresPuntajes) {

		listModel.removeAllElements();
		for (int i = 0; i < mejoresPuntajes.length; i++) {
			listModel.addElement(mejoresPuntajes[i]);
		}

		listaJugadores.revalidate();
		scrollMostrar.revalidate();
		scrollMostrar.repaint();
		repaint();

	}

	/**
	 * Descripci�n: Permite que al seleccionar un elemento en la lista se genere el
	 * evento de mostrar los datos de ese jugador
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {

		principal.actualizarInfo(listaJugadores.getSelectedValue());

	}

	/**
	 * Descripci�n: Permite agregar un jugador a la lista de los mejores puntajes 
	 * @param usuario
	 */
	public void agregarUsuario(Puntaje usuario) {
		listModel.addElement(usuario);
	}

	/**
	 * Descripci�n: Permite actualizar 
	 * @param jugadores
	 */
	public void actializarLista(Puntaje[] jugadores) {

		listModel.clear();
		for (int i = 0; i < jugadores.length; i++) {
			agregarUsuario(jugadores[i]);
		}
		listaJugadores.revalidate();
		listaJugadores.repaint();
	}

	/**
	 * Descripci�n: Permite actualizar los puntajes del jugador
	 * @param pts
	 */
	public void actializarListaPuntajesXJugador(ArrayList<Puntaje> pts) {

		listModel.clear();
		for (int i = 0; i < pts.size(); i++) {
			agregarUsuario(pts.get(i));
		}

		listaJugadores.revalidate();
		listaJugadores.repaint();
	}

}
