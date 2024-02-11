package ajmac.interview.java.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ajmac.interview.java.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleFilter, String descFilter,
            Pageable pageable);
}
