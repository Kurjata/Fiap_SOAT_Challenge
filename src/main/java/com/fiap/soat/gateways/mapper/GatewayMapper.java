package com.fiap.soat.gateways.mapper;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mapstruct.Named;

public interface GatewayMapper {
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
