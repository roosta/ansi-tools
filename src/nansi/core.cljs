(ns nansi.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require
   [clojure.string :as string]
   [clojure.tools.cli :refer [parse-opts]]
   [cljs.core.async :refer [put! alts! chan <! >! timeout close!]]
   [nansi.mirror :as mirror]
   [nansi.html :as html]
   [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)
(def fs (nodejs/require "fs"))

(defn usage [options-summary]
  (->> ["ASCII/ANSI Helper library"
        ""
        "Usage: nansi [options] file"
        ""
        "Options:"
        options-summary]
       (string/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (.exit js/process status))

(defn valid-file?
  [file]
  (let [ch (chan)]
    (.access fs
             file
             (aget fs "constants" "R_OK")
             (fn [err]
               (go
                 (if err
                   (>! ch false)
                   (>! ch true)))))
    ch))

(defn read-file
  [path]
  (let [ch (chan)]
      (.readFile
       fs
       path
       "utf8"
       (fn [err data]
         (go
           (if err
             (exit 1 (.log js/console err))
             (>! ch data)))))
      ch))

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

(set! *main-cli-fn* -main)
