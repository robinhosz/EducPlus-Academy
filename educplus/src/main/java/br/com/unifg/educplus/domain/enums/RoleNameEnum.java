package br.com.unifg.educplus.domain.enums;

public enum RoleNameEnum {
    ROLE_PROFESSOR("role_professor"),
    ROLE_ALUNO("role_aluno");

    private final String value;

    RoleNameEnum(String value) {

        this.value = value;
    }

    public static String getByValue(String value) {

        for (RoleNameEnum roleName : RoleNameEnum.values()) {

            if (roleName.value.equals(value)) {

                return roleName.toString();
            }
        }

        return null;
    }
}
