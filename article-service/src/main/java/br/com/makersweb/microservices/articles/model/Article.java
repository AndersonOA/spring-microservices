package br.com.makersweb.microservices.articles.model;

import java.time.LocalDate;

/**
 * @author aaristides
 */
public class Article {

    private Long id;
    private String name;
    private LocalDate publishDate;
    private Long authorId;

    public Article() {

    }

    public Article(Long id, String name, LocalDate publishDate, Long authorId) {
        this.id = id;
        this.name = name;
        this.publishDate = publishDate;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishDate=" + publishDate +
                ", authorId=" + authorId +
                '}';
    }
}
