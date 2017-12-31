package com.linenext.br.conferirloterias;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Paulo Victor on 18/12/2017.
 */

public class MegaSena{


    private int numero;
    private Date data;
    private int sorteio[]=new int[5];
    private int ganhadores[]= new int[5];
    private Double rateio []= new Double[5];

    @SerializedName("acumulado")
    private String acumulado;


    private String  valor_acumulado;

    private String cidades[]= new String[5];

    @SerializedName("proximo_estimativa")
    private BigDecimal proxima_estimativa;

    @SerializedName("proximo_data")
    private Date proxima_data;

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

    public String getAcumulado() { return acumulado; }

    public void setAcumulado(String acumulado) {
        this.acumulado = acumulado;
    }

    public String getValor_acumulado() {
        return valor_acumulado;
    }

    public void setValor_acumulado(String  valor_acumulado) {
        this.valor_acumulado = valor_acumulado;
    }

    public String[] getCidades() {
        return cidades;
    }

    public void setCidades(String[] cidades) {
        this.cidades = cidades;
    }

    public BigDecimal getProxima_estimativa() {
        return proxima_estimativa;
    }

    public void setProxima_estimativa(BigDecimal proxima_estimativa) {
        this.proxima_estimativa = proxima_estimativa;
    }

    public Date getProxima_data() {
        return proxima_data;
    }

    public void setProxima_data(Date proxima_data) {
        this.proxima_data = proxima_data;
    }
}
