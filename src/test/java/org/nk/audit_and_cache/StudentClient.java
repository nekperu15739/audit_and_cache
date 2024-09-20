package org.nk.audit_and_cache;

import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

interface StudentClient {

  @PostExchange
  StudentResource create(@RequestBody StudentResource studentModel);

  @PutExchange("/{id}")
  StudentResource update(@RequestBody StudentResource studentModel, @PathVariable UUID id);

  @GetExchange("/{id}")
  StudentResource findById(@PathVariable UUID id);

}
