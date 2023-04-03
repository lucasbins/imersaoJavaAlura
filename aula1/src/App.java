import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // extrair so os dados que interessam ( titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeConteudo = parser.parse(json);

        // exibir os dados
        
        for (Map<String,String> conteudo : listaDeConteudo) {
            System.out.println("Titulo: " + conteudo.get("title"));

            String urlImage = conteudo.get("image");
            InputStream inputStream = new URL(urlImage).openStream();

            String nomeArquivo = conteudo.get("title") + ".png";
            var geradora = new geradorDeFIgurinhas();

            geradora.cria(inputStream, nomeArquivo);

            double rating = Double.parseDouble(conteudo.get("imDbRating"));
            
            int stars = (int) rating;
            System.out.print("Rating: ");
            for (int i = 0; i < stars; i++) {
               System.out.print("🌟"); 
            }

            System.out.print("(" + conteudo.get("imDbRating") + ")");

            System.out.println();
        }
    }
}
