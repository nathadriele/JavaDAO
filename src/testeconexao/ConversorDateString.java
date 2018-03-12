/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeconexao;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nathalia
 */
public class ConversorDateString extends org.jdesktop.beansbinding.Converter<Date,String> {

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public String convertForward(Date value) {
        return df.format(value);
    }

    @Override
    public Date convertReverse(String value) {
        try
        {
            return df.parse(value);
        }catch(ParseException e)
        {
            System.out.println("erro ao converter a data");
        }
        return null;
    }
}
