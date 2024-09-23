package org.nk.audit_and_cache;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StudentUpdater {

  private final StudentRepository studentRepository;

  @Transactional
  public StudentModel update(final StudentModel studentModel, final UUID id) {
    return studentRepository.findById(id)
        .map(s -> s.withName(studentModel.getName()))
        .map(studentRepository::save)
        .orElseThrow(RuntimeException::new);
  }

}
