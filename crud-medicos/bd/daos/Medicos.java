package bd.daos;

import java.sql.*;
import java.util.ArrayList;

import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Medicos
{
    public static boolean cadastrado (String crm) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "select * " + "from Medico " + "where crm = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, crm);

            MeuResultSet resultado = (MeuResultSet)BDPostgreSQL.COMANDO.executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar médico");
        }

        return retorno;
    }

    public static void incluir (Medico medico) throws Exception
    {
        if (medico==null)
            throw new Exception ("Medico nao fornecido");

        try
        {
            String sql;

            sql = "insert into Medico " +
                  "(numero, nome, areaAtuacao, complemento, cep, crm, telefone, rg) " +
                  "values " +
                  "(?,?,?,?,?,?,?,?)";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setInt (1, medico.getNumero ());
            BDPostgreSQL.COMANDO.setString (2, medico.getNome ());
            BDPostgreSQL.COMANDO.setString (3, medico.getAreaAtuacao ());
            BDPostgreSQL.COMANDO.setString (4, medico.getComplemento ());
            BDPostgreSQL.COMANDO.setString (5, medico.getCep ());
            BDPostgreSQL.COMANDO.setString  (6, medico.getCrm ());
            BDPostgreSQL.COMANDO.setString (7, medico.getTelefone ());
            BDPostgreSQL.COMANDO.setString (8, medico.getRg ());

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao incluir medico");
        }
    }

    public static void excluir (String crm) throws Exception
    {
        if (!cadastrado (crm))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "delete from Medico " +
                  "where crm=?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, crm);

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao excluir medico");
        }
    }

    public static void alterar (Medico medico) throws Exception
    {
        if (medico==null)
            throw new Exception ("Medico nao fornecido");

        if (!cadastrado (medico.getCrm()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "update Medico " +
                  "set telefone=?, " +
                  "cep=?, " +
                  "numero=?, " +
                  "nome=?, " +
                  "rg=?, " +
                  "areaAtuacao=?, " +
                  "complemento=? " +
                  "where crm = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, medico.getTelefone ());
            BDPostgreSQL.COMANDO.setString (2, medico.getCep ());
            BDPostgreSQL.COMANDO.setInt (3, medico.getNumero ());
            BDPostgreSQL.COMANDO.setString (4, medico.getNome ());
            BDPostgreSQL.COMANDO.setString (5, medico.getRg ());
            BDPostgreSQL.COMANDO.setString (6, medico.getAreaAtuacao ());
            BDPostgreSQL.COMANDO.setString (7, medico.getComplemento ());
            BDPostgreSQL.COMANDO.setString (8, medico.getCrm ());

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados do medico");
        }
    }

    public static Medico getMedico (String crm) throws Exception
    {
        Medico medico = null;

        try
        {
            String sql;

            sql = "select * " +
                  "from Medico " +
                  "where crm = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, crm);

            MeuResultSet resultado = (MeuResultSet)BDPostgreSQL.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            medico = new Medico (resultado.getString ("crm"),
                    resultado.getString("nome"),
                    resultado.getString("areaAtuacao"),
                    resultado.getString("telefone"),
                    resultado.getString("rg"),
                    resultado.getString("cep"),
                    resultado.getInt("numero"),
                    resultado.getString("complemento"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar medico");
        }

        return medico;
    }

    public static ArrayList<Medico> getMedicos () throws Exception
    {
        ArrayList<Medico> medicos = new ArrayList<>();

        try
        {
            String sql;

            sql = "select * " +
                  "from Medico";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)BDPostgreSQL.COMANDO.executeQuery ();

            while (resultado.next())
            {
                Medico medicoAtual = new Medico (resultado.getString("crm"),
                                                 resultado.getString("nome"),
                                                 resultado.getString("areaAtuacao"),
                                                 resultado.getString("telefone"),
                                                 resultado.getString("rg"),
                                                 resultado.getString("cep"),
                                                 resultado.getInt("numero"),
                                                 resultado.getString("complemento"));

                medicos.add(medicoAtual);
            }
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar medicos");
        }

        return medicos;
    }


}
