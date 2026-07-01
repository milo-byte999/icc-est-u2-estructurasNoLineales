package collections.sets;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import models.Contacto;

public class Sets {

    public Set<String> construirHashSet() {
        Set <String> hashSet = new HashSet<>();
        hashSet.add("A");
        hashSet.add("B");
        hashSet.add("C");
        hashSet.add("A");
        hashSet.add("D");
        hashSet.add("E");
        hashSet.add("F");
        hashSet.add("1Ggggggeegeg");
        hashSet.add("2G2gggggeegeg");
        hashSet.add("3Gggggeegeg");
        hashSet.add("4Ggggggeegeg");
        hashSet.add("5Ggggggeegeg");
        hashSet.add("5Ggggggeegeg");
        hashSet.add("6Ggggggeegeg");
        hashSet.add("G7gggggeegeg");

        return hashSet;
    }

    public Set<String> LinkedHashSet() {
        Set <String> lSet = new LinkedHashSet<>();
        lSet.add("A");
        lSet.add("B");
        lSet.add("C");
        lSet.add("A");
        lSet.add("D");
        lSet.add("E");
        lSet.add("F");
        lSet.add("1Ggggggeegeg");
        lSet.add("2G2gggggeegeg");
        lSet.add("3Gggggeegeg");
        lSet.add("4Ggggggeegeg");
        lSet.add("5Ggggggeegeg");
        lSet.add("5Ggggggeegeg");
        lSet.add("6Ggggggeegeg");
        lSet.add("G7gggggeegeg");

        return lSet;

        
    }

    public Set<String> TreeSet() {
        
        Set <String> tSet = new TreeSet<>();
        tSet.add("A");
        tSet.add("B");
        tSet.add("C");
        tSet.add("A");
        tSet.add("D");
        tSet.add("E");
        tSet.add("F");
        tSet.add("1Ggggggeegeg");
        tSet.add("2G2gggggeegeg");
        tSet.add("3Gggggeegeg");
        tSet.add("4Ggggggeegeg");
        tSet.add("5Ggggggeegeg");
        tSet.add("5Ggggggeegeg");
        tSet.add("6Ggggggeegeg");
        tSet.add("G7gggggeegeg");

        return tSet;
    }
    
    public Set<Contacto> construirHContacto(){
        Set<Contacto> hCSet = new HashSet<>();

    
        Contacto c1 = new Contacto("Juan", "Perez", "123456789");
        Contacto c2 = new Contacto("Ana", "Gomez", "987654321");
        Contacto c3 = new Contacto("Pedro", "Lopez", "456789123");
        Contacto c4 = new Contacto("Maria", "Rodriguez", "789123456");
        Contacto c5 = new Contacto("Juan", "Perez", "123456789"); // Duplicado, no se agregará
        Contacto c6 = new Contacto("Juan", "Lopez", "123456789");

        System.out.println(c1.hashCode());
        System.out.println(c5.hashCode());

        hCSet.add(c1);
        hCSet.add(c2);
        hCSet.add(c3);
        hCSet.add(c4);
        hCSet.add(c5);
        hCSet.add(c6);

        return hCSet;
        
    }

    public Set<Contacto> construirTreeSetContacto() {

        Set<Contacto> thCSet = new TreeSet<>(
            
            (c1, c2) -> {
                int compN = c1.getNombre().compareTo(c2.getNombre());
                if (compN != 0)
                    return compN;
                
                int compA = c1.getApellido().compareTo(c2.getApellido());
                return compA;


                //return c1.getNombre().compareTo(c2.getNombre());
            }
        
        );

        thCSet.add(new Contacto("Juan", "Perez", "123456789"));
        thCSet.add(new Contacto("Ana", "Gomez", "987654321"));
        thCSet.add(new Contacto("Pedro", "Lopez", "456789123"));
        thCSet.add(new Contacto("Maria", "Rodriguez", "789123456"));
        thCSet.add(new Contacto("Juan", "Perez", "123456789")); // Duplicado, no se agregará
        thCSet.add(new Contacto("Juan", "Lopez", "123456789"));
        return thCSet;
    }
}