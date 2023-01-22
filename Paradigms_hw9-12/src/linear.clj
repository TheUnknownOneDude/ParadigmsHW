

(defn v+ [left right] {:pre [(vector? left) (vector? right)]} (mapv + left right))

(defn v- [left right] {:pre [(vector? left) (vector? right)]} (mapv - left right))

(defn v* [left right] {:pre [(vector? left) (vector? right)]} (mapv * left right))

(defn vd [left right] {:pre [(vector? left) (vector? right)]} (mapv / left right))

(defn isMatrix? [x] (cond (every? true? (mapv vector? x)) true :else false))

(defn m+ [left right] {:pre [(isMatrix? left) (isMatrix? right)]} (mapv v+ left right))

(defn m- [left right] {:pre [(isMatrix? left) (isMatrix? right)]} (mapv v- left right))

(defn m* [left right] {:pre [(isMatrix? left) (isMatrix? right)]} (mapv v* left right))

(defn md [left right] {:pre [(isMatrix? left) (isMatrix? right)]} (mapv vd left right))


(println (isMatrix? [[1]]))
(println (m+ [[10 20 30] [10 20 30] [10 20 30]] [[10 20 30] [10 20 30] [10 20 30]]))