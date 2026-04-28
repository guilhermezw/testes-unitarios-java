package org.example;

public class Aluno {

    private String nome;
    private int idade;

    public Aluno() {
    }

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double calcularMedia (double nota1 , double nota2){
        return  (nota1 + nota2) / 2;
    }


    public String verificarMedia (double media){

        String resultado = "";

        if (media >= 7) {
            resultado = "Aprovado";

        } else if (media < 7) {
            resultado = "Reprovado";
        }

        return resultado;
    }
}
