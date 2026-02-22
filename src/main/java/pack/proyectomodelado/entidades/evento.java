package pack.proyectomodelado.entidades;

public class evento {

    private String ciudad;
    private String nombre;
    private String categoria;
    private float precioEntrada;

    public evento(String ciudad, String nombre, String categoria, float precioEntrada) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioEntrada = precioEntrada;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(float precioEntrada) {
        this.precioEntrada = precioEntrada;
    }
}
