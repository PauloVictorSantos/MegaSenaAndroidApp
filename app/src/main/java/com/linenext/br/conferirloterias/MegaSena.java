package com.linenext.br.conferirloterias;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Paulo Victor on 18/12/2017.
 */

public class MegaSena implements Serializable{
    static final long serialVersionUID = 42L;

    private int numero;
    private Date data;
    private int sorteio[]=new int[5];
    private int ganhadores[]= new int[5];
    private Double rateio []= new Double[5];
    private boolean acumulado;
    private Double valor_acumulado;
    private String Cidades;
    private Double proxima_estimativa;
    private Date proxima_data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int[] getSorteio() {
        return sorteio;
    }

    public void setSorteio(int[] sorteio) {
        this.sorteio = sorteio;
    }

    public int[] getGanhadores() {
        return ganhadores;
    }

    public void setGanhadores(int[] ganhadores) {
        this.ganhadores = ganhadores;
    }

    public Double[] getRateio() {
        return rateio;
    }

    public void setRateio(Double[] rateio) {
        this.rateio = rateio;
    }

    public boolean isAcumulado() {
        return acumulado;
    }

    public void setAcumulado(boolean acumulado) {
        this.acumulado = acumulado;
    }

    public Double getValor_acumulado() {
        return valor_acumulado;
    }

    public void setValor_acumulado(Double valor_acumulado) {
        this.valor_acumulado = valor_acumulado;
    }

    public String getCidades() {
        return Cidades;
    }

    public void setCidades(String cidades) {
        Cidades = cidades;
    }

    public Double getProxima_estimativa() {
        return proxima_estimativa;
    }

    public void setProxima_estimativa(Double proxima_estimativa) {
        this.proxima_estimativa = proxima_estimativa;
    }

    public Date getProxima_data() {
        return proxima_data;
    }

    public void setProxima_data(Date proxima_data) {
        this.proxima_data = proxima_data;
    }

    @Override
    public String toString() {
        return "MegaSena{" +
                "numero=" + numero +
                ", data=" + data +
                ", sorteio=" + Arrays.toString(sorteio) +
                ", ganhadores=" + Arrays.toString(ganhadores) +
                ", rateio=" + Arrays.toString(rateio) +
                ", acumulado=" + acumulado +
                ", valor acumulado=" + valor_acumulado +
                ", Cidades='" + Cidades + '\'' +
                ", proxima estimativa=" + proxima_estimativa +
                ", proxima data=" + proxima_data +
                '}';
    }
}
