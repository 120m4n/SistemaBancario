import BancaElectronica.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import static BancaElectronica.Utilities.convertStringToDate;
import static BancaElectronica.Utilities.readFileCuentas;



public class LoadCuentas {


    public static void main(String[] args) {
        ServicioClientes sc = new Banco();
        /* Dentro de la clase principal crear 3 clientes con los números 1, 2, 3. */
        Cliente cliente_0 = new Cliente(0, "roman");
        Cliente cliente_1 = new Cliente(1, "antonio");
        Cliente cliente_2 = new Cliente(2, "gustavo");
        Cliente cliente_3 = new Cliente(3, "jesus");

        sc.agregarCliente(cliente_0);
        sc.agregarCliente(cliente_1);
        sc.agregarCliente(cliente_2);
        sc.agregarCliente(cliente_3);


        /*  create a timer to measure the time of the execution */
        long startTime = System.currentTimeMillis();

        /* Leer el archivo de cuentas y agregar las cuentas a los clientes. */
        String path = "C:\\Users\\ROMANS\\IdeaProjects\\SistemaBancario\\src\\cuentas_nuevas.txt";
        Stream<String> lineas = readFileCuentas(path);

        /* check if the file is empty */
        if (lineas == null) {
            System.out.println("El archivo esta vacio");
            return;
        }


            lineas.forEach(linea -> {
            String[] datos = linea.replace("]", "").split(",");
            String tipoCuenta = datos[0].split("\\[")[0].trim();

            int idCuenta = Integer.parseInt(datos[0].split("\\[")[1].trim());
            double saldo = Double.parseDouble(datos[2].trim());
            double tasaInteres = Double.parseDouble(datos[3].trim());
            int idCliente = Integer.parseInt(datos[4].trim());
            LocalDate fechaApertura = convertStringToDate(datos[1].trim());
            switch (tipoCuenta) {
                case "CA":
                    CuentaDeAhorro cuentaAhorro = new CuentaDeAhorro(idCuenta, saldo, tasaInteres, fechaApertura);
                    /* if client exist add the account to the client */
                    if (sc.consultarCliente(idCliente) != null) {
                        sc.consultarCliente(idCliente).agregarCuenta(cuentaAhorro);
                    } else {
                        /* create cliente and add the account */
                        Cliente cliente = new Cliente(idCliente, "cliente" + idCliente);
                        cliente.agregarCuenta(cuentaAhorro);
                        sc.agregarCliente(cliente);
                    }
                    break;
                case "CC":
                    double costoMantenimiento = Double.parseDouble(datos[3]);
                    CuentaDeCheque cuentaCheque = new CuentaDeCheque(idCuenta, saldo, costoMantenimiento, fechaApertura);
                    if (sc.consultarCliente(idCliente) != null) {
                        sc.consultarCliente(idCliente).agregarCuenta(cuentaCheque);
                    } else {
                        /* create cliente and add the account */
                        Cliente cliente = new Cliente(idCliente, "cliente" + idCliente);
                        cliente.agregarCuenta(cuentaCheque);
                        sc.agregarCliente(cliente);
                    }
                    break;
            }
        });

        /* print time of execution */
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Tiempo de ejecucion: " + totalTime + " ms");



        /* Imprimir el total de cuentas por cada cliente. */
        sc.obtenerClientes().forEach(cliente -> {
            System.out.println("Cliente: " + cliente.getNombre() + " tiene " + cliente.getCuentas().size() + " cuentas");
        });

        Integer totalCuentas = sc.obtenerClientes().stream().mapToInt(cliente -> cliente.getCuentas().size()).sum();
        System.out.println("Total de cuentas: " + totalCuentas);
    }
}
