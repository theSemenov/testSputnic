package ru.sputnic.test.humanmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Human implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5093219456013384288L;
	private Long id;
	
	@NotNull(message="Имя не должно быть null")
	@Size(min=1, message="Длина имени должна быть больше {min} символа")
	private String fname;
	
	@NotNull(message="Фамилия не должно быть null")
	@Size(min=1, message="Длина фамилии должна быть больше {min} символа")
	private String sname;
	
	@NotNull(message="Отчество не должно быть null")
	@Size(min=1, message="Длина отчества должна быть больше {min} символа")
	private String mname;
	
	@NotNull(message="Дата рождния не должно быть null")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy", timezone="ru")
	private Date birthDate;
	
	public Human() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
