package org.nk.audit_and_cache;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Builder
@Getter
public class StudentResource {

  private final UUID id;
  @With
  private final String name;
  private final Instant lastModifiedDate;
  private final Long version;
  private final Instant createdDate;
}
