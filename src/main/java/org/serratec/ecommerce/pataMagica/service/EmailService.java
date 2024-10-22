package org.serratec.ecommerce.pataMagica.service;

import org.serratec.ecommerce.pataMagica.dto.RelatorioItemDto;
import org.serratec.ecommerce.pataMagica.dto.RelatorioPedidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/*@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	public String enviarEmail(String destinatario, String assunto, String mensagem) {
		SimpleMailMessage enviaMensagem = new SimpleMailMessage();
		
		enviaMensagem.setFrom("exemplo@gmail.com");
		enviaMensagem.setTo(destinatario);
		enviaMensagem.setSubject(assunto);
		enviaMensagem.setText(mensagem);
		
		try {
			sender.send(enviaMensagem);
			return "E-mail enviado com sucesso";
		} catch (Exception e) {
			return "Erro ao enviar mensagem. Verifique!";
		}	
	}
}*/

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarRelatorioPedido(RelatorioPedidoDto relatorio, String destinatario) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(destinatario);
        helper.setSubject("Relatório de Pedido");

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("<h1>Relatório de Pedido</h1>");
        emailContent.append("<p><strong>ID do Pedido:</strong> ").append(relatorio.getIdPedido()).append("</p>");
        emailContent.append("<p><strong>Data do Pedido:</strong> ").append(relatorio.getDataPedido()).append("</p>");
        emailContent.append("<p><strong>Valor Total:</strong> ").append(relatorio.getValorTotal()).append("</p>");
        emailContent.append("<h2>Itens do Pedido:</h2>");
        emailContent.append("<ul>");

        for (RelatorioItemDto item : relatorio.getItensPedido()) {
            //RelatorioItemDto produto = item.get;
            emailContent.append("<li>"); 
            emailContent.append("<p><strong>ID do Produto:</strong> ").append(item.getIdProduto()).append("</p>");
            emailContent.append("<p><strong>Nome do Produto:</strong> ").append(item.getNomeProduto()).append("</p>");
            emailContent.append("<p><strong>Preço Unitário:</strong> ").append(item.getPrecoUnitarioProduto()).append("</p>");
            emailContent.append("<p><strong>Quantidade:</strong> ").append(item.getQtdeItem()).append("</p>");
            emailContent.append("<p><strong>Percentual de Desconto:</strong> ").append(item.getPercentualDescontoItem()).append("</p>");
            emailContent.append("<p><strong>Valor Líquido:</strong> ").append(item.getValorLiquidoItem()).append("</p>");
            emailContent.append("</li>");
        }


        emailContent.append("</ul>");
        helper.setText(emailContent.toString(), true);

        mailSender.send(message);
    }
}
