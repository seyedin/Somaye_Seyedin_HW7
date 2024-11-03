package articlesystem.repository;

public interface UserRepository {
    boolean updatePassword(int authorId, String newPassword);
}
