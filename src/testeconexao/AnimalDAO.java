
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
public class AnimalDAO extends DAO<Animal> {

    @Override
    public boolean inserir(Animal element) {
        try
        {
            String sql = "insert into animal (nome,especie,dataNasc,dono) values (?,?,?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,element.getNome());
            stmt.setString(2,element.getEspecie());
            if(element.getDataNasc()==null)
            {
                stmt.setDate(3,null);
            }
            else
            {
                java.sql.Date sdate = new java.sql.Date(element.getDataNasc().getTime());
                stmt.setDate(3, sdate);
            }
            if(element.getDono()==null) //nao consigo inserir animal sem dono no banco de dados
                                        //no banco--> dono integer not null,
            {
                return false;
            }
            else
            {
                if(element.getDono().getCodigo()==null) //cliente não está no banco!!! gravar!!!
                {
                    ClienteDAO cdao = new ClienteDAO();
                    if(cdao.inserir(element.getDono())==true) //se conseguiu inserir no banco
                    {
                        stmt.setInt(4, element.getDono().getCodigo());
                    }
                }
                else
                {
                    ClienteDAO cdao = new ClienteDAO();
                    cdao.alterar(element.getDono());
                    stmt.setInt(4, element.getDono().getCodigo());
                }
            }
            if(stmt.executeUpdate()>0)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                element.setCodigo(rs.getInt(1));
                return true;
            }
        }catch(SQLException e)
        {
            System.out.printf("Erro ao inserir: %s\n",e.getMessage());
        }
        return false;
    }

    @Override
    public boolean alterar(Animal element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Animal element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean gravar(Animal element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Animal> listar() {
        List<Animal> lstAnimal = new LinkedList<>();
        try
        {
            String sql = "select * from animal;";
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                Animal a = new Animal();
                a.setCodigo(rs.getInt("codigo"));
                a.setNome(rs.getString("nome"));
                a.setEspecie(rs.getString("especie")); 
                a.setDataNasc(rs.getDate("dataNasc"));
                ClienteDAO cdao = new ClienteDAO();
                Cliente cl = cdao.getById(rs.getInt("dono"));
                if(cl == null)
                {
                    System.out.printf("erro grave - dono do animal nao cadastrado ou erro ao executar a consulta");
                }
                a.setDono(cl);
                lstAnimal.add(a);
            }
        }
        catch(SQLException e)
        {
            System.out.printf("Erro: %s\n", e.getMessage());
        }
        return lstAnimal;
    }
    
}
