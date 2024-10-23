package org.serratec.ecommerce.pataMagica.model;

public class ErrorResponse {

	private String mensagemErro;
	private int statusErro;

	public ErrorResponse(String mensagemErro, int statusErro) {
		this.mensagemErro = mensagemErro;
		this.statusErro =statusErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public int getStatusErro() {
		return statusErro;
	}

	public void setStatusErro(int statusErro) {
		this.statusErro = statusErro;
	}
}
