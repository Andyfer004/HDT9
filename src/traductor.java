import java.util.HashMap;



public class traductor {
    /***
     * Traductor para el texto que se reciba del archivo
     * @param stock almacen de palabras
     * @param text traduccion a realizar
     * @return traduccion
     */
    public String traducir(String text, HashMap<String, String> stock) {
        String[] palabras = text.split(" ");
        StringBuilder traduccion = new StringBuilder();

        for (String palabra : palabras) {
            String traduccionPalabra = stock.getOrDefault(palabra, "*" + palabra + "*");
            traduccion.append(traduccionPalabra).append(" ");
        }

        return traduccion.toString().trim();
    }

    public String calculate(String s, Map map) {
    }
}

