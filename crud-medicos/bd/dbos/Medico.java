package bd.dbos;

public class Medico implements Cloneable
{
    private int numero;
    private String cep, nome, areaAtuacao, crm, complemento, telefone, rg;

    public void setCrm (String crm) throws Exception
    {
        if (crm == null || crm.equals(""))
            throw new Exception ("CRM invalido");

        if (crm.length() != 9 || crm.indexOf('/') == -1 || crm.indexOf('/') != 6)
            throw new Exception ("CRM invalido");

        for (int i = 0; i < 6; i++)
                if (Character.isLetter(crm.charAt(i)))
                    throw new Exception ("CRM invalido");

        for (int i = 7; i < 9; i++)
            if (!Character.isLetter(crm.charAt(i)))
                throw new Exception ("CRM invalido");

        this.crm = crm;
    }

    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        if (nome.length() > 60)
            throw new Exception ("Nome invalido");

        this.nome = nome;
    }

    public void setTelefone (String telefone) throws Exception
    {
        if (telefone == null || telefone.equals(""))
            throw new Exception ("Telefone invalido");

        if (telefone.length() != 11)
            throw new Exception ("Numero de telefone invalido");

        for (int i = 0; i < telefone.length(); i++)
            if (Character.isLetter(telefone.charAt(i)))
                throw new Exception ("Numero de telefone invalido");

        this.telefone = telefone;
    }

    public void setAreaAtuacao (String areaAtuacao) throws Exception
    {
        if (areaAtuacao==null || areaAtuacao.equals(""))
            throw new Exception ("Area de atuacao invalida");

        if (areaAtuacao.length() > 30 )
            throw new Exception ("Area de atuacao invalida");

        this.areaAtuacao = areaAtuacao;
    }

    public void setCep (String cep) throws Exception
    {
        if (cep == null || cep.equals(""))
            throw new Exception ("CEP invalido");

        if (cep.length() != 8)
            throw new Exception ("CEP invalido");

        for (int i = 0; i < cep.length(); i++)
            if (Character.isLetter(cep.charAt(i)))
                throw new Exception ("CEP invalido");

        this.cep = cep;
    }

    public void setNumero (int numero) throws Exception
    {
        if (numero < 0 && numero > 9999)
                throw new Exception ("Numero invalido");

        this.numero = numero;
    }

    public void setComplemento (String complemento) throws Exception
    {
        if (complemento==null)
            throw new Exception ("Complemento invalido");

        if (complemento.length() > 20)
            throw new Exception ("Complmento invalido");

        this.complemento = complemento;
    }

    public void setRg (String rg) throws Exception
    {
        if (rg==null || rg.equals(""))
            throw new Exception ("RG invalido");

        if (rg.length() != 9)
            throw new Exception ("RG invalido");

        for (int i = 0; i < rg.length(); i++)
            if (Character.isLetter(rg.charAt(i)))
                throw new Exception ("RG invalido");

        this.rg = rg;
    }

    public String getCrm ()
    {
        return this.crm;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public String getTelefone ()
    {
        return this.telefone;
    }

    public String getAreaAtuacao ()
    {
        return this.areaAtuacao;
    }

    public String getCep ()
    {
        return this.cep;
    }

    public int getNumero ()
    {
        return this.numero;
    }

    public String getRg () { return this.rg; }

    public String getComplemento () { return this.complemento; }

    public Medico (String crm, String nome, String areaAtuacao,
                   String telefone, String rg, String cep, int numero,
                   String complemento) throws Exception
    {
        this.setCrm         (crm);
        this.setNome        (nome);
        this.setAreaAtuacao (areaAtuacao);
        this.setTelefone    (telefone);
        this.setRg          (rg);
        this.setCep         (cep);
        this.setNumero      (numero);
        this.setComplemento (complemento);
    }

    public String toString ()
    {
        String ret="";

        ret+="CRM............: "+this.crm+"\n";
        ret+="Nome...........: "+this.nome+"\n";
        ret+="Area de Atuacao: "+this.areaAtuacao+"\n";
        ret+="Telefone.......: "+this.telefone+"\n";
        ret+="RG.............: " +this.rg+"\n";
        ret+="CEP............: " + this.cep+"\n";
        ret+="Número.........: "+this.numero+"\n";
        ret+="Complemento....: "+this.complemento;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Medico))
            return false;

        Medico med = (Medico)obj;

        if (this.getCrm().equals(med.getCrm()))
            return false;

        if (this.nome.equals(med.getNome()))
            return false;

        if (this.getNome().equals(med.getNome()))
            return false;

        if (this.getAreaAtuacao().equals(med.getAreaAtuacao()))
            return false;

        if (this.getTelefone().equals(med.getTelefone()))
            return false;

        if (this.getRg().equals(med.getRg()))
            return false;

        if (this.getCep().equals(med.getCep()))
            return false;

        if (this.getNumero()!=med.getNumero())
            return false;

        if (this.getComplemento().equals(med.getComplemento()))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.crm.hashCode();
        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + this.areaAtuacao.hashCode();
        ret = 7*ret + this.telefone.hashCode();
        ret = 7*ret + this.rg.hashCode();
        ret = 7*ret + this.cep.hashCode();
        ret = 7*ret + Integer.valueOf(this.numero).hashCode();
        ret = 7*ret + this.complemento.hashCode();

        return ret;
    }


    public Medico (Medico modelo) throws Exception
    {
        this.crm = modelo.crm;
        this.nome = modelo.nome;
        this.areaAtuacao = modelo.areaAtuacao;
        this.telefone = modelo.telefone;
        this.rg = modelo.rg;
        this.cep = modelo.cep;
        this.numero = modelo.numero;
        this.complemento = modelo.complemento;
    }

    public Object clone ()
    {
        Medico ret = null;

        try
        {
            ret = new Medico(this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca é null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
}
