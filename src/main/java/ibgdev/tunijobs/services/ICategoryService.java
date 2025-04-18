package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> findAll();

    public Category findCategoryById(Long id);

    public Category addCategory(Category c);
    public void deleteCategory(Long id);

    public Category updateCategory(Long id , Category e);
}
