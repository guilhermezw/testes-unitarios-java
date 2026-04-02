package com.spring.teste_unitarios.model;

import com.spring.teste_unitarios.exception.ReservaInvalidaException;

public class Reserva {

    private Cliente cliente;
    private Carro carro;
    private int dias;

    public Reserva() {
    }

    public Reserva(Cliente cliente, Carro carro, int dias) {
        this.cliente = cliente;
        this.carro = carro;
        this.dias = dias;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void verificarNumeroDias(int dias){
        if(dias < 1){
            throw new ReservaInvalidaException("O número da reserva menor que 1");
        }

    }
}
