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

;; (println (first (s/split (slurp "partial.txt") #"\n")))

(defn replace-char
  []
  (let [ascii (s/split (slurp "partial.txt") #"\n")]
    (for [l ascii
          c l]
      c
      )
    #_(println (ffirst ascii)))
  )

(println (replace-char))

(defn -main
  [& args]
  (println "Hi!"))
