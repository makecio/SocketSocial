package br.com.SocketSocial.model;

import java.sql.Timestamp;
import java.util.Date;


public class FeedLike {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Long idFeedComment;
	
	private Long tipo;
	
	private Long data;
	private Date dataView;
	
	private Long hora;
	private Timestamp horaView;
	
	private Long idUser;
		
	private boolean deleted;

	private Long qtd;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFeedComment() {
		return idFeedComment;
	}

	public void setIdFeedComment(Long idFeedComment) {
		this.idFeedComment = idFeedComment;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}

	public Date getDataView() {
		return dataView;
	}

	public void setDataView(Date dataView) {
		this.dataView = dataView;
	}

	public Long getHora() {
		return hora;
	}

	public void setHora(Long hora) {
		this.hora = hora;
	}

	public Timestamp getHoraView() {
		return horaView;
	}

	public void setHoraView(Timestamp horaView) {
		this.horaView = horaView;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getQtd() {
		return qtd;
	}

	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}
	

}
