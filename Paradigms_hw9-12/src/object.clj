(definterface Operative
  (evaluate [vars])
  (^String toString [])
  (diff [])
  )


(deftype Operation [evaluate toString diff left right]
  Operative
  (evaluate [this xyz] (evaluate (.evaluate left xyz) (.evaluate right xyz)))
  (toString [this] (str "(" toString " " (.toString left) " " (.toString right) ")"))
  (diff [this] (diff left right))
  )

(deftype UnarOperation [evaluate toString diff value]
  Operative
  (evaluate [this xyz] (evaluate (.evaluate value xyz)))
  (toString [this] (str "negate " (.toString value)))
  (diff [this] (diff value)))

(deftype Element [eval toString diff elem]
  Operative
  (evaluate [this vars] (eval vars elem))
  (toString [this] (toString elem))
  (diff [this] (diff elem))
  )

(defn buildOperation [evaluate toString diff]
  (fn [left right] (Operation. evaluate toString diff left right))
  )

(defn buildElement [eval toString diff]
  (fn [elem] (Element. eval toString diff elem))
  )

(defn buildUnarOperation [evaluate toString diff]
  (fn [value] (UnarOperation. evaluate toString diff value)))

(declare Constant)

(def Constant
  (buildElement
    (fn [xyz value] value)
    (fn [value] (str value))
    (fn [value] 0))
  )

(declare Variable)

(def Variable
  (buildElement
    (fn [xyz x] (xyz x))
    (fn [x] (str x))
    (fn [x] 1))
  )

(declare Subtract)

(def Subtract
  (buildOperation - "-" (fn [left right] (Subtract (.diff left) (.diff right)))))

(declare Add)

(def Add
  (buildOperation + "+" (fn [left right] (Add (.diff left) (.diff right)))))


(declare Multiply)

(def Multiply
  (buildOperation * "*" (fn [left right] (Add (Multiply (.diff left) right) (Multiply left (.diff right))))))

(declare Divide)

(def Divide
  (buildOperation (fn [left right] (/ (double left) (double right))) "/" (fn [left right] (Divide
                                           (Subtract (Multiply (.diff left) right) (Multiply left (.diff right)))
                                                           (Multiply right right))))
  )

(declare Negate)

(def Negate
  (buildUnarOperation (fn [value] (* value -1)) "negate" (fn [value] (Negate value))))

(def tokenToObjOperation {'+ Add '- Subtract '* Multiply '/ Divide})

(defn parseObject [string] (letfn [(parseExpr [token] (cond (symbol? token) (Variable (str token))
                                                                              (number? token) (Constant token)
                                                                              :else ((tokenToObjOperation (nth token 0))
                                                                                     (parseExpr (nth token 1))
                                                                                     (parseExpr (nth token 2)))
                                                                              )
                                       )]  (parseExpr (read-string string))
                                           )
  )

(defn evaluate [expression xyz] (.evaluate expression xyz))

(defn toString [expression] (.toString expression))

(defn diff [expression] (.diff expression))


(println (/ (double 5) (double 0)))

(println (evaluate (Divide (Constant 5) (Variable "x")) {"x" 0}))

;(println (evaluate (Constant 10.0) {"x" 20}))
;(def expre
;  (Subtract
;    (Multiply
;      (Negate (Constant 2))
;      (Variable "x"))
;    (Constant 3)))
;
;(def death (Subtract
;             (Add
;               (Multiply (Constant 0) (Variable "x"))
;               (Multiply (Constant 2) (Constant 1)))
;             (Constant 0)))
;
;(def forDiff (Multiply (Variable "x") (Variable "x")))
;(println (evaluate expre {"x" 20}))
;
;(println (.toString expre))
;
;(println (.toString death))
;
;(println (.toString (.diff expre)))
;
;(println (.toString (parseObject "(- (* 2 x) 3)")))
