(require '[cemerick.piggieback :as piggieback])
(require '[cljs.repl.node :as node])
(piggieback/cljs-repl (node/repl-env))
