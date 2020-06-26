package com.example.demo.BlogPost.BlogPost;


	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;

	@Entity
	public class BlogPost {

	    @Id

	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String title, author, blogEntry;

	    public BlogPost() {
	        // non-argument constructor for JPA
	    }

	    public BlogPost(String title, String author, String blogEntry) {
	        this.title = title;
	        this.author = author;
	        this.blogEntry = blogEntry;
	    }
	    public Long getId() {
	        return id;
	    }
	 
	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getAuthor() {
	        return author;
	    }

	    public void setAuthor(String author) {
	        this.author = author;
	    }

	    public String getBlogEntry() {
	        return blogEntry;
	    }

	    public void setBlogEntry(String blogEntry) {
	        this.blogEntry = blogEntry;
	    }

	    @Override
	    public String toString() {
	        return "BlogPost [author=" + author + ", blogEntry=" + blogEntry + ", id=" + id + ", title=" + title + "]";
	    }

	}

