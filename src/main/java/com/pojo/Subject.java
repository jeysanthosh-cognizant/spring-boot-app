package com.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@Table(name="SUBJECT")
public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="SUBJECT_ID")
	private long subjectId;
	@Column(name="SUBTITLE")
	private String subtitle;
	@Column(name="DURATION_IN_HOURS")
	private int durationInHours;
	@Transient
	private long refBookId;
	
	public long getRefBookId() {
		return refBookId;
	}

	public void setRefBookId(long refBookId) {
		this.refBookId = refBookId;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	   @JoinTable(name = "book_subject_rel", 
	         joinColumns = { @JoinColumn(name = "SUBJECT_ID") }, 
	         inverseJoinColumns = { @JoinColumn(name = "BOOK_ID") })
	private Set<Book> references;
	
	public Subject() {
		
	}
	
	public Subject(long subjectId,String subtitle,int durationInHours,Set<Book> references) {
		this.subjectId = subjectId;
		this.subtitle = subtitle;
		this.durationInHours = durationInHours;
		this.references = references;
	}
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}
	public Set<Book> getReferences() {
		return references;
	}
	public void setReferences(Set<Book> references) {
		this.references = references;
	}
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle + ", durationInHours=" + durationInHours
				+ ", references=" + references + "]";
	}

	@Override
    public boolean equals(final Object obj) {
      if (obj == null) {
         return false;
      }
      final Subject std = (Subject) obj;
      if (this == std) {
         return true;
      } else {
         return (this.subtitle.equals(std.subtitle) );
      }
    }
	
}
