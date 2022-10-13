import BancaElectronica.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static BancaElectronica.Utilities.convertStringToDate;
import static BancaElectronica.Utilities.readFileCuentas;


public class LoadCuentasHilos {


    public static void main(String[] args) {
        ServicioClientes sc = new Banco();
        /* Dentro de la clase principal crear 3 clientes con los números 1, 2, 3. */
        Cliente cliente_1 = new Cliente(1, "antonio");
        Cliente cliente_2 = new Cliente(2, "gustavo");
        Cliente cliente_3 = new Cliente(3, "jesus");

        sc.agregarCliente(cliente_1);
        sc.agregarCliente(cliente_2);
        sc.agregarCliente(cliente_3);

        /*  create a timer to measure the time of the execution */
        long startTime = System.currentTimeMillis();


        /* Leer el archivo de cuentas y agregar las cuentas a los clientes. */
        String path = "C:\\Users\\ROMANS\\IdeaProjects\\SistemaBancario\\src\\cuentas.txt";
        Stream<String> lineas = readFileCuentas(path);

        /* check if the file is empty */
        if (lineas == null) {
            System.out.println("El archivo esta vacio");
            return;
        }

        /* create a thread for each line */
        Callable<TreeSet<Cuenta>> task = () -> {
            TreeSet<Cuenta> cuentas = new TreeSet<Cuenta>();
            lineas.forEach(linea -> {
                String[] datos = linea.replace("]", "").split(",");
                String tipoCuenta = datos[0].split("\\[")[0].trim();

                int idCuenta = Integer.parseInt(datos[0].split("\\[")[1].trim());
                double saldo = Double.parseDouble(datos[2].trim());
                double tasaInteres = Double.parseDouble(datos[3].trim());
                int idCliente = Integer.parseInt(datos[4].trim());
                LocalDate fechaApertura = convertStringToDate(datos[1].trim());
                Cuenta cuenta = null;
                switch (tipoCuenta) {
                    case "CA":
                        cuenta = new CuentaDeAhorro(idCuenta, saldo, tasaInteres, fechaApertura);
                        break;
                    case "CC":
                        double costoMantenimiento = Double.parseDouble(datos[3]);
                        cuenta = new CuentaDeCheque(idCuenta, saldo, costoMantenimiento, fechaApertura);
                        break;
                }
                cuentas.add(cuenta);
            });
            return cuentas;
        };

        ExecutorService pool= Executors.newFixedThreadPool(2);
        Future<TreeSet<Cuenta>> future1=pool.submit(task);

        pool.shutdown();

        /* print time of execution */
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Tiempo de ejecucion: " + totalTime + " ms");


        try{
            TreeSet<Cuenta> cuentas=future1.get();
            cuentas.forEach(cuenta -> {
                //sc.agregarCuenta(cuenta);
                System.out.println(cuenta);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
