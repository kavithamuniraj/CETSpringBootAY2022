package e62f.bobochan.lp03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>  {

	public List<Item> findByCategory_Id(int id);
	
}
