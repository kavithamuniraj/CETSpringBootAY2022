package com.sbstransit.booklinkSBS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	@Procedure(procedureName="SearchItemsByName")
	List<Item> searchItems(String searchStr);

}
