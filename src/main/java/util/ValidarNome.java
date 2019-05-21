package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validacaoNome")
public class ValidarNome implements Validator {

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
		
		String nome = (String) o;
	       if(nome.length() < 8|| !nome.contains(" ") || nome.contains("@")){
	           //caso nao atenda as validaçoes, vamos retornar uma msg ao usuario
	           FacesMessage msg = new FacesMessage("Nome inválido, insira o nome completo");
	           msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	           throw new ValidatorException(msg);       }
		
	}
}
