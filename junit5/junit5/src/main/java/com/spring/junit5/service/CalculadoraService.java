package com.spring.junit5.service;

import com.spring.junit5.enums.Operacao;
import com.spring.junit5.exception.ErroAoCalcularException;
import com.spring.junit5.model.HistoricoOperacaoModel;
import com.spring.junit5.repository.HistoricoOperacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CalculadoraService {

    private final HistoricoOperacaoRepository historicoOperacaoRepository;

    public CalculadoraService(HistoricoOperacaoRepository historicoOperacaoRepository) {
        this.historicoOperacaoRepository = historicoOperacaoRepository;
    }


    public double somar(Double a, Double b) {
        double resultado = a + b;

        salvarHistorico(
                Operacao.SOMAR,
                "Soma: " + a + " + " + b,
                resultado
        );

        return resultado;
    }

    public double subtrair(Double a, Double b) {
        double resultado = a - b;

        salvarHistorico(
                Operacao.SUBTRACAO,
                "Subtração: " + a + " - " + b,
                resultado
        );

        return resultado;
    }

    public double multiplicar(Double a, Double b) {
        double resultado = a * b;

        salvarHistorico(
                Operacao.MULTIPLICACAO,
                "Multiplicação: " + a + " * " + b,
                resultado
        );

        return resultado;
    }

    public double dividir(Double a, Double b) {
        if (b == 0) {
            throw new ErroAoCalcularException(
                    "Não é possível dividir por zero."
            );
        }

        double resultado = a / b;

        salvarHistorico(
                Operacao.DIVISAO,
                "Divisão: " + a + " / " + b,
                resultado
        );

        return resultado;
    }


    public double raizQuadrada(Double valor) {

        if (valor < 0) {
            throw new ErroAoCalcularException(
                    "Não existe raiz quadrada real de número negativo."
            );
        }

        double resultado = Math.sqrt(valor);

        salvarHistorico(
                Operacao.RAIZ_QUADRADA,
                "Raiz quadrada de " + valor,
                resultado
        );

        return resultado;
    }

    public double potencia(Double a, Double b) {

        double resultado = Math.pow(a, b);

        salvarHistorico(
                Operacao.POTENCIA,
                "Potência: " + a + " ^ " + b,
                resultado
        );

        return resultado;
    }


    public double porcentagemDe(Double percentual, Double valor) {

        double resultado = (percentual / 100.0) * valor;

        salvarHistorico(
                Operacao.PORCENTAGEM,
                percentual + "% de " + valor,
                resultado
        );

        return resultado;
    }

    public double percentualQueRepresenta(Double parte, Double total) {

        if (total == 0) {
            throw new ErroAoCalcularException(
                    "Não é possível dividir por zero."
            );
        }

        double resultado = (parte / total) * 100.0;

        salvarHistorico(
                Operacao.PORCENTAGEM,
                parte + " representa quanto % de " + total,
                resultado
        );

        return resultado;
    }

    public double aumentarPorcentagem(Double percentual, Double valor) {

        double resultado = valor + (valor * percentual / 100.0);

        salvarHistorico(
                Operacao.PORCENTAGEM,
                "Aumentar " + valor + " em " + percentual + "%",
                resultado
        );

        return resultado;
    }

    public double diminuirPorcentagem(Double percentual, Double valor) {

        double resultado = valor - (valor * percentual / 100.0);

        salvarHistorico(
                Operacao.PORCENTAGEM,
                "Diminuir " + valor + " em " + percentual + "%",
                resultado
        );

        return resultado;
    }


    private void salvarHistorico(Operacao operacao, String calculo, Double resultado) {

        HistoricoOperacaoModel historico = new HistoricoOperacaoModel();
        historico.setOperacao(operacao);
        historico.setCalculo(calculo);
        historico.setResultado(resultado);

        historicoOperacaoRepository.save(historico);
    }
}