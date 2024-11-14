import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ArrayList<Genero> generos = new ArrayList<>();
        ArrayList<Filme> filmes = new ArrayList<>();

        try {
            generos = Genero.listarGeneros();
            filmes = Filme.listarFilmes(generos);
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        while(true) {
            System.out.println("Menu Principal:");
            System.out.println("1 - Gênero: Cadastrar, Editar, Listar, Consultar");
            System.out.println("2 - Filme: Cadastrar, Editar, Listar, Consultar");
            System.out.println("0 - Sair");
            int opcao = sc.nextInt();
            sc.nextLine(); 

            if(opcao == 0) break;

            switch (opcao) {
                case 1:
                    System.out.println("1 - Cadastrar Gênero");
                    System.out.println("2 - Editar Gênero");
                    System.out.println("3 - Listar Gêneros");
                    System.out.println("4 - Consultar Gênero");
                    int opcaoGenero = sc.nextInt();
                    sc.nextLine();

                    
                    switch (opcaoGenero) {
                        case 1:
                            System.out.println("Digite o id do gênero:");
                            int idGenero = sc.nextInt();
                            boolean generoEncontrado = false;
                            sc.nextLine();
                            
                            for (Genero g : generos) {
                                if (g.getId() == idGenero) {
                                    System.out.println("Gênero já cadastrado.");
                                    generoEncontrado = true;
                                    break;
                                }
                            }

                            if (generoEncontrado) {
                                break;                            
                            }
                            
                            System.out.println("Digite a descrição do gênero:");
                            
                            String desc = sc.nextLine();
                            Genero novoGenero = new Genero(idGenero, desc, "A");
                            Genero.inserirGenero(novoGenero);
                            generos.add(novoGenero);

                            break;

                        case 2:
                            System.out.println("Digite o id do gênero que deseja editar:");
                            int idEditarGenero = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Digite a nova descrição do gênero:");
                            String novaDesc = sc.nextLine();
                            Genero.editarGenero(new Genero(idEditarGenero, novaDesc, "A"));
                            
                            break;
                        case 3:
                            Filme.listarFilmes(generos);
                            break;
                        case 4:
                        System.out.println("Digite o nome do genero que deseja consultar:");
                        String nomeGenero = sc.nextLine();

                        Genero genero = Genero.consultarGenero(nomeGenero);

                        if (genero != null) {
                            System.out.println("ID: " + genero.getId() + " - Título: " + genero.getDesc());
                        } else {
                            System.out.println("Genero não encontrado.");
                        }
                        break;         
                    }
                    break;

                case 2:
                    System.out.println("1 - Cadastrar Filme");
                    System.out.println("2 - Editar Filme");
                    System.out.println("3 - Listar Filmes");
                    System.out.println("4 - Consultar Filme");
                    int opcaoFilme = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoFilme) {
                        case 1:
                            System.out.println("Digite o id do filme:");
                            int idFilme = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o id do gênero:");
                            int idGenero = sc.nextInt();
                            sc.nextLine();
                            boolean generoEncontrado = false;

                            for (Genero g : generos) {
                                if (g.getId() == idGenero) {
                                    System.out.println("Digite o título do filme:");
                                    String titulo = sc.nextLine();
                                    Filme novoFilme = new Filme(idFilme, idGenero , titulo);
                                    Filme.inserirFilme(novoFilme);
                                    filmes.add(novoFilme);
                                    generoEncontrado = true;
                                    break;
                                }
                            }
                            
                            if (!generoEncontrado) {
                                System.out.println("Gênero não encontrado.");
                            }
                            break;

                        case 2:
                            System.out.println("Digite o id do filme que deseja editar:");
                            int idEditarFilme = sc.nextInt();
                            sc.nextLine();
                            boolean filmeEncontrado = false;

                            System.out.println("Digite o novo título do filme:");
                            String novoTitulo = sc.nextLine();
                            Filme.editarFilme(idEditarFilme, novoTitulo, generos);
                            filmeEncontrado = true;

                            if (!filmeEncontrado) {
                                System.out.println("Filme não encontrado.");
                            }

                            break;
                        case 3:
                            for (Filme f : filmes) {
                                System.out.println("ID: " + f.getId() + " - Título: " + f.getTitulo());
                            }
                            break;
                        case 4:
                            System.out.println("Digite o nome do filme que deseja consultar:");
                            String nomeFilme = sc.nextLine();

                            Filme filme = Filme.consultarFilme(nomeFilme);

                            if (filme != null) {
                                System.out.println("ID: " + filme.getId() + " - Título: " + filme.getTitulo());
                            } else {
                                System.out.println("Filme não encontrado.");
                            }
                            break;    
                    }
                    break;
            }
        }

        sc.close();
    }
}
