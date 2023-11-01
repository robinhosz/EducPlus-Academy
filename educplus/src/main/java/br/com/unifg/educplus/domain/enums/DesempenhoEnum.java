package br.com.unifg.educplus.domain.enums;

public enum DesempenhoEnum {

    RUIM("Ruim"),
    REGULAR("Regular"),
    BOM("Bom"),
    OTIMO("Ótimo");

    private final String value;

    DesempenhoEnum(String value) {

        this.value = value;
    }

      public static DesempenhoEnum getByValue(String value) {

        for (DesempenhoEnum desempenho : DesempenhoEnum.values()) {

            if (desempenho.value.equals(value)) {

                return desempenho;
            }
        }

        return null;
    }

}
