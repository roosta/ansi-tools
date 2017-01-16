(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'nansi.core
   :output-to "main.js"
   :output-dir "out"})

