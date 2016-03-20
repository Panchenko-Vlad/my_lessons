package ru.fiveInARow.interfaces;

/**
 * Описывает поведения доски.
 */
public interface IBoard {

	/**
	 * Рисует доску исходя из входящего массива ячеек.
	 * @param cells Массив ячеек.
	 */
	void drawBoard(ICell[][] cells);

	/**
	 * Рисует ячейку.
	 */
	void drawSelect();

	/**
	 * Рисует, когда пользователь сложил пять в ряд
	 */
	void drawCongratulate();

	/**
	 * Рисует проигрыш.
	 */
	void drawLosing();
}
