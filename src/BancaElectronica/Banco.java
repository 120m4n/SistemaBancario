package BancaElectronica;

import java.util.ArrayList;
import java.util.TreeSet;

public class Banco implements ServicioClientes {
private String nombre;
    private Domicilio domicilio;
    private String rfc;
    private String telefono;
    private TreeSet<Cliente> clientes;

    public Banco(){
        clientes = new TreeSet<Cliente>();
    }

    public Banco(String nombre, Domicilio domicilio, String rfc, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.rfc = rfc;
        this.telefono = telefono;
        clientes = new TreeSet<Cliente>();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TreeSet<Cliente> getClientes() {
        return clientes;
    }


    /*
    toString
     */
    @Override
    public String toString() {
        return "Banco{" + "nombre=" + nombre + ", domicilio=" + domicilio + ", rfc=" + rfc + ", telefono=" + telefono + ", clientes=" + clientes + '}';
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        return clientes.add(cliente);
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return clientes.remove(cliente);
    }

    @Override
    public Cliente consultarCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public TreeSet<Cliente> obtenerClientes() {
        return clientes;
    }

    @Override
    public Cliente buscarClientePorRFC(String rfc) {
        for (Cliente cliente : clientes) {
            /* return null if getRfc is null*/
            if (cliente.getRfc() != null && cliente.getRfc().equals(rfc)) {
                return cliente;
            }

        }
        return null;
    }


}
