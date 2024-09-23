package org.nk.audit_and_cache;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@CacheConfig(cacheNames = StudentRepository.MIDS)
public interface StudentRepository extends JpaRepository<StudentModel, UUID> {

  String MIDS = "mids";

  @Override
  @Cacheable
  Optional<StudentModel> findById(UUID id);

  @Override
  @Cacheable
  List<StudentModel> findAll();

  @Override
  @Modifying
  @Transactional
  StudentModel save(StudentModel entity);
}
