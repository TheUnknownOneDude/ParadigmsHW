(defn AND [left right] (fn [xyz] (and (left xyz) (right xyz))))

(defn OR [left right]  (fn [xyz] (or (left xyz) (right xyz))))

(defn NOT [value] (fn [xyz] (not (value xyz))))

(defn varr [x] (fn [xyz] (xyz x)))

(println (read-string (str "(" "(a & b)|~c" ")")))

(def tokenToBinOperation {'& AND, '| OR, 'clojure.core/unquote NOT})

(defn dfns [string] (letfn [(parseExpr [token] (cond
                                                 (symbol? token) (varr (str token))
                                                 :else (cond (= (tokenToBinOperation (nth token 0)) NOT)
                                                             (NOT (parseExpr (nth token 1)))
                                                             :else
                                                             ((tokenToBinOperation (nth token 0))
                                                              (parseExpr (nth token 1))
                                                              (parseExpr (nth token 2)))
                                                             )
                                                          )
                                       )]  (parseExpr (read-string string))
                                           )
  )

(println (read-string "~(& (& a b) (& c d))"))

(println ((dfns "~ (| (& a b) (& c d))") {"a" true, "b" true, "c" true, "d" false}))
