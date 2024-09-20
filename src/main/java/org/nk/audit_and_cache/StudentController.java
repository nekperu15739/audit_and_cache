package org.nk.audit_and_cache;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;
  private final StudentMapper studentMapper;

  @PostMapping
  public StudentResource create(@RequestBody final StudentResource resource) {
    var model = studentMapper.from(resource);
    var modelCreated = studentService.create(model);
    return studentMapper.from(modelCreated);
  }

  @PutMapping("/{id}")
  public StudentResource update(@RequestBody final StudentResource resource, @PathVariable("id") final UUID id) {
    var model = studentMapper.from(resource);
    var modelUpdated = studentService.update(model, id);
    return studentMapper.from(modelUpdated);
  }

  @GetMapping("/{id}")
  public Optional<StudentResource> findById(@PathVariable("id") final UUID id) {
    return studentService.findById(id)
        .map(studentMapper::from);
  }
}
