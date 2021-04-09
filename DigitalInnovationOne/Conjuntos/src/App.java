import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        /**
         * le a primeira linha que indica número de palavras no primeiro conjunto
         */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numeroLido = Integer.parseInt(st.nextToken());
        /**
         * Enquanto uma linha indicadora de tamanho de conjunto não for = 0, o programa continua
         */
        while(numeroLido > 0)
        {
            String[] conjunto = new String[numeroLido];
            for(int i = numeroLido - 1; i>=0; --i)
            {
                /**
                 * Le e armazena cada palavra
                 */
                st = new StringTokenizer(br.readLine());
                conjunto[i] = st.nextToken();
            }

            /**
             * Compara todas as palavras do conjunto com todas as outras
             * Para os loops quando chegar no fim ou quando encontrar uma palavra que
             * é prefixo de outra.
             */
            boolean conjuntoRuim = false;
            for(int i = numeroLido - 1; i>=0 && !conjuntoRuim;--i)
            {
                for(int j = numeroLido - 1; j>=0 && !conjuntoRuim; --j)
                {
                    if( i!= j )
                    {
                        conjuntoRuim = conjunto[j].startsWith(conjunto[i]);
                    }
                }
            }

            if(conjuntoRuim)
            {
                System.out.println("Conjunto Ruim");
            }else
            {
                System.out.println("Conjunto Bom");
            }

            /**
             * Le o número indicador do tamanho do próximo conjunto
             */
            st = new StringTokenizer(br.readLine());
            numeroLido = Integer.parseInt(st.nextToken());
        }
    }
}
