package eva;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PersonaController {

    public Set<Persona1> filtrarYOrdenar(
            List<Persona1> personas,
            int edadUmbra) {

        System.out.println("----- Ejercicio 1 -----");
        Set<Persona1> personasFiltradas = new TreeSet<>((p1, p2) -> {

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

        for (Persona1 p : personas) {
            if (p.getEdad() >= edadUmbra) {
                personasFiltradas.add(p);
            }
        }

        for (Persona1 p : personasFiltradas) {
            System.out.println(p);
        }

        return personasFiltradas;
    }

    public Map<String, Set<String>> agruparPorEdad(List<Persona1> personas) {
        System.out.println("----- Ejercicio 2 -----");
        Map<String, Set<String>> grupos = new TreeMap<>();
        grupos.put("ADULTO", new LinkedHashSet<>());
        grupos.put("JOVEN", new LinkedHashSet<>());
        grupos.put("MAYOR", new LinkedHashSet<>());

        for (Persona1 p : personas) {
            String categoria;
            if (p.getEdad() < 30) {
                categoria = "JOVEN";
            } else if (p.getEdad() < 60) {
                categoria = "ADULTO";
            } else {
                categoria = "MAYOR";
            }

            String primerNombre = p.getNombre().split(" ")[0];
            Set<String> set = grupos.get(categoria);
            boolean existe = false;
            for (String n : set) {
                if (n.equalsIgnoreCase(primerNombre)) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                set.add(primerNombre);
            }
        }
        System.out.println(grupos);
        return grupos;
    }
}
