(ns nansi.mirror
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [clojure.string :as s]
            [cljs.nodejs :as nodejs]))

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
  [file]
  (let [ascii (s/split-lines file)]
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
  [lines]
  (let [longest-length (-> (sort-by count lines)
                           last
                           count)]
    (for [line lines
          :let [padding-length (- longest-length (count line))]]
      (apply str (repeat padding-length \space)))))

(defn join-line-padding
  [lines]
  (let [padding (get-padding lines)]
    (map-indexed
     (fn [idx line]
       (str (nth padding idx) line))
     lines)))

(defn output
  [ch]
  (go (let [lines (reverse (replace-char (<! ch)))]
        (doseq [l (join-line-padding lines)]
          (println l))))
  )
