import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

//gera uma figurinha;
public class geradorDeFIgurinhas {
  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

    //leitura da imagem
    //  InputStream inputStream = new FileInputStream(new File("entrada/pulpFiction.jpg"));
    //  InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_9.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    //cria uma nova imagem na memoria com transparencia e com tamanho novo
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;
    BufferedImage novaImage = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // copiar a imagem original para uma nova imagem 
    Graphics2D graphics = (Graphics2D) novaImage.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // Configurar a fonte 
    Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 150);
    graphics.setColor(Color.RED);
    graphics.setFont(fonte);

    //escrever na nova imagem
    graphics.drawString("Top", largura / 2 - 200 , novaAltura - 50);
    String novoNome = nomeArquivo.replace(':', '-');
    //transformar a nova imagem em um arquivo
    ImageIO.write(novaImage, "png", new File("saida/" + novoNome));

  }
}
