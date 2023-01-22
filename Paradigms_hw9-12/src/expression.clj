;(defn operation [oper left right] (fn [xyz] (oper (left xyz) (right xyz))))
;
;(defn add [left right] (operation + left right))
;
;(defn subtract [left right] (operation - left right))
;
;(defn multiply [left right] (operation * left right))
;
;(defn divide [left right] (operation (fn [left right] (/ (double left) (double right))) left right))
;
;(defn constant [value] (fn [xyz] value))
;
;(defn negate [value] (fn [xyz] (* (value xyz) -1)))
;
;(defn variable [arg] (fn [xyz] (get xyz arg)))
;
;(def tokenToOperation {'+ add '- subtract '* multiply '/ divide 'negate negate})
;
;(defn parseFunction [string] (letfn [(parseExpr [token] (cond
;                                                          (symbol? token) (variable (str token))
;                                                          (number? token) (constant token)
;                                                          :hui (cond (= (tokenToOperation (nth token 0)) negate)
;                                                                      (negate (parseExpr (nth token 1)))
;                                                                      :hui
;                                                                      ((tokenToOperation (nth token 0))
;                                                                       (parseExpr (nth token 1))
;                                                                       (parseExpr (nth token 2)))
;                                                                      )
;                                                          )
;                                      )]  (parseExpr (read-string string))
;                               )
;  )
;
;
;(definterface Operative
;  (evaluate [vars])
;  (^String toString [])
;  (diff [x])
;  )
;
;
;(deftype Operation [evaluate toString diff left right]
;  Operative
;  (evaluate [this xyz] (evaluate (.evaluate left xyz) (.evaluate right xyz)))
;  (toString [this] (str "(" toString " " (.toString left) " " (.toString right) ")"))
;  (diff [this x] (diff x left right))
;  )
;
;(deftype UnarOperation [evaluate toString diff value]
;  Operative
;  (evaluate [this xyz] (evaluate (.evaluate value xyz)))
;  (toString [this] (str "(negate " (.toString value) ")"))
;  (diff [this x] (diff (.diff value x)))
;  )
;
;(deftype Element [eval toString diff elem]
;  Operative
;  (evaluate [this vars] (eval vars elem))
;  (toString [this] (toString elem))
;  (diff [this x] (diff elem x))
;  )
;
;(defn buildOperation [evaluate toString diff]
;  (fn [left right] (Operation. evaluate toString diff left right))
;  )
;
;(defn buildElement [eval toString diff]
;  (fn [elem] (Element. eval toString diff elem))
;  )
;
;(defn buildUnarOperation [evaluate toString diff]
;  (fn [value] (UnarOperation. evaluate toString diff value)))
;
;(declare Constant)
;
;(declare ZERO)
;
;(declare ONE)
;
;(def Constant
;  (buildElement
;    (fn [xyz value] value)
;    (fn [value] (str value))
;    (fn [value x] ZERO))
;  )
;(def ZERO (Constant 0))
;
;(declare Variable)
;
;(def Variable
;  (buildElement
;    (fn [xyz x] (xyz x))
;    (fn [x] (str x))
;    (fn [x y] (cond (= x y) ONE :else ZERO)))
;  )
;(def ONE (Constant 1))
;
;(declare Subtract)
;
;(def Subtract
;  (buildOperation - "-" (fn [x left right] (Subtract (.diff left x) (.diff right x)))))
;
;(declare Add)
;
;(def Add
;  (buildOperation + "+" (fn [x left right] (Add (.diff left x) (.diff right x)))))
;
;
;(declare Multiply)
;
;(def Multiply
;  (buildOperation * "*" (fn [x left right] (Add (Multiply (.diff left x) right) (Multiply left (.diff right x))))))
;
;(declare Divide)
;
;(def Divide
;  (buildOperation (fn [left right] (/ (double left) (double right))) "/" (fn [x left right] (Divide
;                                           (Subtract (Multiply (.diff left x) right) (Multiply left (.diff right x)))
;                                           (Multiply right right))))
;  )
;
;(declare Negate)
;
;(def Negate
;  (buildUnarOperation (fn [value] (* value -1)) "negate" (fn [value] (Negate value))))
;
;(def tokenToObjOperation {'+ Add '- Subtract '* Multiply '/ Divide, 'negate Negate})
;
;(defn parseObject [string] (letfn [(parseExpr [token] (cond (symbol? token) (Variable (str token))
;                                                            (number? token) (Constant token)
;
;                                                            :else (cond (= (tokenToObjOperation (nth token 0)) Negate)
;                                                                        (Negate (parseExpr (nth token 1)))
;                                                                        :else
;                                                                        ((tokenToObjOperation (nth token 0))
;                                                                         (parseExpr (nth token 1))
;                                                                         (parseExpr (nth token 2)))
;                                                                        )
;                                                            )
;                                     )]  (parseExpr (read-string string))
;                                         )
;  )
;
;(defn evaluate [expression xyz] (.evaluate expression xyz))
;
;(defn toString [expression] (.toString expression))
;
;(defn diff [expression x] (.diff expression x))
