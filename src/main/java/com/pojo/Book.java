package com.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="BOOK")
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "BOOK_ID")
	private long bookId; 
	@Column(name = "TITLE")
	private String title;
	@Column(name = "PRICE")
	private double price;
	@Column(name = "VOLUME")
	private Integer volume;
	@Column(name = "PUBLISH_DATE")
	private Date publishDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	   @JoinTable(name = "book_subject_rel", 
	         joinColumns = { @JoinColumn(name = "BOOK_ID") }, 
	         inverseJoinColumns = { @JoinColumn(name = "SUBJECT_ID") })
	private Set<Subject> references;
	
	public Set<Subject> getReferences() {
		return references;
	}
	public void setReferences(Set<Subject> references) {
		this.references = references;
	}
	public Book(){
		
	}
	public Book(long bookId,String title,double price,Integer volume,
			Date publishDate){
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishDate = publishDate;
	}
	
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishDate=" + publishDate + "]";
	}
	
	 @Override
     public boolean equals(final Object obj) {
       if (obj == null) {
          return false;
       }
       final Book std = (Book) obj;
       if (this == std) {
          return true;
       } else {
          return (this.title.equals(std.title));
       }
     }
	

}
