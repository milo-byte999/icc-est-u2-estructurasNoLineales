package models;

public class Persona implements Comparable<Persona>{

    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
    }

    @Override
    public int compareTo(Persona otra) {
        int compEdad = Integer.compare(this.edad, otra.getEdad());
        if(compEdad != 0) return compEdad;
        int compNombre = this.nombre.compareTo(otra.getNombre());
        return compNombre;
    }
}
