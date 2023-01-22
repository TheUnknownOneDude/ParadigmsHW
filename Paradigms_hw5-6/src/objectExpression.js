"use strict"

let operators = new Map
let xyz = new Map
let nums = new Map

xyz.set("x", 0)
xyz.set("y", 1)
xyz.set("z", 2)


nums.set("1", 1)
nums.set("2", 2)
nums.set("3", 3)
nums.set("4", 4)
nums.set("5", 5)
nums.set("6", 6)
nums.set("7", 7)
nums.set("8", 8)
nums.set("9", 9)
nums.set("0", 0)
// let cnst = num => (...args) => num;

function Operation(f, left, right, operator) {
    this.f = f
    this.left = left
    this.right = right
    this.operator = operator
}

Operation.prototype.evaluate = function (...args) {
    return this.f(this.left.evaluate(...args), this.right.evaluate(...args))
}

Operation.prototype.toNotString = function () {
    return "(" + this.left.toNotString() + " " + this.operator.toString() + " " + this.right.toNotString() + ")"
}

Operation.prototype.toString = function () {
    return this.left.toString() + " " + this.right.toString() + " " + this.operator
}

Operation.prototype.prefix = function () {
    return this.left.operator + " " + this.right.operator + " " + this.left.prefix() + " "
        + this.right.prefix()

}

function Subtract(left, right) {
    return new Operation((left, right) => left - right, left, right, "-")
}

function Add(left, right) {
    return new Operation((left, right) => left + right, left, right, "+")
}

function Multiply(left, right) {
    return new Operation((left, right) => left * right, left, right, "*")
}

function Divide(left, right) {
    return new Operation((left, right) => left / right, left, right, "/")
}


// let sex = new Subtract(cnst(10), cnst(20))

function Const(value) {
    this.value = value
    this.operator = ""
}

Const.prototype.evaluate = function (...args) {
    return this.value
}

Const.prototype.toNotString = function () {
    return this.value.toString()
}

Const.prototype.toString = function () {
    return this.value.toString()
}

Const.prototype.prefix = function () {
    return this.value.toString()
}

function Variable(x) {
    this.x = x
    this.operator = ""
}

Variable.prototype.evaluate = function (...args) {
    return args[xyz.get(this.x)]
}

Variable.prototype.toNotString = function () {
    return this.x.toString()
}

Variable.prototype.toString = function () {
    return this.x.toString()
}

Variable.prototype.prefix = function () {
    return this.x.toString()
}


function Negate(value) {
    this.value = value
    this.operator = ""
}

Negate.prototype.evaluate = function (...args) {
    return this.value.evaluate(...args) * -1
}

Negate.prototype.toNotString = function () {
    return "negate " + this.value.toString()
}

Negate.prototype.toString = function () {
    return this.value.toString() + " negate"
}

Negate.prototype.prefix = function () {
    return "negate " + this.value.toString()
}


operators.set("+", Add);
operators.set("-", Subtract);
operators.set("/", Divide);
operators.set("*", Multiply);
operators.set("n", Negate);

let parse = (string) => {
    let stack = [];
    let num = ""
        for (let i = 0; i < string.length; i++) {
            if (string[i] === "-" && typeof nums.get(string[i + 1]) !== "undefined") {
                num += "-"
                i++
            }
            if (typeof nums.get(string[i]) !== "undefined") {
                num += string[i]
                i++
                while (typeof nums.get(string[i]) !== "undefined") {
                    num += string[i]
                    i++
                }
                stack.push(new Const(Number(num)))
                num = ""
                continue
            }
            if (string[i] === "n") {
                stack.push(new Negate(stack.pop()))
                i += 5
                continue
            }
            if (typeof xyz.get(string[i]) !== "undefined") {
                stack.push(new Variable(string[i]))
                continue
            }
            if (typeof operators.get(string[i]) !== "undefined") {
                let oper = stack.pop()
                let poper = stack.pop()
                stack.push(operators.get(string[i])(poper, oper))
            }
        }
        return stack.pop()
    }

let parse1 = parse("2 x * 3 -")

console.log(parse1.toNotString())

console.log(parse1.toString())

console.log(parse1.prefix())
