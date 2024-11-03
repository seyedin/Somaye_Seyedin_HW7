package articlesystem.service.impl;

import articlesystem.model.Category;
import articlesystem.repository.CategoryRepository;
import articlesystem.repository.impl.CategoryRepositoryImpl;
import articlesystem.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAllCategory();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.addCategory(category);
    }
}
