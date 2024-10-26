package articlesystem.repository;

import articlesystem.model.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAllCategory();

    void addCategory(Category category);
}
