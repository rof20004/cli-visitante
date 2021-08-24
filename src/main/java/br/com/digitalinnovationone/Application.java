package br.com.digitalinnovationone;

import java.util.Hashtable;
import java.util.Scanner;

public class Application {

    private static boolean isRunning = true;
    private static String opcaoSelecionada = "";
    private static final String CADASTRAR = "1";
    private static final String RECUPERAR = "2";
    private static final String SAIR = "3";

    private static Hashtable<String, Visitante> repository = new Hashtable<>();

    public static void main(String[] args) {
        var leitor = new Scanner(System.in);

        while(isRunning) {
            opcoes(leitor);

            switch (opcaoSelecionada) {
                case CADASTRAR:
                    cadastrar(leitor);
                    break;
                case RECUPERAR:
                    var visitante = recuperar(leitor);
                    if (visitante != null) {
                        System.out.println(String.format("CPF: %s, Nome: %s", visitante.getCpf(), visitante.getNome()));
                    }
                    break;
                case SAIR:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void opcoes(Scanner leitor) {
        System.out.println("1 - Cadastrar visitante");
        System.out.println("2 - Procurar visitante");
        System.out.println("3 - Sair");
        opcaoSelecionada = leitor.nextLine();
    }

    private static void cadastrar(Scanner leitor) {
        System.out.println("Informe o cpf:");
        var cpf = leitor.nextLine();

        System.out.println("Informe o nome:");
        var nome = leitor.nextLine();

        try {
            var visitante = new Visitante(cpf, nome);
            repository.put(cpf, visitante);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Visitante recuperar(Scanner leitor) {
        System.out.println("Informe o cpf para pesquisar:");
        var cpf = leitor.nextLine();

        var visitante = repository.get(cpf);
        if (visitante == null) {
            System.out.println("Visitante não encontrado!");
        }

        return visitante;
    }

}
