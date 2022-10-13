import BancaElectronica.Agente;
import BancaElectronica.Banco;
import BancaElectronica.ServicioClientes;
import BancaElectronica.Utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static BancaElectronica.Utilities.readFileCuentas;

public class TestAgenteCuentas {
    public static void main(String[] args) {
        ServicioClientes sc = new Banco();
        //--------------------------------------------------------------------------------
        System.out.println("-------clientes-------");

        String path = "/Users/120m4n/IdeaProjects/Sesion_3/SistemaBancario/src/cuentas_nuevas.txt";
        Stream<String> lineas = readFileCuentas(path);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Double> result = executorService.submit(new Agente((Banco) sc, lineas));
        try {
            System.out.println("El resultado es: " + result.get());
            /* list all clients from bank and yours accounts*/
            /* Imprimir el total de cuentas por cada cliente. */
            sc.obtenerClientes().forEach(cliente -> {
                System.out.println("Cliente: " + cliente.getNombre() + " tiene " + cliente.getCuentas().size() + " cuentas");
            });

            Integer totalCuentas = sc.obtenerClientes().stream().mapToInt(cliente -> cliente.getCuentas().size()).sum();
            System.out.println("Total de cuentas: " + totalCuentas);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }

}
