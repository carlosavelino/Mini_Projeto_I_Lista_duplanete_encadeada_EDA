package lista;

public class ListaDuplamenteEncadeada implements TListaMiniProjeto {

	private Node initialNode; // apontador para o primeiro elemento da lista

	@Override

	/**
	 * esse m�todo insere a entrada na lista. No caso de listas ordenadas, a
	 * inser��o acontece na posi��o adequada. No caso de listas desordenadas, a
	 * inser��o acontece no final da lista.
	 * 
	 * @param s valor a ser guardado na lista
	 * @throws Exception n�o deve ser poss�vel inserir valores repetidos dentro da
	 *                   lista
	 */
	public void add(String s) throws Exception {
		Node node = new Node(s);

		if (size() == 0) { // Insers�o no come�o
			initialNode = node;
			node.setPreviousNode(null);

		} else { // Insers�o nas demais posi��es
			Node aux = initialNode;

			if (size() == 1 && aux.getDado().equals(s)) { // Valida insers�o na segunda posi��o
				throw new Exception("Elemento j� est� presente na lista!");

			} else {
				while (true) {

					if (aux.getDado().equals(s))
						throw new Exception("Elemento j� est� presente na lista!");

					if (aux.getNextNode() == null)
						break;
					aux = aux.getNextNode();
				}
			}

			aux.setNextNode(node);
			node.setPreviousNode(aux);
		}
	}

	/**
	 * esse m�todo insere o valor fornecido no �ndice recebido por par�metro, no
	 * caso de listas ordenadas, o m�todo lan�a uma UnsupportedOperationException
	 * 
	 * @param s valor a ser guarddo na lista
	 * @param i �ndice onde o elemento ser� inserido
	 * @throws Exception o m�todo lan�a uma exce��o caso n�o seja poss�vel inserir
	 *                   na posi��o recebida por par�metro
	 */
	@Override
	public void add(String s, int i) throws Exception {
		int cont = 0;
		Node noParaInserir = new Node(s);
		Node noAtual = initialNode;

		if (i == 0) {
			noAtual.setPreviousNode(noParaInserir);
			noParaInserir.setNextNode(noAtual);
			initialNode = noParaInserir;
		} else {
			while (cont < i) {
				if (noAtual.getNextNode() == null) {
					if (i - 1 == cont) {
						noAtual.setNextNode(noParaInserir);
						noParaInserir.setPreviousNode(noAtual);
					} else {
						throw new Exception("N�o pode inserir na posi��o");
					}
					return;
				} else {
					noAtual = noAtual.getNextNode();
					cont++;
				}
			}
			noParaInserir.setPreviousNode(noAtual.getPreviousNode());
			noParaInserir.setNextNode(noAtual);
			noAtual.setPreviousNode(noParaInserir);
			noParaInserir.getPreviousNode().setNextNode(noParaInserir);
		}
	}

	// m�todo para excluir node
	private void excluirNode(Node node) {
		if (node == initialNode) { // Se o elemento a ser removido for o inicial
			initialNode = initialNode.getNextNode();
			if (initialNode != null)
				initialNode.setPreviousNode(null);

		} else if (node.getNextNode() == null) { // Se o elemento a ser removido for o �ltimo
			node.getPreviousNode().setNextNode(null);

		} else { // Se o elemento a ser removido for um intermedi�rio
			node.getNextNode().setPreviousNode(node.getPreviousNode());
			node.getPreviousNode().setNextNode(node.getNextNode());
		}

		// Para concluir a remo��o (tanto do primeiro, ultimo ou intermedi�rio) do
		// elemento,
		// � conveniente fazer seus apontadores apontar para null.
		node.setPreviousNode(null); // O elemento a ser removido perde a refer�ncia para seu antecessor
		node.setNextNode(null); // A refer�ncia do auxiliar para seu pr�ximo deve ser perdida
	}

	/**
	 * o m�todo remove o elemento que est� guardado no �ndice passado por par�metro
	 * (come�ando de 0)
	 * 
	 * @param i �ndice do qual o elemento ser� removido
	 * @return retorna o elemento que foi removido da lista
	 * @throws Exception o m�todo lan�a uma exce��o caso o �ndice passado n�o exista
	 *                   na lista atualmente
	 */
	@Override
	public String removeIndex(int i) throws Exception {
		if (i >= size() || i < 0)
			throw new Exception("Essa lista n�o tem esse �ndice");

		Node aux = initialNode;
		for (int j = 0; j <= i; j++) {
			if (j == i) {
				excluirNode(aux);
				return aux.getDado();
			}
			aux = aux.getNextNode();
		}
		return null;
	}

	/**
	 * o m�todo remove da lista o elemento que foi passado por par�metro
	 * 
	 * @param s elemento a ser removido da lista
	 * @throws Exception o m�todo lan�a uma exce��o caso o elemento n�o fa�a parte
	 *                   da lista
	 */
	@Override
	public void removeElem(String s) throws Exception {
		validarSize();

		Node aux = initialNode;
		while (aux != null) {
			if (aux.getDado().equals(s)) {
				excluirNode(aux);
				return;
			}
			aux = aux.getNextNode();
		}
		// Se a execu��o chegou at� aqui, o elemento especificado n�o foi encontrado
		throw new Exception("O Elemento n�o foi encontrado na lista");
	}

	/**
	 * retorna o antecessor de um elemento na lista
	 * 
	 * @param s elemento que se deseja consultar o antecessor
	 * @return o m�todo retorna o antecessor do elemento passado por par�metro
	 * @throws Exception o m�todo lan�a uma exce��o caso o elemento recebido por
	 *                   par�metro n�o fa�a parte da lista ou n�o possua antecessor
	 *                   (seja o primeiro)
	 */
	@Override
	public String previous(String s) throws Exception {
		validarSize();

		Node aux = initialNode;

		while (aux != null) {
			if (aux.getDado().equals(s)) {
				if (aux == initialNode)
					throw new Exception(
							"Esse � o primeiro elemento da lista, desta forma ele n�o possui um elemento antecessor.");
				return aux.getPreviousNode().getDado();
			}
			aux = aux.getNextNode();
		}
		// Se a execu��o chegou at� aqui, o elemento especificado n�o foi encontrado
		throw new Exception("Elemento n�o foi encontrado na lista");
	}

	// util para os pr�ximos m�todos
	private void validarSize() throws Exception {
		if (size() <= 0)
			throw new Exception("Essa lista n�o possui nenhum elemento");
	}

	/**
	 * o m�todo retorna o �ndice do elemento recebido por par�metro dentro da lista
	 * (come�ando de 0)
	 * 
	 * @param s elemento que se deseja consultar o �ndice
	 * @return retorna o �ndice do elemento recebido por par�metro
	 * @throws Exception lan�a uma exce��o caso o elemento recebido n�o fa�a parte
	 *                   da lista
	 */
	@Override
	public int index(String s) throws Exception {
		validarSize();
		Node aux = initialNode;
		for (int i = 0; i < size(); i++) {
			if (aux.getDado().equals(s))
				return i;
			aux = aux.getNextNode();
		}
		throw new Exception("Erro - Elemento n�o encontrado na lista.");

	}

	/**
	 * o m�todo retorna o elemento da lista que est� posicionado no �ndice recebido
	 * por par�metro (come�ando de 0)
	 * 
	 * @param i �ndice cujo elemento se deseja consultar
	 * @return reteorna o elemento da lista que est� posicionado no �ndice recebido
	 * @throws Exception lan�a uma xce��o caso a lista n�o possua esse �ndice
	 */
	@Override
	public String elemen(int i) throws Exception {
		if (i >= size() || i < 0) {
			throw new Exception("Essa lista n�o possui este �ndice.");
		}

		Node aux = initialNode;
		int contador = 0;
		while (aux != null) {
			if (contador++ == i)
				return aux.getDado();
			aux = aux.getNextNode();
		}
		return null;
	}

	/**
	 * @return retorna o tamanho atual da lista
	 */
	@Override
	public int size() {
		int size = 0;

		Node aux = initialNode;
		while (aux != null) {
			size++;
			aux = aux.getNextNode();
		}

		return size;
	}

	/**
	 * @return retorna o primeiro elemento da lista (null caso a lista esteja vazia)
	 */
	@Override
	public String first() {
		return size() != 0 ? initialNode.getDado() : null;
	}

	/**
	 * @return retorna o �ltimo elemento da lista (null caso a lista esteja vazia)
	 */
	@Override
	public String last() {
		if (initialNode == null) {
			return null;
		} else {

			Node aux = initialNode; // Node auxiliar para iterar sobre a lista
			while (aux.getNextNode() != null) { // Parar� no �ltimo node da lista
				aux = aux.getNextNode();
			}
			// Retornar� o dado do �ltimo elemento da lista
			return aux.getDado();
		}
	}

	/**
	 * @return retorna o maior elemento da lista
	 */
	@Override
	public String maior() {
		String maiorElemento = initialNode.getDado();
		Node noAtual = initialNode;
		while (noAtual.getNextNode() != null) {
			noAtual = noAtual.getNextNode();
			if (noAtual.getDado().compareTo(maiorElemento) > 0) {
				maiorElemento = noAtual.getDado();
			}
		}
		return maiorElemento;
	}

	/**
	 * imprime no console a lista dos elementos, seguindo o seguinte padr�o: (lista
	 * com elementos) LISTA -> "ABC" -> "BCA" -> NULL (lista vazia) LISTA -> NULL
	 */
	@Override
	public void print() {

		System.out.print("LISTA: NULL <- ");

		Node aux = initialNode; // N� auxiliar para iterar sobre a lista
		while (aux.getNextNode() != null) {
			System.out.print(aux.getDado() + " <=> ");
			aux = aux.getNextNode();
		}
		System.out.println(aux.getDado() + " -> NULL ");
		System.out.println("NULL");
	}

}
