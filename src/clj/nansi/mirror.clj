(ns nansi.mirror
  (:require [clojure.string :as s]))

(def pairs
  {"├" "┤"
   "┤" "├"
   "┌" "┐"
   "┐" "┌"
   "└" "┘"
   "┘" "└"})

(def regex
  #"[├┘└┐┌┤]")

(defn replace-char
  []
  (let [ascii (s/split-lines (slurp "resources/partial.txt"))]
    (reduce
     (fn [acc line]
       (let [r (apply str (reverse line))
             c (s/replace r regex #(get pairs %))]
         (conj acc c)))
     '()
     ascii)))

(def lines (reverse (replace-char)))
(def longest-length (-> (sort-by count lines)
                        last
                        count))

(defn get-padding
  []
  (for [line lines
        :let [padding-length (- longest-length (count line))]]
    (apply str (repeat padding-length \space))))

(defn join-line-padding
  []
  (let [padding (get-padding)]
    (map-indexed
     (fn [idx line]
       (str (nth padding idx) line))
     lines)))

(defn output
  []
  (doseq [l (join-line-padding)]
    (println l))
  )
