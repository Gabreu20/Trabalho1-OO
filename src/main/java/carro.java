/**
 *
 * @author gabreu e lucas
 */
public class carro {
    String placa;
    String marca;
    String cor;
    float kmAndado;
    String dataRetorno;
    float preco;
    Boolean disponivel;
    
    public carro(String PLACA, String MARCA, String COR, float KMANDADO, String DATARETORNO, float PRECO, Boolean DISPONIVEL){
        placa = PLACA; marca = MARCA; cor = COR; kmAndado = KMANDADO; 
        dataRetorno = DATARETORNO;
        preco = PRECO; disponivel = DISPONIVEL;
    }
    
    public void attPreco(float nPreco){
        preco = nPreco;
    }
    public void attKmAndado(float nKm){
        kmAndado = nKm;
    }
}