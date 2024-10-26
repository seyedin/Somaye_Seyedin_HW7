package articlesystem.repository.impl;

import articlesystem.database.Database;
import articlesystem.model.Category;
import articlesystem.repository.CategoryRepository;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public List<Category> findAllCategory() {
        return Database.categories;
    }

    @Override
    public void addCategory(Category category) {
        Database.categories.add(category);
    }
}
