package articlesystem.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Author extends User {
    private String nationalCode;
    private LocalDate birthday;

    public Author(int id, String username, String nationalCode, LocalDate birthday, String password) {
        super(id, username, password);
        this.nationalCode = nationalCode;
        this.birthday = birthday;
    }
}