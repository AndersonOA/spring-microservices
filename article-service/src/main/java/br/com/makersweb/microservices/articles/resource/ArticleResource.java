package br.com.makersweb.microservices.articles.resource;

import br.com.makersweb.microservices.articles.model.Article;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author aaristides
 */
@RestController
@RequestMapping("/articles")
public class ArticleResource {

    private List<Article> articles;
    private Logger logger = Logger.getLogger(ArticleResource.class.getName());

    public ArticleResource() {
        this.articles = new ArrayList<>();

        this.articles.add(new Article(1l, "First Article", LocalDate.now(), 1l));
        this.articles.add(new Article(2l, "Second Article", LocalDate.now(), 2l));
        this.articles.add(new Article(3l, "Third Article", LocalDate.now(), 2l));
        this.articles.add(new Article(4l, "Fourth Article", LocalDate.now(), 1l));
        this.articles.add(new Article(5l, "Fifth Article", LocalDate.now(), 1l));
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable Long id) {
        this.logger.info(String.format("Articles.findById(%d)", id));
        return this.articles.stream().filter(article -> article.getId().intValue() == id.intValue()).findFirst().get();
    }

    @GetMapping("/author/{authorId}")
    public List<Article> findByAuthor(@PathVariable Long authorId) {
        this.logger.info(String.format("Articles.findByAuthor(%d)", authorId));
        return this.articles.stream().filter(article -> article.getAuthorId().intValue() == authorId.intValue()).collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "getAllCached")
    @GetMapping
    public List<Article> getAll() {
        this.logger.info("Articles.getAll()");
        return this.articles;
    }

    public List<Article> getAllCached() {
        this.logger.info("Articles.getAllCached()");
        this.logger.warning("Return cached result here");

        return new ArrayList<>();
    }

}
