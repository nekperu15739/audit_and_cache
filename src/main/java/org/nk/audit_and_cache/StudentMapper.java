package org.nk.audit_and_cache;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    implementationName = "MapStruct<CLASS_NAME>"
)
public interface StudentMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
  @Mapping(source = "createdDate", target = "createdDate")
  @Mapping(source = "version", target = "version")
  StudentModel from(StudentResource resource);

  @InheritInverseConfiguration
  StudentResource from(StudentModel studentModel);
}
