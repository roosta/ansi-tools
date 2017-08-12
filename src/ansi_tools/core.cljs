(ns ansi-tools.core
  (:require
   [clojure.string :as s]
   [cljs.tools.cli :refer [parse-opts]]
   [ansi-tools.mirror :as mirror]
   [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)
(def fs (nodejs/require "fs"))
(def read-file (.-readFileSync fs))

(defn usage [options-summary]
  (->> ["ASCII/ANSI Helper library"
        ""
        "Usage: ansi-tools [options] file"
        ""
        "Options:"
        options-summary]
       (s/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (s/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (.exit js/process status))

(def cli-options
  [["-m" "--mirror" "Mirror input"]
   ["-h" "--help"]])

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 (usage summary))
      (not= (count arguments) 1) (exit 1 (usage summary))
      (:mirror options) (mirror/main (read-file (first arguments)))
      errors (exit 1 (error-msg errors)))))
