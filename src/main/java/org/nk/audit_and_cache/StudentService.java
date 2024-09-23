package org.nk.audit_and_cache;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = StudentRepository.MIDS)
public class StudentService {

  private final StudentRepository studentRepository;
  private final StudentUpdater updater;

  @Transactional
  public StudentModel create(final StudentModel studentModel) {
    return studentRepository.save(studentModel);
  }

  @CachePut(key = "#result.id")
  public StudentModel update(final StudentModel studentModel, final UUID id) {
    return updater.update(studentModel, id);
  }

  @Transactional(readOnly = true)
  public Optional<StudentModel> findById(final UUID id) {
    return studentRepository.findById(id);
  }

}
