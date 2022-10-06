package BancaElectronica;

import java.util.ArrayList;
import java.util.TreeSet;

public interface ServicioClientes {
    public boolean agregarCliente(Cliente cliente);
    public boolean eliminarCliente(Cliente cliente);
    public Cliente consultarCliente(int id);
    public TreeSet<Cliente> obtenerClientes();
    public Cliente buscarClientePorRFC(String rfc);
}
