package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validacaoEmail")
public class ValidarEmail implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent uic, Object o) throws ValidatorException {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher((String) o);
        if(!m.matches()){
            FacesMessage msg = new FacesMessage("Email inválido! Por favor, preencha corretamente o email.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
		
	}

}
