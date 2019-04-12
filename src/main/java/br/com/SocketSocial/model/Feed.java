package br.com.SocketSocial.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Feed implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String texto;
	
	private String titulo;
	
	private Long data;
	private Date dataView;
	
	private Long hora;
	private Timestamp horaView;
	
	private String link;
	
	private String idUser;
	
	private Long qtdLikes;
	
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
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

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}

	public Long getHora() {
		return hora;
	}

	public void setHora(Long hora) {
		this.hora = hora;
	}

	public Date getDataView() {
		return dataView;
	}

	public void setDataView(Date dataView) {
		this.dataView = dataView;
	}

	public Timestamp getHoraView() {
		return horaView;
	}

	public void setHoraView(Timestamp horaView) {
		this.horaView = horaView;
	}

	public Long getQtdLikes() {
		return qtdLikes;
	}

	public void setQtdLikes(Long qtdLikes) {
		this.qtdLikes = qtdLikes;
	}


	


}
