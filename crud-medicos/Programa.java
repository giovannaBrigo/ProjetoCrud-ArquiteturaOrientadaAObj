import bd.core.MeuResultSet;
import bd.daos.Medicos;
import bd.dbos.Medico;
import logradouro.Logradouro;

import java.util.ArrayList;

public class Programa
{
    public static void main (String[] args)
    {
        // MENU

        String opcoes = "123456";
        char opcao    = ' ';

        do
        {
            do
            {
                System.out.println("CRUD - MEDICOS");
                System.out.println("******** MENU ********");
                System.out.println("1 - Consultar");
                System.out.println("2 - Consultar Todos");
                System.out.println("3 - Incluir");
                System.out.println("4 - Excluir");
                System.out.println("5 - Atualizar");
                System.out.println("6 - Sair");
                System.out.println("**********************");
                System.out.println("O que deseja fazer? ");

                try
                {
                    opcao = Teclado.getUmChar();
                }
                catch (Exception erro1)
                {
                    System.err.println("Opcao invalida!");
                }

                if (!opcoes.contains(String.valueOf(opcao)))
                    System.out.println("Opcao invalida! Tente novamente: ");
            }
            while (opcoes.indexOf(opcao) == -1);

            boolean valido = true;
            switch (opcao)
            {
                // consultar
                case '1':
                    String crmProcurado;
                    do
                    {
                        System.out.println("\nDigite o CRM do medico procurado: ");
                        crmProcurado = Teclado.getUmString();

                        if (crmProcurado.length() != 9 || crmProcurado.indexOf('/') == -1 ||
                            crmProcurado.indexOf('/') != 6)
                            valido = false;
                        else
                        {
                            for (int i = 0; i < 6; i++)
                                if (Character.isLetter(crmProcurado.charAt(i)))
                                    valido = false;

                            for (int i = 7; i < 9; i++)
                                if (!Character.isLetter(crmProcurado.charAt(i)))
                                    valido = false;
                        }

                        if (valido == false)
                        {
                            System.out.println("\nCRM inválido! ");
                            System.out.println("Certifique-se de que o CRM segue o seguinte padrão: 910264/SP");
                        }

                    } while(valido == false);

                    try
                    {
                        Medico medicoProcurado = Medicos.getMedico(crmProcurado);
                        System.out.println("\nMedico encontrado!");

                        Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", medicoProcurado.getCep());
                        System.out.println("\nDADOS: ");
                        System.out.println(medicoProcurado);
                        System.out.println(logradouro + "\n");
                    }
                    catch (Exception erro)
                    {
                        System.err.println(erro.getMessage());
                    }
                    break;
                // consultar tudo
                case '2':
                    try
                    {
                        System.out.println("\nMÉDICOS: ");
                        ArrayList<Medico> medicos = Medicos.getMedicos();
                        for (Medico medico : medicos)
                        {
                            Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", medico.getCep());
                            System.out.println(medico);
                            System.out.println(logradouro + "\n");
                        }
                    }
                    catch (Exception erro)
                    {
                        System.out.println(erro.getMessage());
                    }
                    break;
                // incluir
                case '3':
                    String novoCrm, nome, area, tel, rg, cep, compl;
                    int num = 0;

                    System.out.println("\nDigite os dados do novo medico...");

                    System.out.println("CRM: ");
                    novoCrm = Teclado.getUmString();

                    System.out.println("Nome: ");
                    nome = Teclado.getUmString();

                    System.out.println("Area de atuacao: ");
                    area = Teclado.getUmString();

                    System.out.println("Telefone: ");
                    tel = Teclado.getUmString();

                    System.out.println("RG: ");
                    rg = Teclado.getUmString();

                    System.out.println("CEP: ");
                    cep = Teclado.getUmString();

                    System.out.println("Complemento: ");
                    compl = Teclado.getUmString();

                    System.out.println("Numero: ");
                    try
                    {
                        num = Teclado.getUmInt();
                    }
                    catch (Exception erro)
                    {
                        System.err.println(erro.getMessage());
                    }

                    try
                    {
                        Medico novoMedico = new Medico(novoCrm, nome, area, tel, rg, cep, num, compl);
                        Medicos.incluir(novoMedico);
                        System.out.println("\n Medico incluido! \n");
                    }
                    catch (Exception erro2)
                    {
                        System.err.println("\nDados inválidos! Certifique-se de digitar tudo corretamente!\n");
                    }
                    break;
                // excluir
                case '4':
                    String crmAExcluir;
                    do
                    {
                        System.out.println("\nDigite o CRM do medico que deseja excluir: ");
                         crmAExcluir = Teclado.getUmString();

                        if (crmAExcluir.length() != 9 || crmAExcluir.indexOf('/') == -1 || crmAExcluir.indexOf('/') != 6)
                            valido = false;
                        else
                        {
                            for (int i = 0; i < 6; i++)
                                if (Character.isLetter(crmAExcluir.charAt(i)))
                                    valido = false;

                            for (int i = 7; i < 9; i++)
                                if (!Character.isLetter(crmAExcluir.charAt(i)))
                                    valido = false;
                        }

                        if (valido == false)
                        {
                            System.out.println("\nCRM inválido! ");
                            System.out.println("Certifique-se de que o CRM segue o seguinte padrão: 910264/SP\n");
                        }

                    } while(valido == false);

                    try
                    {
                        Medicos.excluir(crmAExcluir);
                        System.out.println("\nMedico excluido!\n");
                    }
                    catch (Exception erro)
                    {
                        System.err.println(erro.getMessage());
                    }
                    break;
                // atualizar
                case '5':
                    String crmAAlterar;
                    do
                    {
                        System.out.println("Digite o CRM do medico que tera seus dados alterados: ");
                        crmAAlterar = Teclado.getUmString();

                        if (crmAAlterar.length() != 9 || crmAAlterar.indexOf('/') == -1 || crmAAlterar.indexOf('/') != 6)
                            valido = false;
                        else
                        {
                            for (int i = 0; i < 6; i++)
                                if (Character.isLetter(crmAAlterar.charAt(i)))
                                    valido = false;

                            for (int i = 7; i < 9; i++)
                                if (!Character.isLetter(crmAAlterar.charAt(i)))
                                    valido = false;
                        }

                        if (valido == false)
                        {
                            System.out.println("\nCRM inválido! ");
                            System.out.println("Certifique-se de que o CRM segue o seguinte padrão: 910264/SP");
                        }

                    } while(valido == false);

                    try
                    {
                        if (!Medicos.cadastrado(crmAAlterar))
                        {
                            System.out.println("Medico nao cadastrado!");
                            break;
                        }
                        else
                        {
                            System.out.println("Digite os dados atualizados: ");

                            try
                            {
                                System.out.println("Nome: ");
                                String nomeAlt = Teclado.getUmString();

                                System.out.println("Area de atuacao: ");
                                String areaAlt = Teclado.getUmString();

                                System.out.println("Telefone: ");
                                String telAlt = Teclado.getUmString();

                                System.out.println("RG: ");
                                String rgAlt = Teclado.getUmString();

                                System.out.println("CEP: ");
                                String cepAlt = Teclado.getUmString();

                                System.out.println("Complemento: ");
                                String complAlt = Teclado.getUmString();

                                System.out.println("Numero: ");
                                int numAlt = Teclado.getUmInt();

                                Medico medicoAlt = new Medico(crmAAlterar, nomeAlt, areaAlt, telAlt, rgAlt, cepAlt, numAlt, complAlt);

                                Medicos.alterar(medicoAlt);

                                System.out.println("\nOs dados foram atualizados!\n");
                            }
                            catch (Exception erro) // esse erro pode acontecer na leitura do número
                            {
                                System.err.println(erro.getMessage());
                            }
                        }

                    }
                    catch (Exception erro)
                    {
                        System.err.println(erro.getMessage());
                    }
                    break;
                // sair
                case '6':
                    break;
            }
        }
        while (opcao != '6');
    }
}
