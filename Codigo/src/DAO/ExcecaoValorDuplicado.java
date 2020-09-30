<<<<<<< HEAD
package DAO;

public class ExcecaoValorDuplicado extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoValorDuplicado(String tipo, float cpfCnpj) {
		super (tipo + cpfCnpj + " ja esta cadastrado!");
	}

}
=======
package DAO;

public class ExcecaoValorDuplicado extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoValorDuplicado(String tipo, float cpfCnpj) {
		super (tipo + cpfCnpj + " ja esta cadastrado!");
	}

}
>>>>>>> parent of e9ec400... DAO Produtos
