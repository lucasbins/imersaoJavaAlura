import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair so os dados que interessam ( titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir os dados
        
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Titulo: " + filme.get("title"));

            String urlImage = filme.get("image");
            InputStream inputStream = new URL(urlImage).openStream();

            String nomeArquivo = filme.get("title") + ".png";
            var geradora = new geradorDeFIgurinhas();

            geradora.cria(inputStream, nomeArquivo);

            double rating = Double.parseDouble(filme.get("imDbRating"));
            
            int stars = (int) rating;
            System.out.print("Rating: ");
            for (int i = 0; i < stars; i++) {
               System.out.print("🌟"); 
            }

            System.out.print("(" + filme.get("imDbRating") + ")");

            System.out.println();
        }

        // acessar outro servico de api
        // estilizar o terminal
        // esconder a Apikey
    }
}
