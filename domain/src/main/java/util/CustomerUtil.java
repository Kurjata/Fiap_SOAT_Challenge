package util;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CustomerUtil {
    public static String clearDocumentNumber(String documentNumber) {
        return documentNumber.replaceAll("-", "").replaceAll("\\.", "");
    }
}
