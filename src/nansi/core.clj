(ns nansi.core
  (:require [clojure.string :as s]
            [nansi.mirror :as m])
  (:gen-class))

(defn -main
  [& args]
  (m/output)
  )
