(ns ansi-tools.mirror
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
  [file]
  (let [lines (s/split-lines file)]
    (reverse
     (reduce
      (fn [acc line]
        (let [r (apply str (reverse line))
              c (s/replace r regex #(get pairs %))]
          (conj acc c)))
      '()
      lines))))

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

(defn main
  [file]
  (let [lines (replace-char file)]
    (doseq [l (join-line-padding lines)]
      (println l))))
