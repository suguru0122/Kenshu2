package todo;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMailSender {
	private static Logger log = Logger.getLogger(SimpleMailSender.class.getName());
	
	// �A�J�E���g�ڑ�����SSL�𗘗p����ꍇ�ɗ��p����
	private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	// SMTP�T�[�o�[�ڑ��|�[�g�F25�Ԃ��w��
	private final String SMTP_PORT = "465";
	// private final String SMT_PORT = "465";
	
	// ���[���A�J�E���g
	private final String AUTH_USER_NAME = "suguru.kyogoku@gmail.com";
	
	// ���[���A�J�E���g�̃p�X���[�h
	private final String AUTH_PASSWORD = "sk01220122";
	
	// SMTP���[���z�X�g
	private static final String SMTP_HOST = "smtp.gmail.com";
	
	public static void main(String argv[]) throws Exception{
		SimpleMailSender mail = new SimpleMailSender();
		
		mail.sendMessage("suguru.kyogoku@gmail.com","suguru.kyogoku@gmail.com","Test","Test","�e�X�g�ł�");
	}
	
	// ���[�����M����
	// @param toAddr ���M���[���A�h���X
	// @param fromAdrr ���M�����[���A�h���X
	// @param personName ���M�Җ�
	// @param subject ���[���̌���
	// @param message ���[���{��
	// throws Exception
	
	public void sendMessage(String toAddr,String fromAddr,String personName,String subject,String message) throws Exception{
		// ���[�����M�v���p�e�B�̍쐬
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.host", SMTP_HOST);
		props.put("mail.from", fromAddr);
		
		// SMTP�F�ؐݒ�
		props.put("mail.smtp.auth", "true");
		
		// SMTP�\�P�b�g�|�[�g(SSL�p)
		props.put("mail.smtp.socketFactory.port",SMTP_PORT);
		
		// �\�P�b�g�N���X��
		// SSL�𗘗p����SMTP�T�[�o��SSL�p�̃\�P�b�g�t�@�N�g���[�𗘗p����
		props.put("mail.smtp.socketFactory.class",SSL_FACTORY);
		
		// SSL�t�H�[���o�b�N(SSL�p)
		props.put("mail.smtp.socketFactory.fallback",String.valueOf(false));
		
		// ���[���Z�b�V�������m������
		// �Z�b�V�����m����props�Ɋi�[�����
		Session session = Session.getDefaultInstance(props,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return (new PasswordAuthentication(AUTH_USER_NAME,AUTH_PASSWORD));
			}
		});
		session.setDebug(true);
		
		// ���M���b�Z�[�W�𐶐�
		MimeMessage mimeMsg = new MimeMessage(session);
		
		// ���M��(TO�̂ق��ACC��BCC���ݒ�\)
		mimeMsg.setRecipients(Message.RecipientType.TO, toAddr);
		
		// FROM�w�b�_
		InternetAddress fromHeader = new InternetAddress(fromAddr,personName);
		mimeMsg.setFrom(fromHeader);
		
		// ����
		mimeMsg.setSubject(subject, "ISO-2022-JP");
		
		// ���M����
		mimeMsg.setSentDate(new Date());
		
		// �{��
		mimeMsg.setText(message,"ISO-2022-JP");
		
		// ���[���𑗐M����
		Transport.send(mimeMsg);
	}
	
}