import BancaElectronica.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import static BancaElectronica.Utilities.convertStringToDate;
import static BancaElectronica.Utilities.readFileCuentas;



public class LoadCuentas {


    public static void main(String[] args) {
        ServicioClientes sc = new Banco();
        /* Dentro de la clase principal crear 3 clientes con los números 1, 2, 3. */
        Cliente cliente_1 = new Cliente(1, "antonio");
        Cliente cliente_2 = new Cliente(2, "gustavo");
        Cliente cliente_3 = new Cliente(3, "jesus");

        sc.agregarCliente(cliente_1);
        sc.agregarCliente(cliente_2);
        sc.agregarCliente(cliente_3);


        /* Leer el archivo de cuentas y agregar las cuentas a los clientes. */
        String path = "C:\\Users\\ROMANS\\IdeaProjects\\SistemaBancario\\src\\cuentas.txt";
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
                    sc.consultarCliente(idCliente).agregarCuenta(cuentaAhorro);
                    break;
                case "CC":
                    double costoMantenimiento = Double.parseDouble(datos[3]);
                    CuentaDeCheque cuentaCheque = new CuentaDeCheque(idCuenta, saldo, costoMantenimiento, fechaApertura);
                    sc.consultarCliente(idCliente).agregarCuenta(cuentaCheque);
                    break;
            }
        });


        /* print the clientes and their cuentas */
        sc.obtenerClientes().forEach(c -> {
            System.out.println(c);
            c.obtenerCuentas().forEach(System.out::println);
        });
    }
}
