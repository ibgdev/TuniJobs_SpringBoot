package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findCategoryById(Long idCategory);
}
