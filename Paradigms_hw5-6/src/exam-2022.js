"use strict";

let WHITE_FIELD = -1;
let BLACK_FIELD = 1;

let WHITE_CHECK = -2;
let BLACK_CHECK = 2;

let CELL = new Map();
CELL.set(WHITE_CHECK, "W");
CELL.set(BLACK_CHECK, "B");
CELL.set(WHITE_FIELD, ".");
CELL.set(BLACK_FIELD, "#");

let LOSE = -3;
let WIN = 3;
let DRAW = -4;
let UNKNOWN = 0;

function Position(board) {
    this.board = board;
}

Position.prototype.getTurn = function () {
    return this.board.getTurn();
}

Position.prototype.getPosition = function (row, column) {
    return this.board.getCell(row, column);
}
Position.prototype.toString = function () {
    return this.board.toString();
}
Position.prototype.isCorrect = function (move) {
    return true;
}
function Move(startRow, startColumn, endRow, endColumn) {
    this.startRow = startRow;
    this.startColumn = startColumn;
    this.endRow = endRow;
    this.endColumn = endColumn;
}
Move.prototype.toString = function () {
    return (this.startRow + 1) + " " + (this.startColumn + 1) + " " + (this.endRow + 1) + " " + (this.endColumn + 1);
}

function HumanPlayer() {
}
HumanPlayer.prototype.skipSpaces = function (string, i) {
    while (i < string.length && string[i] === " ") {
        ++i;
    }
    return i;
}
HumanPlayer.prototype.skipNum = function (string, i) {
    while (i < string.length && '0' <= string[i] && string[i] <= '9') {
        ++i;
    }
    return i;
}
HumanPlayer.prototype.getNum = function (string, i) {
    let num = "";
    while (i < string.length && '0' <= string[i] && string[i] <= '9') {
        num += string[i];
        ++i;
    }
    return parseInt(num, 10);
}
HumanPlayer.prototype.parseCoordinates = function (coordinates) {
    let i = 0;
    i = HumanPlayer.prototype.skipSpaces(coordinates, i);

    let startRow = HumanPlayer.prototype.getNum(coordinates, i);
    i = HumanPlayer.prototype.skipNum();
    i = HumanPlayer.prototype.skipSpaces(coordinates, i);

    let startColumn = HumanPlayer.prototype.getNum(coordinates, i);
    i = HumanPlayer.prototype.skipNum();
    i = HumanPlayer.prototype.skipSpaces(coordinates, i);

    let endRow = HumanPlayer.prototype.getNum(coordinates, i);
    i = HumanPlayer.prototype.skipNum();
    i = HumanPlayer.prototype.skipSpaces(coordinates, i);

    let endColumn = HumanPlayer.prototype.getNum(coordinates, i);

    return new Move(startRow, startColumn, endRow, endColumn);
}

HumanPlayer.prototype.move = function (position) {
    println("Position");
    println(position.toString());
    println(position.getTurn() + "'s move");
    println("Enter:");
    while (true) {
        let coordinates = readLine("");
        let move = HumanPlayer.prototype.parseCoordinates(coordinates);
        if (!position.isCorrect(move)) {
            println("invalid coordinates");
            continue;
        }
        return move;
    }
}

function CheckBoard(turn) {
    this.cntWhiteChecks = 12;
    this.cntBlackChecks = 12;
    this.turn = turn;
    this.position = new Position(this);
    this.field = new Array(8);
    for (let i = 0; i < 8; ++i) {
        this.field[i] = new Array(8);
    }
    CheckBoard.prototype.setStart(this.field);
}

CheckBoard.prototype.setStart = function (field) {
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; ++j) {
            if ((i + j) % 2 === 0) {
                field[i][j] = WHITE_FIELD;
            } else {
                if (i <= 2) {
                    field[i][j] = BLACK_CHECK;
                } else if (i >= 5) {
                    field[i][j] = WHITE_CHECK;
                } else {
                    field[i][j] = BLACK_FIELD;
                }
            }
        }
    }
}

CheckBoard.prototype.toString = function () {
    print(" ")
    for (let i = 0; i < 8; ++i) {
        print(" " + (i + 1));
    }
    print("\n");
    for (let i = 0; i < 8; ++i) {
        print(i + 1 + " ");
        for (let j = 0; j < 8; ++j) {
            print(CELL.get(this.field[i][j]) + " ");
        }
        println("")
    }
}

CheckBoard.prototype.getPosition = function () {
    return this.position;
}

CheckBoard.prototype.getCell = function (row, column) {
    return this.field[row][column];
}

CheckBoard.prototype.getTurn = function () {
    return this.turn;
}

CheckBoard.prototype.isMovable = function (row, column, color) {
    if (color === WHITE_CHECK) {
        if (row - 1 > 0 && column - 1 > 0) {
            if (CheckBoard.prototype.getCell(row - 1, column - 1) === BLACK_FIELD) {
                return true;
            } else if (row - 2 > 0 && column - 2 > 0
                && CheckBoard.prototype.getCell(row - 2, column - 2) === BLACK_FIELD
                && CheckBoard.prototype.getCell(row - 1, column - 1) === BLACK_CHECK) {
                return true;
            }
        } else if (row - 1 > 0 && column + 1 < 8) {
            if (CheckBoard.prototype.getCell(row - 1, column + 1) === BLACK_FIELD) {
                return true;
            } else if (row - 2 > 0 && column + 2 < 8
                && CheckBoard.prototype.getCell(row - 2, column + 2) === BLACK_FIELD
                && CheckBoard.prototype.getCell(row - 2, column + 2) === BLACK_CHECK) {
                return true;
            }
        } else if (row + 2 < 8 && column - 2 > 0
            && CheckBoard.prototype.getCell(row + 2, column - 2) === BLACK_FIELD
            && CheckBoard.prototype.getCell(row + 1, column - 1) === BLACK_CHECK) {
            return true;
        } else if (row + 2 < 8 && column + 2 > 0
            && CheckBoard.prototype.getCell(row + 2, column + 2) === BLACK_FIELD
            && CheckBoard.prototype.getCell(row + 1, column + 1) === BLACK_CHECK) {
            return true;
        }
    } else {
        if (row + 1 < 8 && column + 1 < 8) {
            if (CheckBoard.prototype.getCell(row + 1, column + 1) === BLACK_FIELD) {
                return true;
            } else if (row + 2 < 8 && column + 2 < 8
                && CheckBoard.prototype.getCell(row + 2, column + 2) === BLACK_FIELD
                && CheckBoard.prototype.getCell(row + 1, column + 1) === WHITE_CHECK) {
                return true;
            }
        } else if (row + 1 < 8 && column - 1 > 0) {

            if (CheckBoard.prototype.getCell(row + 1, column - 1) === BLACK_FIELD) {
                return true;
            } else if (row + 2 < 8 && column - 2 > 0
                && CheckBoard.prototype.getCell(row + 2, column - 2) === BLACK_FIELD
                && CheckBoard.prototype.getCell(row + 1, column - 1) === WHITE_CHECK) {
                return true;
            }

        } else if (row - 2 > 0 && column - 2 > 0) {
            if (CheckBoard.prototype.getCell(row - 2, column - 2) === BLACK_FIELD
                && CheckBoard.prototype.getCell(row - 1, column - 1) === WHITE_CHECK) {
                return true;
            }
        } else if (row - 2 > 0 && column + 2 < 8) {
            if (CheckBoard.prototype.getCell(row - 2, column + 2) === BLACK_FIELD
                && CheckBoard.prototype.getCell(row - 1, column + 1) === WHITE_CHECK) {
                return true;
            }
        }
    }
    return false;
}

CheckBoard.prototype.checkForDraw = function () {
    let isWhite = true;
    let isBlack = true;
    for (let i = 0; i < 8; ++i) {
        for (let j = 0; j < 8; ++j) {
            if (this.field[i][j] === WHITE_CHECK && CheckBoard.prototype.isMovable(i, j, WHITE_CHECK)) {
                isWhite = true;
            } else if (this.field[i][j] === BLACK_CHECK && CheckBoard.prototype.isMovable(i, j, BLACK_CHECK)) {
                isBlack = true;
            }
        }
    }
    if (CheckBoard.prototype.getTurn() === WHITE_CHECK && !isBlack) {
        return true;
    }
    if (CheckBoard.prototype.getTurn() === BLACK_CHECK && !isWhite) {
        return true;
    }
    return  false;
}

CheckBoard.prototype.makeMove = function (move) {
    if (!CheckBoard.prototype.getPosition().isCorrect(move)) {
        return LOSE;
    }

    if (Math.abs(move.startRow - move.endRow) === 2) {
        this.field[move.startRow][move.startColumn] = BLACK_FIELD;
        if (CheckBoard.prototype.getTurn() === WHITE_CHECK) {
            this.field[move.endRow][move.endColumn] = WHITE_CHECK;
            this.cntBlackChecks--;
        } else {
            this.field[move.endRow][move.endColumn] = BLACK_CHECK;
            this.cntWhiteChecks--;
        }
        let deleteX = (move.startRow + move.endRow) / 2;
        let deleteY = (move.startColumn + move.endColumn) / 2;
        this.field[deleteX][deleteY] = BLACK_FIELD;
    } else {
        this.field[move.startRow][move.startColumn] = BLACK_FIELD;
        if (CheckBoard.prototype.getTurn() === WHITE_CHECK) {
            this.field[move.endRow][move.endColumn] = WHITE_CHECK;
        } else {
            this.field[move.endRow][move.endColumn] = BLACK_CHECK;
        }
    }

    this.turn = this.turn === WHITE_CHECK ? BLACK_CHECK : WHITE_CHECK;

    if (this.cntWhiteChecks === 0 || this.cntBlackChecks === 0) {
        return WIN;
    } else if (CheckBoard.prototype.checkForDraw()) {
        return DRAW;
    } else {
        return UNKNOWN;
    }

}

function Game(player1, player2, log) {
    this.player1 = player1;
    this.player2 = player2;
    this.log = log;
}

Game.prototype.move = function (board, player, numberOfPlayer) {
    let move = player.move(board.getPosition());
    let result = board.makeMove(move);
    if (result === WIN) {
        return numberOfPlayer;
    } else if (result === LOSE) {
        return 3 - numberOfPlayer;
    } else if (result === DRAW) {
        return 0;
    } else {
        return -1;
    }
}

Game.prototype.play = function (board) {
    if (this.player1 === null || this.player2 === null) {
        throw new Error("error: player is null");
    }
    if (board === null) {
        throw new Error("error: board is null");
    }
    while (true) {
        let result = Game.prototype.move(board, this.player1, 1);
        if (result !== -1) {
            return result;
        }
        result = Game.prototype.move(board, this.player2, 2);
        if (result !== -1) {
            return result;
        }
    }
}

let game = new Game(new HumanPlayer(), new HumanPlayer(), true);
game.play(new CheckBoard(WHITE_CHECK));