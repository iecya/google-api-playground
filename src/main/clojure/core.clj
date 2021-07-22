(ns core
  (:require [org.httpkit.client :as http]
            [cheshire.core :as json]))

(def ^:private google-api-uri "https://googleapis.com/auth/")

(def ^:private app-config
  (:web (json/parse-string (slurp "./private/client_secret.json") true)))

(defn get-token
  []
  (http/get (:auth_uri app-config)
            {:query-params {:client_id (:client_id app-config)
                              :redirect_uri (last (:redirect_uris app-config))
                              :response_type "code"
                              :scope (str google-api-uri "drive.metadata.readonly")}}
            (fn [res]
              (clojure.pprint/pprint res))))

(defn -main [& _args]
  (println "Hello world"))