(defproject nansi "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/tools.cli "0.3.5"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.293"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-npm "0.4.0"]]

  :main ^:skip-aot nansi.core

  :target-path "target/%s"

  :source-paths ["src/clj"]

  :node-dependencies [[source-map-support "0.2.8"]]

  :clean-targets ^{:protect false} ["out" "target"]
  
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :compiler {:main 'nansi.core
                                   :output-to "out/main.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :source-map true
                                   :target :nodejs}}]}

  :profiles {:uberjar {:aot :all}})
