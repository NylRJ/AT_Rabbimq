package com.i9developement.classroom.entity.valueObject;

public enum Presence {
	PRESENT(1,"P"), ABSENT(2, "F"), LATE(3,"A");
	
	
		private int codigo;
		private String descricao;
		
		private Presence(int codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}

		public int getCodigo() {
			return codigo;
		}

		public String getDescricao() {
			return descricao;
		}	
		
		public static  Presence toEnum(Integer codigo) {
			if (codigo == null) {
				return null;
			}
			
			for(Presence x : Presence.values()) {
				if(codigo.equals(x.getCodigo())) {
					return x;
				}
			}
			throw new IllegalArgumentException("Id Inválido" + codigo);
		}
	
		
}
