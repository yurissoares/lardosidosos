package com.ufrb.lardosidosos.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidityErrorMsg {
    
    public static final String MAX_CHAR_SIZE_70 = "Tamanho máximo de 70 characteres permitido.";
    public static final String MAX_CHAR_SIZE_50 = "Tamanho máximo de 50 characteres permitido.";
    public static final String MAX_CHAR_SIZE_20 = "Tamanho máximo de 20 characteres permitido.";
    public static final String MAX_CHAR_SIZE_10 = "Tamanho máximo de 10 characteres permitido.";
    public static final String MAX_CHAR_SIZE_255 = "Tamanho máximo de 255 characteres permitido.";
    public static final String DATE_NOT_BLANK = "A data não pode estar em branco ou nula.";

    private final String valor;
}
