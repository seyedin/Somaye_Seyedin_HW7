package articlesystem.repository.impl;

import articlesystem.database.Database;
import articlesystem.model.Author;
import articlesystem.repository.AuthorRepository;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {
    @Override
    public List<Author> findAllAuthors(){
        return Database.authors;
    }
    @Override
    public void addAuthor(Author author){
        Database.authors.add(author);
    }
}
