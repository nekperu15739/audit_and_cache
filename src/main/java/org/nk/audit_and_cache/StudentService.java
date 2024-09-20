package org.nk.audit_and_cache;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  @Transactional
  public StudentModel create(final StudentModel studentModel) {
    return studentRepository.save(studentModel);
  }

  @Transactional
  public StudentModel update(final StudentModel studentModel, final UUID id) {
    return studentRepository.findById(id)
        .map(s -> s.withName(studentModel.getName()))
        .map(studentRepository::save)
        .orElseThrow(RuntimeException::new);
  }

  @Transactional(readOnly = true)
  public Optional<StudentModel> findById(final UUID id) {
    return studentRepository.findById(id);
  }

}
