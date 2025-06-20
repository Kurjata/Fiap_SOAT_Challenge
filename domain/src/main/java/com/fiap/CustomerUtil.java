package com.fiap;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class CustomerUtil {
    public static String clearDocumentNumber(String documentNumber) {
        return documentNumber.replaceAll("-", "").replaceAll("\\.", "");
    }
}
