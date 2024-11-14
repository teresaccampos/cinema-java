import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Filme {
  private int id;
  private int classificacao;
  private int ano;
  private int genero;
  private String titulo;
  private String sinopse;
  private String status;

  //Método construtor

  public Filme(int id, int classificacao, int ano, int genero, String titulo, String sinopse, String status) {
    this.id = id;
    this.classificacao = classificacao;
    this.ano = ano;
    this.genero = genero;
    this.titulo = titulo;
    this.sinopse = sinopse;
    this.status = "A";
  }

  //Getters & Setters

  public Filme(int idFilme, int idGenero, String titulo) {
    this.id = idFilme;
    this.titulo = titulo;
    this.genero = idGenero;
  }
  
  public int getId() {
    return id;
  }

  public int getClassificacao() {
    return classificacao;
  }

  public int getAno() {
    return ano;
  }

  public Genero getGenero(ArrayList<Genero> generos) {
    Genero gen = null;
    for (Genero g : generos) {
      if (g.getId() == genero) {
        gen = g;
        break;
      }
    }
    return gen;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getSinopse() {
    return sinopse;
  }

  public String getStatus() {
    return status;
  }

  public void setClassificacao(int classificacao) {
    this.classificacao = classificacao;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setSinopse(String sinopse) {
    this.sinopse = sinopse;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public static boolean inserirFilme(Filme filme) throws IOException {
    boolean retorno = true;
    BufferedWriter bw = new BufferedWriter(new FileWriter("filme.txt", true));
     
    bw.write(filme.getId() + ";" + filme.genero + ";" + filme.getTitulo());
    bw.newLine();
    bw.close();
      
      return retorno;
    } 

  public static ArrayList<Filme> listarFilmes(ArrayList<Genero> generos) throws IOException {
    ArrayList<Filme> filmes = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader("filme.txt"));
    
    String linha;

    while((linha = br.readLine()) != null) {
      String[] dados = linha.split(";");
      int id = Integer.parseInt(dados[0]);
      int idGenero = Integer.parseInt(dados[1]);
      String titulo = dados[2];

      Filme filme = new Filme(id, idGenero, titulo );
      filmes.add(filme);
    }
    
    br.close();
    return filmes;
  }

  public static Filme consultarFilme(String nomeFilme) {
    Filme filme = null;
    try (BufferedReader br = new BufferedReader(new FileReader("filme.txt"))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        String[] dados = linha.split(";");
        if (dados[2].equals(nomeFilme)) {
         
          int id = Integer.parseInt(dados[0]);
          int idGenero = Integer.parseInt(dados[1]);
        
          filme = new Filme(id, idGenero, dados[2]);
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return filme;
  }

  public static boolean editarFilme(int idFilme, String novoTitulo, ArrayList<Genero> generos) throws IOException {
    boolean retorno = false;

    ArrayList<Filme> filmes = listarFilmes(generos);
    
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("filme.txt"))) {
      for (Filme f : filmes) {
        if (f.getId() == idFilme) {
          retorno = true;
          Filme filme = new Filme(f.getId(), f.genero, novoTitulo);
          bw.write(filme.getId() + ";" + filme.genero + ";" + filme.getTitulo());
        } else {
          bw.write(f.getId() + ";" + f.genero + ";" + f.getTitulo());
        }
        bw.newLine();
      }
    } catch (IOException e) {
      retorno = false;
      e.printStackTrace();
    }

    if (!retorno) {
      System.out.println("Gênero não encontrado.");
    }

    return retorno;
  }
}
