package structures.grafos;

public interface PathFinder<T> {

    PathResult<T> find(
            Graph<T> graph,
            T start,
            T end

    );

}