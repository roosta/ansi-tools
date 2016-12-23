(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'nansi.core
   :output-to "out/mies_empty.js"
   :output-dir "out"})

