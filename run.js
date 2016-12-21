try {
    require("source-map-support").install();
} catch(err) {
}
require("./out/goog/bootstrap/nodejs.js");
require("./out/main.js");
goog.require("main.core");
goog.require("cljs.nodejscli");
