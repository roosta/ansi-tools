(ns nansi.core
  (:require [clojure.string :as s])
  (:gen-class))

(def symbols
  {"┌" "&#9484;"
   "┐" "&#9488;"
   "└" "&#9492;"
   "┘" "&#9496;"
   "│" "&#9474;"
   "─" "&#9472;"
   "░" "&#9617;"
   "█" "&#9608;"
   "▀" "&#9600;"
   "▄" "&#9604;"})

(def pairs
  {"├" "┤"
   "┤" "├"
   "┌" "┐"
   "┐" "┌"
   "└" "┘"
   "┘" "└"})

(def regex
  #"[├┘└┐┌┤]")

;; (conj "zzzz" "a")
(defn replace-char
  []
  (let [ascii (s/split-lines (slurp "partial.txt"))]
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
    (take padding-length (repeatedly (fn [] " ")))))

(def padding (get-padding))

(defn join-line-padding
  []
  (map-indexed
   (fn [idx line]
     (conj (nth padding idx) line))
   lines)
  )

;; (println (join-line-padding))
;; (println lines)

(doseq [l (join-line-padding)]
  (println l))
;; (println (join-line-padding))

(defn -main
  [& args]
  (println "Hi!"))
