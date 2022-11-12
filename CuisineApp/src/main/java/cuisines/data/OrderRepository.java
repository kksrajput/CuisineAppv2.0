package cuisines.data;

import org.springframework.data.repository.CrudRepository;

import cuisines.CuisineOrder;

public interface OrderRepository 
         extends CrudRepository<CuisineOrder, Long> {

}
