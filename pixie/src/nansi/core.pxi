#!/usr/bin/env pixie-vm
(ns nansi.core
  (:require [pixie.io :as io]
            [pixie.string :as s]
            ;[hiccup.core :refer :all]
            ))

(def symbols {"┌" "&#9484;"
              "┐" "&#9488;"
              "└" "&#9492;"
              "┘" "&#9496;"
              "│" "&#9474;"
              "─" "&#9472;"
              "░" "&#9617;"
              "█" "&#9608;"
              "▀" "&#9600;"
              "▄" "&#9604;"})

;(defn read-file [file]
  ;(-> (io/slurp file)
      ;(string/split-lines)))

;(defn swap-char [i]
  ;(doseq [[k v] symbols]
    ;(s/replace i k v))
  ;)

(defn swap-char [i]
  (let [result []]
    (doseq [[k v] symbols]

      ))
  )

;(defn asd [i]
  ;(s/replace i #"[┌┐└┘│─░█▀▄]" #(get symbols %1)))

;(defn generate-html [input]
  ;(html [:span.test
          ;input]))

;(println "hello")
(println(asd "┌───────────────────────────────────────────────────────────────────────┐"))
;(defn main [])
;(println (first (read-file "ansi.txt")))
