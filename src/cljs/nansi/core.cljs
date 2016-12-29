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
  (->> ["This is my program. There are many like it, but this one is mine."
        ""
        "Usage: program-name [options] action"
        ""
        "Options:"
        options-summary
        ""
        "Actions:"
        "  start    Start a new server"
        "  stop     Stop an existing server"
        "  status   Print a server's status"
        ""
        "Please refer to the manual page for more information."]
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
             (>! ch err)
             (>! ch data)))))
      ch))

(def cli-options
  ;; An option with a required argument
  [["-m" "--mirror" "Mirror input"
    ;; :default 80
    ;; :parse-fn #(Integer/parseInt %)
    ;; :validate [#(< 0 % 0x10000) "Must be a valid file"]
    ;; :validate [#(valid-file? %)
    ;;            "Must be a valid file"]
    ]
   ["-H" "--html" "output escaped html"]
   ;; A non-idempotent option
   ["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :assoc-fn (fn [m k _] (update-in m [k] inc))]
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 (usage summary))
      (not= (count arguments) 1) (exit 1 (usage summary))
      (:mirror options) (mirror/output (read-file (first arguments)))
      (:html options) (html/output (read-file (first arguments)))
      errors (exit 1 (error-msg errors)))

    #_(println options)
    ;; Execute program with options
    #_(case (first arguments)
      "valid-file" (go (.log js/console (<! (valid-file? "resources/partial.txt"))))
      "read-file" (go (.log js/console (<! (read-file "resources/partial.txt"))))
      ;; "mirror" (mirror/output)
      "test" (if (> (count arguments) 1)
               (.log js/console (last arguments))
               (exit 1 (usage summary))
               )
      "mirror" (mirror/output (read-file "resources/partial.txt"))
      "options" (println options)
      (exit 1 (usage summary)))))

(set! *main-cli-fn* -main)
