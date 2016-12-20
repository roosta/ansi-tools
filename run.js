try {
    require("source-map-support").install();
} catch(err) {
}
require("./out/goog/bootstrap/nodejs.js");
require("./out/nansi.js");
goog.require("nansi.core");
goog.require("cljs.nodejscli");
