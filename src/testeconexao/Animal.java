
package testeconexao;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

/**
 *
 * @author Nathalia
 */
public class Animal {
    
    private Integer codigo;

    public static final String PROP_CODIGO = "codigo";

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        Integer oldCodigo = this.codigo;
        this.codigo = codigo;
        propertyChangeSupport.firePropertyChange(PROP_CODIGO, oldCodigo, codigo);
    }

    private String nome;

    public static final String PROP_NOME = "nome";

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        propertyChangeSupport.firePropertyChange(PROP_NOME, oldNome, nome);
    }
    
    private String especie;

    public static final String PROP_ESPECIE = "especie";

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        String oldEspecie = this.especie;
        this.especie = especie;
        propertyChangeSupport.firePropertyChange(PROP_ESPECIE, oldEspecie, especie);
    }

    private Date dataNasc;

    public static final String PROP_DATANASC = "dataNasc";

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        Date oldDataNasc = this.dataNasc;
        this.dataNasc = dataNasc;
        propertyChangeSupport.firePropertyChange(PROP_DATANASC, oldDataNasc, dataNasc);
    }

    private Cliente dono;

    public static final String PROP_DONO = "dono";

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        Cliente oldDono = this.dono;
        this.dono = dono;
        propertyChangeSupport.firePropertyChange(PROP_DONO, oldDono, dono);
    }

    
    
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    
}
