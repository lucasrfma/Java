import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Abreviador {

    public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while(true){
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        ArrayList<String> palavras = new ArrayList<>();
        int numAbreviacoes = 0;
    
        while(st.hasMoreTokens())
        {
            palavras.add(st.nextToken());
        }
    
        // Ultimo caso teste é indicado pelo "." e não deve ser processado.
        if(palavras.get(0).equals("."))
        {
            break;
        }
        // esse array de ArrayLists fará a contagem de palavras.
        // cada array interno representa palavras começadas com uma letra: indice 0 para 'a', índice 25 para 'z'.
        ArrayList<ContaPalavra>[] palavUnicas = new ArrayList[26];
        
        for (int i = 0; i < palavUnicas.length; i++) {
            palavUnicas[i] = new ArrayList<ContaPalavra>();
        }

        // aqui verifica-se cada palavra:
        // length maior que 2 para poder valer a pena abreviar.
        // atribui-se o valor adequado à variável índice a partir da primeira letra da palavra
        // verifica se já existe essa palavra no array interno, se ja houver, incrementa-se o respectivo charAb 
        // se não existir, adiciona-se tanto a palavra ao array palavUnicas qt a valor que seria abreviado com ela ao charAb
        for(String palavra : palavras)
        {
            if(palavra.length()>2)
            {
                int index = palavra.charAt(0)-'a';
                ContaPalavra busca = new ContaPalavra(palavra, 0);
                if(palavUnicas[index].contains(busca))
                {
                    int indexInterno = palavUnicas[index].indexOf(busca);
                    palavUnicas[index].get(indexInterno).setValor(palavra.length()-2+palavUnicas[index].get(indexInterno).getValor());
                }else
                {
                    ContaPalavra novoItem = new ContaPalavra(palavra,palavra.length()-2);
                    palavUnicas[index].add(novoItem);
                }
            }
        }

        /**
         * Criação de lista de abreviaturas
         */
        String[] abreviaturas = new String[26];
        for (int i = 0; i < palavUnicas.length; i++) {
            if( !palavUnicas[i].isEmpty() )
            {
                abreviaturas[i] = Character.toString((char) i+ 'a') + ".";
            }
        }
        /**
         * Ordenação dos arraylists referentes a cada letra inicial em ordem de quantidade de caracteres abreviáveis totais
         * e substituição das palavras escolhidas por suas abreviaturas
         */
        for (int i = 0; i < palavUnicas.length; i++) {
            if(!palavUnicas[i].isEmpty())
            {
                Collections.sort(palavUnicas[i]);
                String aSubstituir = palavUnicas[i].get(palavUnicas[i].size()-1).getPalavra();
                ++numAbreviacoes;
                for (int j = 0; j < palavras.size() ; j++)
                {
                    if(palavras.get(j).equals(aSubstituir)){
                        palavras.set(j,abreviaturas[i]);
                    }
                }
            }
        }
        /**
        * Frase original re-montada
        */
        for (int i = 0; i < palavras.size(); i++) {
            if(i < palavras.size()-1)
            {
                System.out.print(palavras.get(i) + " ");
            }else
            {
                System.out.println(palavras.get(i));
            }
        }
        // "Em seguida, imprima um inteiro N, indicando o número de palavras em que foram escolhidas uma letra para a abreviação no texto."
        System.out.println(numAbreviacoes);
        // "Nas próximas N linhas, imprima o seguinte padrão “C. = P”, onde C é a letra inicial e P é a palavra escolhida para tal letra." 
        for (int i = 0; i < palavUnicas.length; i++) {
            if(!palavUnicas[i].isEmpty()){
                System.out.println(abreviaturas[i] +" = " + palavUnicas[i].get(palavUnicas[i].size()-1).getPalavra());
            }
        }        

    }
    }
}
