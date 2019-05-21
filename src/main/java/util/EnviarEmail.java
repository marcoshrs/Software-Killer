package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import base.controle.UsuarioMB;
import base.modelo.Usuario;

public class EnviarEmail {
	
	private Usuario usuario;
	
	/*
	 * quando instanciar um Objeto ja sera atribuido o servidor SMTP do GMAIL 
	 * e a porta usada por ele
	 */
	public EnviarEmail () { //Para o GMAIL 
		
		
	}
	
		public static Boolean enviarEmail(String destinatario, String titulo, String mensagem) {
			
			
		
			Boolean flag = false;
			
			
			
			try {
				SimpleEmail email = new SimpleEmail();
			
				email.setHostName("smtp.gmail.com");
				
				email.setSmtpPort(465);
		
				email.addTo(destinatario);

				email.setFrom("softcalqui@gmail.com");;
				// Adicione um assunto
				email.setSubject(titulo);
				// Adicione a mensagem do email
				email.setMsg(mensagem);
				
			//	System.out.println("autenticando...");
				email.setSSL(true);
				email.setAuthentication("softcalqui@gmail.com", "calculo10");
			//	System.out.println("enviando...");
				email.send();
			//	System.out.println("Email enviado!");
				
				flag = true;
			
			} catch (EmailException e) {
				e.printStackTrace();
				ExibirMensagem.exibirMensagem(Mensagem.ERRO_EMAIL +" "+e);

			}
			
			
			if (flag) {
		
				return true;
			} else {
			
				return false;
			}
		}

	
}  
    
