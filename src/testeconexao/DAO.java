/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeconexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aluno
 */
public abstract class DAO<E> {
    private Connection conn;
    public DAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/aula_dsoo";
            String user="root";
            String password="univem";
            conn = DriverManager.getConnection(url,user, password);
        }
        catch(ClassNotFoundException e)
        {
            System.out.printf("Classe nao encontrada\n");
        }
        catch(SQLException e)
        {
            System.out.printf("Erro no banco de dados: %s\n",e.getMessage());
        }        
    }
    
    protected Connection getConnection()
    {
        return conn;
    }
    
    protected boolean closeConnection()
    {
        try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            return false;
        }
        return true;
    }
    
    public abstract boolean inserir(E element);
    public abstract boolean alterar(E element);
    public abstract boolean excluir(E element);
    public abstract boolean gravar(E element);
    public abstract List<E> listar();
/*    
    public void inserir(String nome,int idade)
    {

    }
    public void alterar(int codigo, String nome, int idade)
    {

    }
*/    
    public static void main(String[] args) {
        ClienteDAO t = new ClienteDAO();

        Cliente c = new Cliente();
        c.setNome("Mario");
        c.setIdade(1);
        t.gravar(c);
        
        c.setIdade(20);
        t.gravar(c);
        
        //t.alterar(3,"Jorge", 35);
    }
    
}
