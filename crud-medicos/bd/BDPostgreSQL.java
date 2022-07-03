package bd;

import bd.core.*;
import bd.daos.*;

public class BDPostgreSQL
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            comando =
            new MeuPreparedStatement (
            "org.postgresql.Driver",
            "jdbc:postgresql://ec2-54-165-184-219.compute-1.amazonaws.com/d707n0pt0e452n",
            "eteiuvyxwtnyfy", "27ed05bc28850213dd7e6d24f135b52119a28308b7c4c7c5e42022284c44745c");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}
