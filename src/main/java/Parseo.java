import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parseo {


    public static void main(String[] args) {
        String json = obtenerJson();
        //No pone en la tarea si hay que dejar el nombre de la página.
        JSONObject obj = new JSONObject(json);
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        JSONArray comentarios;
        System.out.println(pageName);

        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);

            comentarios = arr.getJSONObject(i).getJSONArray("comments");
            for (int j = 0; j < comentarios.length(); j++) {
                String comment = comentarios.getString(j);
                System.out.println(comment);
            }
            System.out.println();
        }
    }

    private static String obtenerJson(){
        File file = new File("src\\main\\resources\\datos.json");
        System.out.println(file.getAbsolutePath());
        String content = "";


            try {
                content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        return content;
    }
}
