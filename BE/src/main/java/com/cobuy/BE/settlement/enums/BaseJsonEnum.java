package com.cobuy.BE.settlement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface BaseJsonEnum {
    String getKey();
    String getValue();

}
