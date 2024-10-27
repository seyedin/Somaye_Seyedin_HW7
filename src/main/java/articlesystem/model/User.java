package articlesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;

    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password changed successfully");
    }
}