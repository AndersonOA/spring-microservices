package br.com.makersweb.microservices.authors.resource;

import br.com.makersweb.microservices.authors.model.Author;
import br.com.makersweb.microservices.authors.model.AuthorType;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("/authors")
public class AuthorResource {

    private List<Author> authors;
    private Logger logger = Logger.getLogger(AuthorResource.class.getName());

    public AuthorResource() {
        this.authors = new ArrayList<>();

        this.authors.add(new Author(1l, "John Snow", "john.snow@gmail.com", AuthorType.EDITOR));
        this.authors.add(new Author(2l, "John Connor", "john.connor@gmail.com", AuthorType.WRITER));
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id) {
        this.logger.info(String.format("Authors.findById(%d)", id));
        return this.authors.stream().filter(aut -> aut.getId().intValue() == id.intValue()).findFirst().get();
    }

    @HystrixCommand(fallbackMethod = "getAllCached")
    @GetMapping
    public List<Author> getAll() {
        this.logger.info("Authors.getAll()");
        return this.authors;
    }

    public List<Author> getAllCached() {
        this.logger.info("Authors.getAllCached()");
        this.logger.warning("Return cached result here");

        return new ArrayList<>();
    }

}
