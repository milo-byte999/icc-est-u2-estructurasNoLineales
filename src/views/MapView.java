package views;

import models.MapPoint;
import models.VisualizationMode;
import structures.grafos.Graph;
import structures.grafos.PathResult;

public interface MapView {

    // Se llama cuando el grafo cambia (nodo agregado, arista agregada, carga de archivo, etc.)
    void mostrarGrafo(Graph<MapPoint> graph);

    // Se llama cuando termina una búsqueda, con el resultado y el modo elegido
    void mostrarResultado(PathResult<MapPoint> resultado, VisualizationMode modo);

    // Se llama para limpiar cualquier recorrido dibujado
    void limpiarRecorrido();

    // Se llama cuando ocurre un error que el usuario debe ver (ej: nodo inexistente)
    void mostrarError(String mensaje);

}
