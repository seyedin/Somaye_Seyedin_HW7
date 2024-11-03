package articlesystem.service.impl;

import articlesystem.repository.UserRepository;
import articlesystem.repository.impl.UserRepositoryImpl;
import articlesystem.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public boolean updatePassword(int authorId, String newPassword) {
        return userRepository.updatePassword(authorId, newPassword);
    }


}
