package articlesystem.service;

import articlesystem.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();

    void addCategory(Category category);
}
