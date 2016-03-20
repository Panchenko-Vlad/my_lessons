package ru.fiveInARow;

import ru.fiveInARow.interfaces.*;

/**
 * Базовые действия пользователя
 */
public class BaseAction implements IUserAction {

	private final IGeneratorBoard generator;
	private final IBoard board;
	private final ILogic logic;

	public BaseAction(final ILogic logic, final IBoard board, final IGeneratorBoard generator) {
		this.generator = generator;
		this.board = board;
		this.logic = logic;
	}

	@Override
	public void initGame() {
		final ICell[][] cells = generator.generate();
		this.logic.loadBoard(cells);
		this.logic.paintingCellsInStartGame();
		this.board.drawBoard(cells);
	}

	@Override
	public void select(int x, int y, int x2, int y2) {
		this.logic.movePaintedCell(x, y, x2, y2);
		this.logic.createSmallCells();
		this.logic.clearCells();
		this.logic.createBigCells();
		if (this.logic.finish())
			this.board.drawLosing();
		else
			this.board.drawSelect();
	}
}
