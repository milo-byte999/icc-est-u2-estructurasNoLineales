package views;

import models.MapPoint;
import models.VisualizationMode;
import structures.grafos.Graph;
import structures.grafos.PathResult;
import structures.node.Node;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel implements MapView {

    private Image mapaFondo;
    private Graph<MapPoint> graph;

    private List<MapPoint> visitadosAnimados;
    private List<MapPoint> rutaAnimada;
    private Timer timer;

    private MapClickListener clickListener;

    private static final int RADIO_NODO = 10;
    private static final Color COLOR_NODO = new Color(40, 90, 200);
    private static final Color COLOR_ARISTA = new Color(90, 90, 90);
    private static final Color COLOR_VISITADO = new Color(255, 180, 60);
    private static final Color COLOR_RUTA = new Color(220, 40, 40);

    public MapPanel(String rutaImagen) {

        this.graph = new Graph<>();
        this.visitadosAnimados = new ArrayList<>();
        this.rutaAnimada = new ArrayList<>();

        cargarImagen(rutaImagen);

        // Clic para agregar nodos (u otra acción que decida el controlador)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickListener != null) {
                    clickListener.onMapClicked(e.getX(), e.getY());
                }
            }
        });
    }

    public void setClickListener(MapClickListener listener) {
        this.clickListener = listener;
    }

    private void cargarImagen(String rutaImagen) {
        try {
            mapaFondo = ImageIO.read(new File(rutaImagen));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen del mapa: " + e.getMessage());
            mapaFondo = null;
        }
    }

    // ---------- Implementación de MapView ----------

    @Override
    public void mostrarGrafo(Graph<MapPoint> graph) {
        this.graph = graph;
        repaint();
    }

    @Override
    public void mostrarResultado(PathResult<MapPoint> resultado, VisualizationMode modo) {

        detenerAnimacionSiActiva();

        visitadosAnimados = new ArrayList<>();
        rutaAnimada = new ArrayList<>();

        if (modo == VisualizationMode.EXPLORATION) {
            animarExploracion(resultado);
        } else {
            animarSoloRuta(resultado);
        }
    }

    @Override
    public void limpiarRecorrido() {
        detenerAnimacionSiActiva();
        visitadosAnimados.clear();
        rutaAnimada.clear();
        repaint();
    }

    @Override
    public void mostrarError(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    // ---------- Animaciones ----------

    private void animarExploracion(PathResult<MapPoint> resultado) {

        List<MapPoint> visitados = resultado.getVisitados();
        List<MapPoint> ruta = resultado.getPath();

        // Primero anima todos los visitados, uno por uno.
        // Cuando termina, empieza a animar la ruta final.
        int[] indice = {0};

        timer = new Timer(300, null);
        timer.addActionListener(e -> {

            if (indice[0] < visitados.size()) {
                visitadosAnimados.add(visitados.get(indice[0]));
                indice[0]++;
                repaint();
            } else {
                timer.stop();
                animarSoloRuta(new PathResult<>(visitados, ruta));
            }
        });
        timer.start();
    }

    private void animarSoloRuta(PathResult<MapPoint> resultado) {

        List<MapPoint> ruta = resultado.getPath();

        if (ruta.isEmpty()) {
            mostrarError("No se encontró una ruta entre los nodos seleccionados.");
            return;
        }

        int[] indice = {0};

        timer = new Timer(300, null);
        timer.addActionListener(e -> {

            if (indice[0] < ruta.size()) {
                rutaAnimada.add(ruta.get(indice[0]));
                indice[0]++;
                repaint();
            } else {
                timer.stop();
            }
        });
        timer.start();
    }

    private void detenerAnimacionSiActiva() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    // ---------- Dibujo ----------

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarFondo(g2);
        dibujarAristas(g2);
        dibujarVisitados(g2);
        dibujarRuta(g2);
        dibujarNodos(g2);
    }

    private void dibujarFondo(Graphics2D g2) {
        if (mapaFondo != null) {
            g2.drawImage(mapaFondo, 0, 0, getWidth(), getHeight(), this);
        } else {
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private void dibujarAristas(Graphics2D g2) {

        g2.setStroke(new BasicStroke(3));
        g2.setColor(COLOR_ARISTA);

        for (Node<MapPoint> nodo : graph.getNodes()) {

            MapPoint origen = nodo.getValue();

            for (Node<MapPoint> vecino : graph.getVecinos(origen)) {
                MapPoint destino = vecino.getValue();
                g2.drawLine(origen.getX(), origen.getY(), destino.getX(), destino.getY());
            }
        }
    }

    private void dibujarNodos(Graphics2D g2) {

        g2.setFont(new Font("Arial", Font.BOLD, 12));

        for (Node<MapPoint> nodo : graph.getNodes()) {
            MapPoint punto = nodo.getValue();
            dibujarUnNodo(g2, punto, COLOR_NODO);
        }
    }

    private void dibujarVisitados(Graphics2D g2) {
        for (MapPoint punto : visitadosAnimados) {
            dibujarUnNodo(g2, punto, COLOR_VISITADO);
        }
    }

    private void dibujarRuta(Graphics2D g2) {

        g2.setStroke(new BasicStroke(5));
        g2.setColor(COLOR_RUTA);

        for (int i = 0; i < rutaAnimada.size() - 1; i++) {
            MapPoint a = rutaAnimada.get(i);
            MapPoint b = rutaAnimada.get(i + 1);
            g2.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
        }

        for (MapPoint punto : rutaAnimada) {
            dibujarUnNodo(g2, punto, COLOR_RUTA);
        }
    }

    private void dibujarUnNodo(Graphics2D g2, MapPoint punto, Color color) {

        g2.setColor(color);
        g2.fillOval(punto.getX() - RADIO_NODO, punto.getY() - RADIO_NODO,
                RADIO_NODO * 2, RADIO_NODO * 2);

        g2.setColor(Color.BLACK);
        g2.drawOval(punto.getX() - RADIO_NODO, punto.getY() - RADIO_NODO,
                RADIO_NODO * 2, RADIO_NODO * 2);

        g2.drawString(punto.getId(), punto.getX() + RADIO_NODO + 2, punto.getY() - RADIO_NODO);
    }
}