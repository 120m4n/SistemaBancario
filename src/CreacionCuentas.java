import BancaElectronica.Utilities;

import java.util.ArrayList;
import java.util.List;

public class CreacionCuentas {

public static void main(String[] args) {
        String path = "C:\\Users\\ROMANS\\IdeaProjects\\SistemaBancario\\src\\";
        String fileName = "cuentas_nuevas.txt";
        String filePath = path + fileName;

        /* create a new List of String to append a file */
        List<String> lines = new ArrayList<>();


        for (int i = 0; i < 100; i++) {
            //System.out.println(Utilities.createRandomStringCA());
            lines.add(Utilities.createRandomStringCA());
            lines.add(Utilities.createRandomStringCC());
        }

        /* write the list of String to a file */
        Utilities.appendLinesToFile(filePath,lines );

        }

}
