package br.com.makersweb.microservices.authors.model;

/**
 * @author aaristides
 */
public class Author {

    private Long id;
    private String name;
    private String email;
    private AuthorType type;

    public Author() {}

    public Author(Long id, String name, String email, AuthorType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthorType getType() {
        return type;
    }

    public void setType(AuthorType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
