class ContaPalavra implements Comparable<ContaPalavra>{
    String palavra;
    Integer vlrAbr;
    public ContaPalavra(String palavra, Integer valor)
    {
        this.palavra = palavra;
        vlrAbr = valor;
    }
    @Override
    public boolean equals(Object object)
    {
        boolean same = false;
        if(object != null && object instanceof ContaPalavra)
        {
            same = this.palavra.equals(((ContaPalavra) object).palavra);
        }
        // same = this.palavra.equals(palavra);
        return same;
    }
    public void setPalavra(String palavra)
    {
        this.palavra = palavra;
    }
    public void setValor(Integer valor)
    {
        this.vlrAbr = valor;
    }
    public String getPalavra()
    {
        return this.palavra;
    }
    public Integer getValor()
    {
        return this.vlrAbr;
    }
    @Override
    public int compareTo(ContaPalavra o) {
        return this.getValor() - o.getValor();
    }
}