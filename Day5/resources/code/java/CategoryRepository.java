package e62f.bobochan.lp03;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	public Category findByName(String name);

}
