package br.com.SocketSocial.model;

import java.sql.Timestamp;
import java.util.Date;

public class FeedComment{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Long idFeed;
	
	private String texto;
	
	private Long data;
	private Date dataView;
	
	private Long hora;
	private Timestamp horaView;
	
	private Long idUser;
		
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFeed() {
		return idFeed;
	}

	public void setIdFeed(Long idFeed) {
		this.idFeed = idFeed;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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







}
