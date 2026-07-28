package persistence;

import java.io.IOException;

import models.MapPoint;
import structures.grafos.Graph;

public interface GraphRepository {

    void save(Graph<MapPoint> graph, String filePath) throws IOException;

    Graph<MapPoint> load(String filePath) throws IOException;

}
