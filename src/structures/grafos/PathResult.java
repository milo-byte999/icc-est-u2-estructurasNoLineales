package structures.grafos;

import java.util.List;

public class PathResult<T> {
    private final List<T> visitados;
    private final List<T> path;

    public PathResult(List<T> visitados, List<T> path) {
        this.visitados = visitados;
        this.path = path;
    }

    public List<T> getVisitados() {
        return visitados;
    }

    public List<T> getPath() {
        return path;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("visitados= ").append(visitados).append("\n");

        if (path != null && !path.isEmpty()) {
            sb.append("path= ").append(path);
        } else {
            sb.append("No se encontró un camino entre los nodos");
        }

        return sb.toString();
    }

}