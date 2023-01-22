"use strict"




let whiteBlack = new Map
whiteBlack.set("white", "black")
whiteBlack.set("black", "white")

let stN = new Map

stN.set('A', 0)
stN.set('B', 1)
stN.set('C', 2)
stN.set('D', 3)
stN.set('E', 4)
stN.set('F', 5)
stN.set('G', 6)
stN.set('H', 7)

let board = [];
let whitesAmount = 12
let blackAmount = 12

function refreshBoard() {
    for (let i = 0; i < 8; i++) {
        let row = [];
        for (let j = 0; j < 8; j++) {
            if (i === 0 && j % 2 === 0) {
                row.push("white")
            } else if (i === 1 && j % 2 === 1) {
                row.push("white")
            } else if (i === 2 && j % 2 === 0) {
                row.push("white")
            } else if (i === 5 && j % 2 === 1) {
                row.push("black")
            } else if (i === 6 && j % 2 === 0) {
                row.push("black")
            } else if (i === 7 && j % 2 === 1) {
                row.push("black")
            } else {
                row.push("none");
            }
        }
        board.push(row)
    }
    whitesAmount = 12
    blackAmount = 12
}



function get(move) {
    return board[move[1] - 1][stN.get(move[0])]
}




function makeMove(from, to, player) {
    let name = get(from)


    if (name !== player) {
        console.log("You've chosen the wrong shashka")
        return false
    }

    let next = get(to)

    if (next === "none") {
        board[from[1] - 1][stN.get(from[0])] = "none"
        board[to[1] - 1][stN.get(to[0])] = name
        return true
    }

    if (next === name) {
        console.log("Already a white shashka on this place")
        return false
    }

    if (next !== name) {
        if (name === "white") {
            if (stN.get(from[0]) < stN.get(to[0]) && stN.get(to[0]) < 7) {
                if (board[to[1]][stN.get(to[0]) + 1] === "none") {
                    board[to[1]][stN.get(to[0]) + 1] = "white"
                    board[to[1]][stN.get(to[0])] = "none"
                    board[from[1]][stN.get(from[0])] = "none"
                    blackAmount--
                }
            } else if (stN.get(from[0]) > stN.get(to[0]) && stN.get(to[0]) > 0) {
                if (board[to[1]][stN.get(to[0]) - 1] === "none") {
                    board[to[1]][stN.get(to[0]) - 1] = "white"
                    board[to[1]][stN.get(to[0])] = "none"
                    board[from[1]][stN.get(from[0])] = "none"
                    blackAmount--
                }
            }
        } else if (name === "black") {
            if (stN.get(from[0]) < stN.get(to[0]) && stN.get(to[0]) < 7) {
                if (board[to[1]][stN.get(to[0]) + 1] === "none") {
                    board[to[1]][stN.get(to[0]) + 1] = "black"
                    board[to[1]][stN.get(to[0])] = "none"
                    board[from[1]][stN.get(from[0])] = "none"
                    whitesAmount--
                }
            } else if (stN.get(from[0]) > stN.get(to[0]) && stN.get(to[0]) > 0) {
                if (board[from[1]][stN.get(to[0]) - 1] === "none") {
                    board[from[1]][stN.get(to[0]) - 1] = "black"
                    board[to[1]][stN.get(to[0])] = "none"
                    board[from[1]][stN.get(from[0])] = "none"
                    whitesAmount--
                }
            }
        }
        return true
    }
}


function showBoard() {

    console.log("A B C D E F G H")
    for (let i = 0; i < 8; i++) {
        let str = ""
        for (let j = 0; j < 8; j++) {
            if (board[i][j] === "none")
                str += ("x ")

            else if (board[i][j] === "white")
                str += ("W ")

            else if (board[i][j] === "black")
                str += ("B ")
        }

        str += i + 1

        console.log(str)
    }
}
function startGame() {
    refreshBoard()

    let move = ""
    let turn = "white"

    showBoard()
    while (move !== "end") {
        move = "A1 B2"

        let from = move.substring(0, 2)
        let to = move.substring(3, 5)

        while (true) {
            console.log(turn + " turn is: ")
            move = readLine()

            if (move === "end") break


            from = move.substring(0, 2)
            to = move.substring(3, 5)


            if (makeMove(from, to, turn))
                break

        }

        if (whitesAmount === 0) {
            console.log("Black wins")
            return
        }

        if (blackAmount === 0) {
            console.log("White wins")
            return
        }

        turn = whiteBlack.get(turn)
        showBoard()
    }
}

startGame()