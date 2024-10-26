package articlesystem.repository;

import articlesystem.model.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> findAllAuthors();
    void addAuthor(Author author);
}
