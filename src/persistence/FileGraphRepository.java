package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.MapPoint;
import structures.grafos.Graph;
import structures.node.Node;

public class FileGraphRepository implements GraphRepository{

    @Override
    public void save(Graph<MapPoint> graph, String filePath) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        // 1. Guardar todos los nodos
        for (Node<MapPoint> node : graph.getNodes()) {
            MapPoint mp = node.getValue();
            bw.write("NODE," + mp.getId() + "," + mp.getX() + "," + mp.getY());
            bw.newLine();
        }

        // 2. Guardar las aristas, evitando repetir una bidireccional dos veces
        Set<String> yaGuardadas = new LinkedHashSet<>();

        for (Map.Entry<Node<MapPoint>, Set<Node<MapPoint>>> entry : graph.getGraph().entrySet()) {

            MapPoint origen = entry.getKey().getValue();
            Set<Node<MapPoint>> vecinos = entry.getValue();

            for (Node<MapPoint> vecinoNode : vecinos) {
                MapPoint destino = vecinoNode.getValue();

                String key = origen.getId() + "-" + destino.getId();
                String keyInversa = destino.getId() + "-" + origen.getId();

                if (yaGuardadas.contains(key)) {
                    continue; // ya se guardó esta conexión
                }

                // Averiguar si es bidireccional: reviso si destino tiene a origen como vecino
                boolean esBidireccional = false;
                Set<Node<MapPoint>> vecinosDelDestino = graph.getVecinos(destino);

                for (Node<MapPoint> posibleVuelta : vecinosDelDestino) {
                    if (posibleVuelta.getValue().equals(origen)) {
                        esBidireccional = true;
                    }
                }

                bw.write("EDGE," + origen.getId() + "," + destino.getId() + "," + esBidireccional);
                bw.newLine();

                yaGuardadas.add(key);
                if (esBidireccional) {
                    yaGuardadas.add(keyInversa);
                }
            }
        }

        bw.close();
    }

    @Override
    public Graph<MapPoint> load(String filePath) throws IOException {

        Graph<MapPoint> graph = new Graph<>();
        Set<String> idsCargados = new LinkedHashSet<>();
        List<String[]> edgesPendientes = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine();

        while (line != null) {

            line = line.trim();

            if (line.isEmpty()) {
                line = br.readLine();
                continue;
            }

            String[] partes = line.split(",");

            if (partes[0].equals("NODE")) {
                procesarLineaNode(partes, graph, idsCargados, line);

            } else if (partes[0].equals("EDGE")) {
                if (partes.length == 4) {
                    edgesPendientes.add(partes);
                } else {
                    System.out.println("Línea de arista incompleta, se ignora: " + line);
                }

            } else {
                System.out.println("Línea desconocida, se ignora: " + line);
            }

            line = br.readLine();
        }

        br.close();

        // Procesar las aristas al final, cuando ya existen todos los nodos
        for (String[] partes : edgesPendientes) {
            procesarLineaEdge(partes, graph, idsCargados);
        }

        return graph;
    }

    // ---- Métodos de apoyo ----

    private void procesarLineaNode(String[] partes, Graph<MapPoint> graph,
                                    Set<String> idsCargados, String lineaOriginal) {

        if (partes.length != 4) {
            System.out.println("Línea de nodo incompleta, se ignora: " + lineaOriginal);
            return;
        }

        String id = partes[1].trim();

        if (idsCargados.contains(id)) {
            System.out.println("Nodo con id repetido, se ignora: " + id);
            return;
        }

        int x;
        int y;

        try {
            x = Integer.parseInt(partes[2].trim());
            y = Integer.parseInt(partes[3].trim());
        } catch (NumberFormatException e) {
            System.out.println("Coordenadas inválidas, se ignora: " + lineaOriginal);
            return;
        }

        graph.add(new MapPoint(id, x, y));
        idsCargados.add(id);
    }

    private void procesarLineaEdge(String[] partes, Graph<MapPoint> graph, Set<String> idsCargados) {

        String fromId = partes[1].trim();
        String toId = partes[2].trim();
        boolean bidireccional = Boolean.parseBoolean(partes[3].trim());

        if (!idsCargados.contains(fromId) || !idsCargados.contains(toId)) {
            System.out.println("Arista con referencia a nodo inexistente, se ignora: "
                    + fromId + " -> " + toId);
            return;
        }

        MapPoint origen = buscarPorId(graph, fromId);
        MapPoint destino = buscarPorId(graph, toId);

        if (bidireccional) {
            graph.addEdge(origen, destino);
        } else {
            graph.addEdgeUni(origen, destino);
        }
    }

    private MapPoint buscarPorId(Graph<MapPoint> graph, String id) {

        for (Node<MapPoint> node : graph.getNodes()) {
            if (node.getValue().getId().equals(id)) {
                return node.getValue();
            }
        }

        return null;
    }

}
