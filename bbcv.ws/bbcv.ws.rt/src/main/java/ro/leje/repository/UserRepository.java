package ro.leje.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ro.leje.model.entity.User;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findByFirstName(String firstName);
}
