(defproject game-of-life "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/koozz/game-of-life"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev
              {:plugins [[com.jakemccrary/lein-test-refresh "0.5.0"]]}}
  :test-refresh {:growl true :notify-on-success false}
  :main ^:skip-aot game-of-life.core
  :target-path "target/%s")
