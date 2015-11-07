package br.edu.ifpb.sistemasdistribuidos.beans;

/**
 * Created by Nath on 11/7/2015.
 */
public class ResponseBean {

    private int status;
    private String acronimo;
    private String significado;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }
}
