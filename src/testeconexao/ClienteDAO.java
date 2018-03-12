
package testeconexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nathalia
 */
public class ClienteDAO extends DAO<Cliente>{

    @Override
    public boolean inserir(Cliente element) {
        try
        {
            String sql = "INSERT INTO cliente (nome, idade) values(?,?);";
            PreparedStatement stmt = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, element.getNome());
            stmt.setInt(2, element.getIdade());
            int linhas = stmt.executeUpdate();
            if(linhas==1)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                int k = rs.getInt(1);
                element.setCodigo(k);
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.printf("Erro: %s\n", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean alterar(Cliente element) {

        try
        {
            String sql = "update cliente set nome = ?, idade = ? where codigo = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, element.getNome());
            stmt.setInt(2, element.getIdade());
            stmt.setInt(3, element.getCodigo());
            int linhas = stmt.executeUpdate();
            if(linhas == 1)
                return true;
        }
        catch(SQLException e)
        {
            System.out.printf("erro: %s\n",e.getMessage());
        }        
        return false;
    }

    @Override
    public boolean excluir(Cliente element) {
        try
        {
            String sql = "delete from cliente where codigo=?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, element.getCodigo());
            
            if(stmt.executeUpdate()>0)
            {
                return true;
            }
            
        }catch(SQLException e)
        {
            System.out.printf("erro ao excluir: %s\n", e.getMessage());
        }
        return false;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> lst = new LinkedList<>();
        lst = org.jdesktop.observablecollections.ObservableCollections.observableList(lst);
        try
        {
            String sql = "select * from cliente;";
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                Cliente c = new Cliente();
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setIdade(rs.getInt("idade"));
                lst.add(c);
            }
        }
        catch(SQLException e)
        {
            System.out.printf("erro ao listar: "+e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean gravar(Cliente element) {
        if(element.getCodigo() == null)
        {
            return inserir(element);
        }
        else
        {
            return alterar(element);
        }
    }
    
    public Cliente getById(int id)
    {
        try
        {
            String sql = "select * from cliente where codigo=?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                Cliente cl = new Cliente();
                cl.setCodigo(rs.getInt("codigo"));
                cl.setNome(rs.getString("nome"));
                cl.setIdade(rs.getInt("idade"));
                return cl;
            }
        }
        catch(SQLException e)
        {
            System.out.printf("Erro: %s\n", e.getMessage());
        }
        return null;
    }
    
}
