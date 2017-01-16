(ns nansi.pain
  (:require-macros
   [hiccups.core :as hiccups :refer [html]]
   [cljs.core.async.macros :refer [go go-loop]])
  (:require
   [clojure.string :as s]
   [hiccups.runtime :as hiccupsrt]
   [cljs.core.async :refer [put! alts! chan <! >! timeout close!]]))

(def symbols
  {"┌" "┌"
   "┐" "┐"
   "└" "└"
   "┘" "┘"
   "│" "│"
   "─" "─"
   "┬" "┬"
   "┴" "┴"
   "├" "├"
   "┤" "┤"
   "┼" "┼"
   "░" "░"
   "█" "█"
   " " " "
   "▀" "▀"
   "▄" "▄"})

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
