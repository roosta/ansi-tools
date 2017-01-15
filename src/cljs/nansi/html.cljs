(ns nansi.html
  (:require-macros [cljs.core.async.macros :refer [go go-loop]]
                   [hiccups.core :as hiccups :refer [html]])
  (:require [clojure.string :as s]
            [cljs.core.async :refer [put! alts! chan <! >! timeout close!]]
            [hiccups.runtime :as hiccupsrt]))

(def symbols
  {"┌" "&#9484;"
   "┐" "&#9488;"
   "└" "&#9492;"
   "┘" "&#9496;"
   "│" "&#9474;"
   "─" "&#9472;"
   "┬" "&#9516;"
   "┴" "&#9524;"
   "├" "&#9500;"
   "┤" "&#9508;"
   "┼" "&#9532;"
   "╌" "&#9548;"
   "┆" "&#9478;"
   "░" "&#9617;"
   "█" "&#9608;"
   " " "&nbsp;"
   "▀" "&#9600;"
   "▄" "&#9604;"})

(def regex #"[├┘└┐┌┤│─░█▀▄┴┬╌┼┆ ]")

(defn swap-chars
  [line]
  (for [c line]
    (s/replace c regex #(get symbols %)))
  )

(defn main
  [ch]
  (go (let [lines (s/split-lines (<! ch))]
        (doseq [l lines]
          (println
           (html
            [:span (apply str (swap-chars l))])))
        ))
  #_(doseq [[k v] symbols]
    (println (s/replace input k v))))
