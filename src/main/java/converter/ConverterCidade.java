package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import base.modelo.Cidade;
import dao.GenericDAO;
import util.Mensagem;

@Named("cidadeConverter")
public class ConverterCidade implements Converter{
	
	@Inject
	private GenericDAO<Cidade> dao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				
				return  dao.buscarPorId(Cidade.class, Long.parseLong(value));
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Mensagem.ERRO_CONVERTER, ""));
			}
		}
			return null;
		
	}
	

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o != null) {
			Cidade cidade = (Cidade) o;
			return cidade.getId().toString();
        }         
        return null;
	}

}
