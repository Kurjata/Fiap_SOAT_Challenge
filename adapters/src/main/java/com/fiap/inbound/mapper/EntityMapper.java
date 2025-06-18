package com.fiap.inbound.mapper;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mapstruct.Named;

public interface EntityMapper {
  @Named("toObjectId")
  default ObjectId toObjectId(String id) {
    if (StringUtils.isEmpty(id)) return new ObjectId();
    return new ObjectId(id);
  }

  @Named("toId")
  default String toId(ObjectId id) {
    return id.toHexString();
  }
}
