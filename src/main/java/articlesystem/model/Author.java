package articlesystem.model;

public class Author extends User {
    private String nationalCode;
    private String birthday;

    public Author(int id, String username, String nationalCode, String birthday, String password) {
        super(id, username, password);
        this.nationalCode = nationalCode;
        this.birthday = birthday;
    }
}