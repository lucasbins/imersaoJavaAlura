import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-04-01&end_date=2023-04-03";
        // busca dados com o clienteHttp
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // extrair so os dados que interessam ( titulo, poster, classificação)
        // var extratorDoImDb = new ExtratorDeConteudoDoImDb();
        var extratorNasa = new ExtratorDeConteudoDaNasa();

        // List<Conteudo> conteudos = extratorDoImDb.extraConteudos(json);
        List<Conteudo> conteudos = extratorNasa.extraConteudos(json);

        // exibir os dados
        var gerador = new geradorDeFIgurinhas();

        for (int i = 0; i < conteudos.size(); i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";
            
            gerador.cria(inputStream, nomeArquivo);

            System.out.println("Titulo:" + conteudo.getTitulo());
            System.out.println();
        }

    }
}
