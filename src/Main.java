import java.util.Scanner;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Object;
/***
 * UNIVERSIDAD DEL VALLE DE GUATEMALA
 * ALGORITMOS Y ESTRUCTURA DE DATOS
 * @author GABRIEL ALBERTO PAZ GONZALEZ 221087
 * @version 1.0
 * @date 21/04/2023
 * @source Clase Main
 */

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        var factory = new Factory<Association2<String, String>, Map<Association2<String, String>>>(); // <K,V>

        traductor translator = new traductor();
        Vector<String> mixed = new Vector<>();

        System.out.println("---------------------------------------- ");
        System.out.println("------- DEL VALLE TRANSLATOR ----------- ");
        System.out.println("---------------------------------------- ");
        System.out.println("Seleccione la implementacion que desea utilizar para el mapa: ");
        System.out.println("- 1 HashMap. -");
        System.out.println("- 2 Red Black Tree RBT. -");
        System.out.println("- 3 Splay Tree. -");
        System.out.println("---------------------------------------- ");

        int option = 0;
        while (true) {
            try {
                option = scanner.nextInt();

                if (option >= 1 && option <= 3) {
                    break;
                } else {
                    System.out.println("Por favor ingrese una opción que se encuentre en el menú.");
                }
            } catch (Exception e) {
                System.out.println("Por favor ingrese unicamente números del 1-3.");
                scanner.next();
            }
        }

        Map<Association2<String, String>> map = factory.getMap(option);

        try {
            File file = new File("Spanish.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split("\\s+");
                String key = "";
                String value = "";
                if (parts.length <= 2) {
                    key = parts[0];
                    value = parts[1];
                } else {
                    key = parts[0];
                    value = parts[2];
                }
                map.put(new Association2<>(key, value));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileReader reader = new FileReader("texto.txt");
            BufferedReader buffer = new BufferedReader(reader);

            String line = "";

            while ((line = buffer.readLine()) != null) {
                mixed.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error, el archivo no se pudo encontrar.");
        }

        int i = 0;
        while (i < mixed.size()) {
            try {
                System.out.println(" - ORIGINAL: " + mixed.get(i) + " ");
                System.out.println(" - TRADUCIDO: " + translator.calculate(mixed.get(i), map) + " ");
                System.out.println();

            } catch (Exception e) {
                System.out.println("Error, no se pudo traducir la oración.");
                System.out.println();

            }
            i++;
        }
    }
}



