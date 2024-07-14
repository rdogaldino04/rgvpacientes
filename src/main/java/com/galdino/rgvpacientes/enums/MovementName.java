package com.galdino.rgvpacientes.enums;

public enum MovementName {
    ENTRADA_AVULSA("Entrada Avulsa"),
    SAIDA_AVULSA("Saída Avulsa"),
    TRANSFERENCIA("Transferência");

    private String value;

    private MovementName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
