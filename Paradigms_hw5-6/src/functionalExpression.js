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


let operation = (f, a, b)  => (...args) =>  f(a(...args), b(...args));

let cnst = num => (...args) => num;

let variable = (x) => (...args) => args[xyz.get(x)];

let add = (a, b) => operation((a, b) => a + b, a, b);

let subtract = (a, b) => operation((a, b) => a - b, a, b);

let divide = (a, b) => operation((a, b) => a / b, a, b);

let multiply = (a, b) => operation((a, b) => a * b, a, b);

let negate = (a) => operation((a, b) => a * -1, a, a);


operators.set("+", add);
operators.set("-", subtract);
operators.set("/", divide);
operators.set("*", multiply);
operators.set("n", negate);


let parse = (string) => (...args) => {
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
                stack.push(cnst(Number(num)))
                num = ""
                continue
            }
            if (string[i] === "n") {
                stack.push(operators.get(string[i])(stack.pop()))
                i += 5
                continue
            }
            if (typeof xyz.get(string[i]) !== "undefined") {
                stack.push(variable(string[i]))
                continue
            }
            if (typeof operators.get(string[i]) !== "undefined") {
                let oper = stack.pop()
                let poper = stack.pop()
                stack.push(operators.get(string[i])(poper, oper))
                //console.log(operators.get(string[i])(poper, oper)(...args))
            }
        }
        return stack.pop()(...args)
    }

//console.log(parse("x x 2 - * x * 1 +")(5, 0, 0)) // (x * (x - 2)) * x + 1    (5 * (5 - 2)) * 5 + 1 = 76

//console.log(parse("-314 negate -819 x - *")(0.18171603944321268000,0.36963948609114660000,0.79070469879362450000))

//console.log(parse("-12 111 - z +")(0.19335406996929216000,0.30577974830501065000,0.69141140763664410000))