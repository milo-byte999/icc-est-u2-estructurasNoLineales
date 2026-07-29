package controllers;

import java.io.File;
import java.io.IOException;
import models.MapPoint;
import models.VisualizationMode;
import persistence.GraphRepository;
import structures.grafos.Graph;
import structures.grafos.PathFinder;
import structures.grafos.PathResult;
import structures.grafos.implementations.BFSPathFinder;
import structures.grafos.implementations.DFSPathFinder;
import structures.node.Node;
import views.MapView;

public class MapController {

    private Graph<MapPoint> graph;
    private GraphRepository repository;
    private MapView view;
    private String archivoConfiguracion;

    public MapController(GraphRepository repository, String archivoConfiguracion) {
        this.graph = new Graph<>();
        this.repository = repository;
        this.archivoConfiguracion = archivoConfiguracion;
    }

    // La vista se conecta después de crearse (evita dependencia circular en el constructor)
    public void setView(MapView view) {
        this.view = view;
    }

    // ---- Manejo de nodos ----

    public void agregarNodo(String id, int x, int y) {

        if (id == null || id.trim().isEmpty()) {
            avisarError("El identificador del nodo no puede estar vacío.");
            return;
        }

        if (graph.contains(new MapPoint(id, x, y))) {
            avisarError("Ya existe un nodo con el id: " + id);
            return;
        }

        graph.add(new MapPoint(id, x, y));
        actualizarVista();
    }

    public void eliminarNodo(String id) {

        MapPoint punto = buscarPorId(id);

        if (punto == null) {
            avisarError("No existe un nodo con el id: " + id);
            return;
        }

        graph.remove(punto);
        actualizarVista();
    }

    // ---- Manejo de conexiones ----

    public void agregarConexion(String idOrigen, String idDestino, boolean bidireccional) {

        MapPoint origen = buscarPorId(idOrigen);
        MapPoint destino = buscarPorId(idDestino);

        if (origen == null || destino == null) {
            avisarError("No se puede crear la conexión: uno de los nodos no existe.");
            return;
        }

        if (bidireccional) {
            graph.addEdge(origen, destino);
        } else {
            graph.addEdgeUni(origen, destino);
        }

        actualizarVista();
    }

    public void eliminarConexion(String idOrigen, String idDestino, boolean bidireccional) {

        MapPoint origen = buscarPorId(idOrigen);
        MapPoint destino = buscarPorId(idDestino);

        if (origen == null || destino == null) {
            avisarError("No se puede eliminar la conexión: uno de los nodos no existe.");
            return;
        }

        if (bidireccional) {
            graph.removeEdge(origen, destino);
        } else {
            graph.removeEdgeUni(origen, destino);
        }

        actualizarVista();
    }

    // ---- Persistencia ----

    public void guardarConfiguracion() {
        try {
            repository.save(graph, archivoConfiguracion);
        } catch (IOException e) {
            avisarError("Error al guardar la configuración: " + e.getMessage());
        }
    }

    public void cargarConfiguracion() {

        File archivo = new File(archivoConfiguracion);

        if (!archivo.exists()) {
            // Primera ejecución: no hay archivo todavía, se arranca con grafo vacío
            actualizarVista();
            return;
        }

        try {
            graph = repository.load(archivoConfiguracion);
            actualizarVista();
        } catch (IOException e) {
            avisarError("Error al cargar la configuración: " + e.getMessage());
        }
    }

    // ---- Búsqueda de rutas (BFS / DFS) ----

    public void buscarRuta(String idInicio, String idDestino, String algoritmo, VisualizationMode modo) {

        MapPoint inicio = buscarPorId(idInicio);
        MapPoint destino = buscarPorId(idDestino);

        if (inicio == null) {
            avisarError("El nodo de inicio no existe: " + idInicio);
            return;
        }

        if (destino == null) {
            avisarError("El nodo de destino no existe: " + idDestino);
            return;
        }

        PathFinder<MapPoint> finder;

        if (algoritmo.equalsIgnoreCase("BFS")) {
            finder = new BFSPathFinder<>();
        } else if (algoritmo.equalsIgnoreCase("DFS")) {
            finder = new DFSPathFinder<>();
        } else {
            avisarError("Algoritmo no reconocido: " + algoritmo);
            return;
        }

        long inicioTiempo = System.nanoTime();

        PathResult<MapPoint> resultado = finder.find(graph, inicio, destino);

        long finTiempo = System.nanoTime();

        double tiempoMs = (finTiempo - inicioTiempo) / 1_000_000.0;

        System.out.println("===== RESULTADOS =====");
        System.out.println("Algoritmo: " + algoritmo);
        System.out.println("Inicio: " + idInicio);
        System.out.println("Destino: " + idDestino);
        System.out.println("Nodos visitados: " + resultado.getVisitados().size());
        System.out.println("Cantidad de aristas: " + (resultado.getPath().size() - 1));
        System.out.println("Tiempo: " + tiempoMs + " ms");

        if (view != null) {
            view.mostrarResultado(resultado, modo);
        }
    }

    // ---- Utilidades ----

    public void limpiar() {
        if (view != null) {
            view.limpiarRecorrido();
        }
    }

    public Graph<MapPoint> getGraph() {
        return graph;
    }

    private MapPoint buscarPorId(String id) {

        for (Node<MapPoint> node : graph.getNodes()) {
            if (node.getValue().getId().equals(id)) {
                return node.getValue();
            }
        }

        return null;
    }

    private void actualizarVista() {
        if (view != null) {
            view.mostrarGrafo(graph);
        }
    }

    private void avisarError(String mensaje) {
        if (view != null) {
            view.mostrarError(mensaje);
        } else {
            System.out.println("Error: " + mensaje);
        }
    }
}