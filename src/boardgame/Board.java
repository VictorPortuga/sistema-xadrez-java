package boardgame;

public class Board {

	private int row;
	private int col;
	private Piece[][] pieces;
	
	public Board(int row, int col) {
		if( row < 1 || col < 1 ) {
			throw new BoardException("Erro ao criar o tabuleiro: necessário que haja pelo menos 1 linha ou 1 coluna ");
		}
		
		this.row = row;
		this.col = col;
		
		pieces = new Piece[row][col];
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public Piece piece(int row, int col) {
		if( !positionExists(row, col) ) {
			throw new BoardException("Não existe essa posição no tabuleiro");
		}
		
		return pieces[row][col];
	}
	public Piece piece(Position position) {
		if( !positionExists(position) ) {
			throw new BoardException("Não existe essa posição no tabuleiro");
		}
		
		return pieces[position.getRow()][position.getCol()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if( thereIsAPiece(position) ) {
			throw new BoardException("Já existe uma peça nessa posição: " + position);
		}
		
		pieces[position.getRow()][position.getCol()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if( !positionExists(position) ) {
			throw new BoardException("Não existe essa posição no tabuleiro");
		}
		if( piece(position) == null ) {
			return null;
		}
		
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getCol()] = null;
		
		return aux;
	}
	
	private boolean positionExists(int row, int col) {
		return row >= 0 && row < this.row && col >= 0 && col < this.col;
	}
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getCol());
	}
	
	public boolean thereIsAPiece(Position position) {
		if( !positionExists(position) ) {
			throw new BoardException("Não existe essa posição no tabuleiro");
		}
		
		return piece(position) != null;
	}
	
}
