package views;

import controllers.MapController;
import java.awt.*;
import javax.swing.*;
import models.MapPoint;
import models.VisualizationMode;
import structures.grafos.Graph;
import structures.grafos.PathResult;
import structures.node.Node;

public class MainFrame extends JFrame implements MapView {

    private MapController controller;
    private MapPanel mapPanel;

    private JComboBox<String> comboInicio;
    private JComboBox<String> comboDestino;
    private JComboBox<String> comboAlgoritmo;
    private JComboBox<String> comboModo;

    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnGuardar;
    private JButton btnCargar;
    private JButton btnEliminarNodo;

    // Guarda el id del nodo seleccionado en el mapa mientras se espera el segundo clic para conectar
    private String nodoPendienteConexion;

    private static final int RADIO_DETECCION = 15; // píxeles de tolerancia para hacer clic sobre un nodo

    public MainFrame(MapController controller) {

        this.controller = controller;
        this.nodoPendienteConexion = null;

        setTitle("Mapa de calles - BFS / DFS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mapPanel = new MapPanel();
        mapPanel.setPreferredSize(new Dimension(800, 600));
        mapPanel.setClickListener(this::manejarClicEnMapa);

        add(mapPanel, BorderLayout.CENTER);
        add(construirPanelControles(), BorderLayout.EAST);

        controller.setView(this);
        controller.cargarConfiguracion(); // intenta leer el archivo al iniciar, si existe

        pack();
        setLocationRelativeTo(null);
    }

    // ---------- Construcción de la interfaz ----------

    private JPanel construirPanelControles() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(220, 600));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nodo de inicio:"));
        comboInicio = new JComboBox<>();
        panel.add(comboInicio);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Nodo de destino:"));
        comboDestino = new JComboBox<>();
        panel.add(comboDestino);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Algoritmo:"));
        comboAlgoritmo = new JComboBox<>(new String[]{"BFS", "DFS"});
        panel.add(comboAlgoritmo);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Modo de visualización:"));
        comboModo = new JComboBox<>(new String[]{"EXPLORATION", "FINAL_PATH"});
        panel.add(comboModo);
        panel.add(Box.createVerticalStrut(20));

        btnBuscar = new JButton("Ejecutar búsqueda");
        btnBuscar.addActionListener(e -> ejecutarBusqueda());
        panel.add(btnBuscar);
        panel.add(Box.createVerticalStrut(10));

        btnLimpiar = new JButton("Limpiar recorrido");
        btnLimpiar.addActionListener(e -> controller.limpiar());
        panel.add(btnLimpiar);
        panel.add(Box.createVerticalStrut(20));

        btnEliminarNodo = new JButton("Eliminar nodo de inicio");
        btnEliminarNodo.addActionListener(e -> eliminarNodoSeleccionado());
        panel.add(btnEliminarNodo);
        panel.add(Box.createVerticalStrut(20));

        btnGuardar = new JButton("Guardar configuración");
        btnGuardar.addActionListener(e -> controller.guardarConfiguracion());
        panel.add(btnGuardar);
        panel.add(Box.createVerticalStrut(10));

        btnCargar = new JButton("Cargar configuración");
        btnCargar.addActionListener(e -> controller.cargarConfiguracion());
        panel.add(btnCargar);
        panel.add(Box.createVerticalStrut(20));

        JLabel ayuda = new JLabel(
                "<html>Clic en el mapa:<br>" +
                "- En vacío: crea nodo<br>" +
                "- Sobre un nodo: lo selecciona<br>" +
                "- Sobre otro nodo: crea conexión</html>");
        ayuda.setFont(ayuda.getFont().deriveFont(11f));
        panel.add(ayuda);

        return panel;
    }

    // ---------- Lógica de clics en el mapa ----------

    private void manejarClicEnMapa(int x, int y) {

        String idCercano = buscarNodoCercano(x, y);

        if (idCercano == null) {
            // Clic en espacio vacío: crear nodo nuevo
            String id = JOptionPane.showInputDialog(this, "Id del nuevo nodo:");

            if (id != null && !id.trim().isEmpty()) {
                controller.agregarNodo(id.trim(), x, y);
            }

            nodoPendienteConexion = null;
            return;
        }

        if (nodoPendienteConexion == null) {
            // Primer nodo seleccionado, se espera el segundo clic
            nodoPendienteConexion = idCercano;
            JOptionPane.showMessageDialog(this,
                    "Nodo '" + idCercano + "' seleccionado. Haz clic en otro nodo para conectarlos.");
            return;
        }

        if (nodoPendienteConexion.equals(idCercano)) {
            // Se hizo clic dos veces sobre el mismo nodo, se cancela la selección
            nodoPendienteConexion = null;
            return;
        }

        // Segundo nodo seleccionado: crear la conexión
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿La conexión entre '" + nodoPendienteConexion + "' y '" + idCercano + "' es bidireccional?",
                "Tipo de conexión", JOptionPane.YES_NO_OPTION);

        boolean bidireccional = (opcion == JOptionPane.YES_OPTION);

        controller.agregarConexion(nodoPendienteConexion, idCercano, bidireccional);
        nodoPendienteConexion = null;
    }

    private String buscarNodoCercano(int x, int y) {

        Graph<MapPoint> graph = controller.getGraph();

        for (Node<MapPoint> nodo : graph.getNodes()) {

            MapPoint punto = nodo.getValue();
            double distancia = Math.sqrt(
                    Math.pow(punto.getX() - x, 2) + Math.pow(punto.getY() - y, 2));

            if (distancia <= RADIO_DETECCION) {
                return punto.getId();
            }
        }

        return null;
    }

    // ---------- Acciones de los botones ----------

    private void ejecutarBusqueda() {

        String inicio = (String) comboInicio.getSelectedItem();
        String destino = (String) comboDestino.getSelectedItem();
        String algoritmo = (String) comboAlgoritmo.getSelectedItem();
        String modoTexto = (String) comboModo.getSelectedItem();

        if (inicio == null || destino == null) {
            JOptionPane.showMessageDialog(this, "Debes tener al menos un nodo de inicio y uno de destino.");
            return;
        }

        VisualizationMode modo = VisualizationMode.valueOf(modoTexto);

        controller.buscarRuta(inicio, destino, algoritmo, modo);
    }

    private void eliminarNodoSeleccionado() {

        String id = (String) comboInicio.getSelectedItem();

        if (id == null) {
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el nodo '" + id + "' y todas sus conexiones?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            controller.eliminarNodo(id);
        }
    }

    // ---------- Implementación de MapView ----------

    @Override
    public void mostrarGrafo(Graph<MapPoint> graph) {

        mapPanel.mostrarGrafo(graph);
        actualizarCombos(graph);
    }

    @Override
    public void mostrarResultado(PathResult<MapPoint> resultado, VisualizationMode modo) {
        mapPanel.mostrarResultado(resultado, modo);
    }

    @Override
    public void limpiarRecorrido() {
        mapPanel.limpiarRecorrido();
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void actualizarCombos(Graph<MapPoint> graph) {

        String inicioPrevio = (String) comboInicio.getSelectedItem();
        String destinoPrevio = (String) comboDestino.getSelectedItem();

        comboInicio.removeAllItems();
        comboDestino.removeAllItems();

        for (Node<MapPoint> nodo : graph.getNodes()) {
            String id = nodo.getValue().getId();
            comboInicio.addItem(id);
            comboDestino.addItem(id);
        }

        // Intenta conservar la selección anterior si el nodo todavía existe
        if (inicioPrevio != null) {
            comboInicio.setSelectedItem(inicioPrevio);
        }
        if (destinoPrevio != null) {
            comboDestino.setSelectedItem(destinoPrevio);
        }
    }
}