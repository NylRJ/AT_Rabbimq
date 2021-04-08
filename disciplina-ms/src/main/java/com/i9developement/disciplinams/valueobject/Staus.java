package com.i9developement.disciplinams.valueobject;

public enum Staus {

	FEITO(1, "F"), PENDENTE(2, "P"), ATRASADO(3, "A");

	private int codigo;
	private String descricao;

	private Staus(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Staus toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (Staus x : Staus.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id Inválido" + codigo);
	}

}
