(defproject nansi "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/tools.cli "0.3.5"]
                 [org.clojure/clojure "1.9.0-alpha14"]
                 [sablono "0.7.7"]
                 [org.clojure/core.async "0.2.395"]
                 [org.clojure/clojurescript "1.9.293"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-npm "0.4.0"]]

  :source-paths ["script"]

  :node-dependencies [[source-map-support "0.2.8"]]

  :clean-targets ^{:protect false} ["out" "target" "main.js"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:main "nansi.core"
                                   :output-to "main.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :source-map true
                                   :target :nodejs}}]})
