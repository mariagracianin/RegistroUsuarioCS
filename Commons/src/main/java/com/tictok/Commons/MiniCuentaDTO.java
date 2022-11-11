package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MiniCuentaDTO {
    private String mailMiniCuentaDTO;
    private String tipoMiniCuentaDTO;

    public MiniCuentaDTO() {
    }

    public MiniCuentaDTO(String mailMiniCuentaDTO, String tipoMiniCuentaDTO) {
        this.mailMiniCuentaDTO = mailMiniCuentaDTO;
        this.tipoMiniCuentaDTO = tipoMiniCuentaDTO;
    }

    public String getMailMiniCuentaDTO() {
        return mailMiniCuentaDTO;
    }

    public void setMailMiniCuentaDTO(String mailMiniCuentaDTO) {
        this.mailMiniCuentaDTO = mailMiniCuentaDTO;
    }

    public String getTipoMiniCuentaDTO() {
        return tipoMiniCuentaDTO;
    }

    public void setTipoMiniCuentaDTO(String tipoMiniCuentaDTO) {
        this.tipoMiniCuentaDTO = tipoMiniCuentaDTO;
    }


}
