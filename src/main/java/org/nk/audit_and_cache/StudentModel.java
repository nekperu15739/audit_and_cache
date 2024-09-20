package org.nk.audit_and_cache;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class StudentModel implements Serializable {

  private static final long serialVersionUID = -5568414877301936922L;

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String name;

  @LastModifiedDate
  private Instant lastModifiedDate;

  @CreatedDate
  private Instant createdDate;

  @Version
  private Long version;

  public StudentModel withName(final String name) {
    this.name = name;
    return this;
  }
}
