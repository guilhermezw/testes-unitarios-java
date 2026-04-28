package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {


    @Test
    @DisplayName("Deve calcular a nota do aluno")
    void deveCalcularMedia(){
        Aluno aluno = new Aluno("Leticia" , 15);
        double media = aluno.calcularMedia(7 , 8);
        Assertions.assertEquals(7.5 , media);
    }

    @Test
    @DisplayName("Deve verificar media do aluno")
    void deveVerificarMedia(){
        Aluno aluno = new Aluno("Davi" , 16);
        double media  = aluno.calcularMedia(8 , 9);
        String resultado = aluno.verificarMedia(media);
        Assertions.assertEquals("Aprovado" , resultado);
    }

    @Test
    @DisplayName("Deve criar um aluno")
    void deveCriarAluno(){
        Aluno aluno = new Aluno("Lucas" , 15);
        assertNotNull(aluno);
    }
}