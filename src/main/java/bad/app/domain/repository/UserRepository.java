package bad.app.domain.repository;

import bad.app.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
}
