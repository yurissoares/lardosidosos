package com.ufrb.lardosidosos.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InternalErrorMsg {
    INTERNAL_ERROR_MSG("Erro interno identificado. Contate o suporte.");

    private final String valor;
}
