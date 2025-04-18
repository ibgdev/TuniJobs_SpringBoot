package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Category;
import ibgdev.tunijobs.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public Category addCategory(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Long id, Category e) {
        Category c = categoryRepository.findCategoryById(id);
        if (c != null) {
            c.setId(e.getId());
            c.setNom(e.getNom());
            c.setJobOffers(e.getJobOffers());
            return categoryRepository.save(c);
        }
        return null;
    }
}
