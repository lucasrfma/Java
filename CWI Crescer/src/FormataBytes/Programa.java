package FormataBytes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Programa
{
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entrada;
        BigDecimal valor;
        int escala = 0;

        try {
            entrada = br.readLine();
            valor = new BigDecimal(entrada);
            while( valor.divide(BigDecimal.valueOf(1024)).compareTo(BigDecimal.valueOf(1)) >= 0 && escala < 8)
            {
                valor = valor.divide(BigDecimal.valueOf(1024));
                ++escala;
            }
            System.out.println(valor.setScale(2,RoundingMode.HALF_EVEN) + " " + EscalasByte.values()[escala]);
        } catch (Exception e) {
            System.out.println("Escreva um número inteiro.");
        }
    }

    public enum EscalasByte
    {
         B ("B"),
        KB ("KB"),
        MB ("MB"),
        GB ("GB"),
        TB ("TB"),
        PB ("PB"),
        EB ("EB"),
        zB ("ZB"),
        YB ("YB");

        private String escala;

        private EscalasByte(String s)
        {
            escala = s;
        }
    }
}
