package eva;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PersonaController {

    public Set<Persona> filtrarYOrdenar(
            List<Persona> personas,
            int edadUmbra) {

        // personas mayores a 18
        // Map <String, set<String>> map = new TreeMap<>();
        // mapa.put(JOVEN, new LinkedHashSet())
        Set<Persona> personasFiltradas = new TreeSet<>((p1, p2) -> {

            if (p1.getEdad() == p2.getEdad()
                    && p1.getNombre().equalsIgnoreCase(p2.getNombre())) {
                return 0;
            }
            int compE = Integer.compare(p2.getEdad(), p1.getEdad());
            if (compE != 0) {
                return compE;
            }
            int compN = p1.getNombre().compareToIgnoreCase(p2.getNombre());
            return compN;
        });

        for (Persona p : personas) {
            if (p.getEdad() >= edadUmbra) {
                personasFiltradas.add(p);
            }
        }
        return personasFiltradas;
    }

    public Map<String, Set<String>> agruparPorEdad(List<Persona> personas) {
        String nombre = "Juan Perez";
        String[] palabras = nombre.split(" ");
        String primerNombre = palabras[0];
        // String pNombre = nombre.split(" ")[0];
        return null;
    }
}
