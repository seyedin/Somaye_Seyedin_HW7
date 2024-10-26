package articlesystem.service.impl;

import articlesystem.model.Author;
import articlesystem.repository.AuthorRepository;
import articlesystem.repository.impl.AuthorRepositoryImpl;
import articlesystem.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository = new AuthorRepositoryImpl();

    @Override
    public List<Author> findAllAuthors(){
        return authorRepository.findAllAuthors();
    }
    @Override
    public void addAuthor(Author author){
        authorRepository.addAuthor(author);
    }
}
