package restapi.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.com.vn.domain.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {

}
