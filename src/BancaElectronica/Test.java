package BancaElectronica;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        ServicioClientes sc = new Banco();
        //--------------------------------------------------------------------------------
        System.out.println("-------clientes-------");
        List<Cliente> clientes = Utilities.readClientsFromCSV("C:\\Users\\ROMANS\\IdeaProjects\\SistemaBancario\\src\\Clientes.csv");
        // let's print all the clients read from CSV file
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
        System.out.println("-------cuentas-------");
        List<Cuenta> cuentas = Utilities.readAccountsFromCSV("C:\\Users\\ROMANS\\IdeaProjects\\SistemaBancario\\src\\Cuentas.csv");
        // let's print all the cuentas read from CSV file
        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta);
        }

        // add cuentas item 0 and 1 to cliente item 0
        clientes.get(0).agregarCuenta(cuentas.get(0));
        clientes.get(0).agregarCuenta(cuentas.get(1));

        // add cuentas item 2 and 3 to cliente item 1
        clientes.get(1).agregarCuenta(cuentas.get(2));
        clientes.get(1).agregarCuenta(cuentas.get(3));

        // add cuentas item 4 and 5 to last cliente
        clientes.get(clientes.size() - 1).agregarCuenta(cuentas.get(4));
        clientes.get(clientes.size() - 1).agregarCuenta(cuentas.get(5));


        // let's add all the clients to the bank
        for (Cliente cliente : clientes) {
            sc.agregarCliente(cliente);
        }

        /* get clientes using stream and count cuentas*/
        System.out.println("-------clientes con cuentas-------");
        sc.obtenerClientes().stream().forEach(cliente -> System.out.println(cliente.getNombre() + " tiene " + cliente.obtenerCuentas().size() + " cuentas"));

        System.out.println("------   consulta los clientes cuyo nombre inicie con a  o con j y retorne unicamente los telefonos y ordenados z to a ---------");
        /* servicio clientes */
        /* consulta los clientes cuyo nombre inicie con a  o con j y retorne unicamente los telefonos y ordenados z to a*/
        sc.obtenerClientes().stream()
                .filter(cliente -> cliente.getNombre().startsWith("A") || cliente.getNombre().startsWith("J"))
                .map(cliente -> cliente.getTelefono())
                .sorted((t1, t2) -> t2.compareTo(t1))
                .forEach(System.out::println);
        // get clients with accounts and name end with E or e
        System.out.println("-------clientes con cuentas y nombre que termina con e o E-------");
        sc.obtenerClientes().stream()
                .filter(cliente -> cliente.obtenerCuentas().size() > 0)
                .filter(cliente -> cliente.getNombre().endsWith("e") || cliente.getNombre().endsWith("E"))
                .forEach(System.out::println);

        // get clients that no have accounts and order by name in reverse order
        System.out.println("-------clientes sin cuentas y ordenados por nombre en orden inverso-------");
        sc.obtenerClientes().stream()
                .filter(cliente -> cliente.obtenerCuentas().size() == 0)
                .sorted((c1, c2) -> c2.getNombre().compareTo(c1.getNombre()))
                .forEach(System.out::println);


    }
}

