package articlesystem.service;

import articlesystem.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();

    void addAuthor(Author author);
}
