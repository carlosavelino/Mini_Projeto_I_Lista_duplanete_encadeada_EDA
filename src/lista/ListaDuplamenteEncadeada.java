package lista;

public class ListaDuplamenteEncadeada implements TListaMiniProjeto {

	private Node initialNode; // apontador para o primeiro elemento da lista

	@Override

	/**
	 * esse método insere a entrada na lista. No caso de listas ordenadas, a
	 * inserção acontece na posição adequada. No caso de listas desordenadas, a
	 * inserção acontece no final da lista.
	 * 
	 * @param s valor a ser guardado na lista
	 * @throws Exception não deve ser possível inserir valores repetidos dentro da
	 *                   lista
	 */
	public void add(String s) throws Exception {
		Node node = new Node(s);

		if (size() == 0) { // Insersão no começo
			initialNode = node;
			node.setPreviousNode(null);

		} else { // Insersão nas demais posições
			Node aux = initialNode;

			if (size() == 1 && aux.getDado().equals(s)) { // Valida insersão na segunda posição
				throw new Exception("Elemento já está presente na lista!");

			} else {
				while (true) {

					if (aux.getDado().equals(s))
						throw new Exception("Elemento já está presente na lista!");

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
	 * esse método insere o valor fornecido no índice recebido por parâmetro, no
	 * caso de listas ordenadas, o método lança uma UnsupportedOperationException
	 * 
	 * @param s valor a ser guarddo na lista
	 * @param i índice onde o elemento será inserido
	 * @throws Exception o método lança uma exceção caso não seja possível inserir
	 *                   na posição recebida por parâmetro
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
						throw new Exception("Não pode inserir na posição");
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

	// método para excluir node
	private void excluirNode(Node node) {
		if (node == initialNode) { // Se o elemento a ser removido for o inicial
			initialNode = initialNode.getNextNode();
			if (initialNode != null)
				initialNode.setPreviousNode(null);

		} else if (node.getNextNode() == null) { // Se o elemento a ser removido for o último
			node.getPreviousNode().setNextNode(null);

		} else { // Se o elemento a ser removido for um intermediário
			node.getNextNode().setPreviousNode(node.getPreviousNode());
			node.getPreviousNode().setNextNode(node.getNextNode());
		}

		// Para concluir a remoção (tanto do primeiro, ultimo ou intermediário) do
		// elemento,
		// é conveniente fazer seus apontadores apontar para null.
		node.setPreviousNode(null); // O elemento a ser removido perde a referência para seu antecessor
		node.setNextNode(null); // A referência do auxiliar para seu próximo deve ser perdida
	}

	/**
	 * o método remove o elemento que está guardado no índice passado por parâmetro
	 * (começando de 0)
	 * 
	 * @param i índice do qual o elemento será removido
	 * @return retorna o elemento que foi removido da lista
	 * @throws Exception o método lança uma exceção caso o índice passado não exista
	 *                   na lista atualmente
	 */
	@Override
	public String removeIndex(int i) throws Exception {
		if (i >= size() || i < 0)
			throw new Exception("Essa lista não tem esse índice");

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
	 * o método remove da lista o elemento que foi passado por parâmetro
	 * 
	 * @param s elemento a ser removido da lista
	 * @throws Exception o método lança uma exceção caso o elemento não faça parte
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
		// Se a execução chegou até aqui, o elemento especificado não foi encontrado
		throw new Exception("O Elemento não foi encontrado na lista");
	}

	/**
	 * retorna o antecessor de um elemento na lista
	 * 
	 * @param s elemento que se deseja consultar o antecessor
	 * @return o método retorna o antecessor do elemento passado por parâmetro
	 * @throws Exception o método lança uma exceção caso o elemento recebido por
	 *                   parâmetro não faça parte da lista ou não possua antecessor
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
							"Esse é o primeiro elemento da lista, desta forma ele não possui um elemento antecessor.");
				return aux.getPreviousNode().getDado();
			}
			aux = aux.getNextNode();
		}
		// Se a execução chegou até aqui, o elemento especificado não foi encontrado
		throw new Exception("Elemento não foi encontrado na lista");
	}

	// util para os próximos métodos
	private void validarSize() throws Exception {
		if (size() <= 0)
			throw new Exception("Essa lista não possui nenhum elemento");
	}

	/**
	 * o método retorna o índice do elemento recebido por parâmetro dentro da lista
	 * (começando de 0)
	 * 
	 * @param s elemento que se deseja consultar o índice
	 * @return retorna o índice do elemento recebido por parâmetro
	 * @throws Exception lança uma exceção caso o elemento recebido não faça parte
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
		throw new Exception("Erro - Elemento não encontrado na lista.");

	}

	/**
	 * o método retorna o elemento da lista que está posicionado no índice recebido
	 * por parâmetro (começando de 0)
	 * 
	 * @param i índice cujo elemento se deseja consultar
	 * @return reteorna o elemento da lista que está posicionado no índice recebido
	 * @throws Exception lança uma xceção caso a lista não possua esse índice
	 */
	@Override
	public String elemen(int i) throws Exception {
		if (i >= size() || i < 0) {
			throw new Exception("Essa lista não possui este índice.");
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
	 * @return retorna o último elemento da lista (null caso a lista esteja vazia)
	 */
	@Override
	public String last() {
		if (initialNode == null) {
			return null;
		} else {

			Node aux = initialNode; // Node auxiliar para iterar sobre a lista
			while (aux.getNextNode() != null) { // Parará no último node da lista
				aux = aux.getNextNode();
			}
			// Retornará o dado do último elemento da lista
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
	 * imprime no console a lista dos elementos, seguindo o seguinte padrão: (lista
	 * com elementos) LISTA -> "ABC" -> "BCA" -> NULL (lista vazia) LISTA -> NULL
	 */
	@Override
	public void print() {

		System.out.print("LISTA: NULL <- ");

		Node aux = initialNode; // Nó auxiliar para iterar sobre a lista
		while (aux.getNextNode() != null) {
			System.out.print(aux.getDado() + " <=> ");
			aux = aux.getNextNode();
		}
		System.out.println(aux.getDado() + " -> NULL ");
		System.out.println("NULL");
	}

}
