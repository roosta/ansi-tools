(ns nansi.html
  (:require [clojure.string :as s]
            [hiccup.core :refer [html]]))

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

(defn output
  [input]
  )
