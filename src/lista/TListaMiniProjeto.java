package lista;

public interface TListaMiniProjeto {

	/**
	 * esse m�todo insere a entrada na lista. No caso de listas ordenadas, a inser��o
	 * acontece na posi��o adequada. No caso de listas desordenadas, a inser��o acontece 
	 * no final da lista.
	 * @param s valor a ser guardado na lista
	 * @throws Exception n�o deve ser poss�vel inserir valores repetidos dentro da lista
	 */
	public void add(String s) throws Exception;
	
	/**
	 * esse m�todo insere o valor fornecido no �ndice recebido por par�metro, no caso de 
	 * 		listas ordenadas, o m�todo lan�a uma UnsupportedOperationException
	 * @param s valor a ser guarddo na lista
	 * @param i �ndice onde o elemento ser� inserido
	 * @throws Exception o m�todo lan�a uma exce��o caso n�o seja poss�vel inserir 
	 * 		na posi��o recebida por par�metro
	 */
	public void add(String s, int i) throws Exception;
	
	/**
	 * o m�todo remove o elemento que est� guardado no �ndice passado por par�metro (come�ando de 0)
	 * @param i �ndice do qual o elemento ser� removido
	 * @return retorna o elemento que foi removido da lista
	 * @throws Exception o m�todo lan�a uma exce��o caso o �ndice passado n�o exista na lista atualmente
	 */
	public String removeIndex(int i) throws Exception;
	
	/**
	 * o m�todo remove da lista o elemento que foi passado por par�metro
	 * @param s elemento a ser removido da lista
	 * @throws Exception o m�todo lan�a uma exce��o caso o elemento n�o fa�a parte da lista
	 */
	public void removeElem(String s) throws Exception;
	
	/**
	 * retorna o antecessor de um elemento na lista
	 * @param s elemento que se deseja consultar o antecessor
	 * @return o m�todo retorna o antecessor do elemento passado por par�metro
	 * @throws Exception o m�todo lan�a uma exce��o caso o elemento recebido por par�metro 
	 * n�o fa�a parte da lista ou n�o possua antecessor (seja o primeiro)
	 */
	public String previous(String s) throws Exception;
	
	/**
	 * o m�todo retorna o �ndice do elemento recebido por par�metro dentro da lista (come�ando de 0)
	 * @param s elemento que se deseja consultar o �ndice
	 * @return retorna o �ndice do elemento recebido por par�metro
	 * @throws Exception lan�a uma exce��o caso o elemento recebido n�o fa�a parte da lista
	 */
	public int index(String s) throws Exception;
	
	/**
	 * o m�todo retorna o elemento da lista que est� posicionado no �ndice recebido por par�metro (come�ando de 0)
	 * @param i �ndice cujo elemento se deseja consultar
	 * @return reteorna o elemento da lista que est� posicionado no �ndice recebido
	 * @throws Exception lan�a uma xce��o caso a lista n�o possua esse �ndice
	 */
	public String elemen(int i) throws Exception;

	/**
	 * @return retorna o tamanho atual da lista
	 */
	public int size();
	
	/**
	 * @return retorna o primeiro elemento da lista (null caso a lista esteja vazia)
	 */
	public String first();

	/**
	 * @return retorna o �ltimo elemento da lista (null caso a lista esteja vazia)
	 */
	public String last();
	
	/**
	 * @return retorna o maior elemento da lista
	 */
	public String maior();

	/**
	 * imprime no console a lista dos elementos, seguindo o seguinte padr�o:
	 * (lista com elementos) LISTA -> "ABC" -> "BCA" -> NULL
	 * (lista vazia) LISTA -> NULL
	 */
	public void print();
	
}
