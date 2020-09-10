(ns app.app
  (:require [keechma.next.controllers.router]
            [keechma.next.controllers.dataloader]
            [app.ui.controllers.countries]
            [app.ui.controllers.country]
            [app.ui.controllers.darkmode]
            [keechma.next.controllers.subscription]
            ["react-dom" :as rdom]))

(defn page-eq? [page] (fn [{:keys [router]}] (= page (:page router))))

(defn role-eq? [role] (fn [deps] (= role (:role deps))))

(def homepage? (page-eq? "home"))

(defn slug [{:keys [router]}] (:slug router))

(def app
  {:keechma.subscriptions/batcher rdom/unstable_batchedUpdates,
   :keechma/controllers
   {:router {:keechma.controller/params true,
             :keechma.controller/type :keechma/router,
             :keechma/routes [["" {:page "home"}]
                              ":page" ":page/:subpage"]},
    :dataloader {:keechma.controller/params true,
                 :keechma.controller/type :keechma/dataloader}
    :entitydb {:keechma.controller/params true
               :keechma.controller/type :keechma/entitydb
               :keechma.entitydb/schema
                                          {:country {:entitydb/id :id}}}
    :countries  #:keechma.controller {:deps [:router :dataloader :entitydb]
                                      :params true}
    :country #:keechma.controller {:deps [:router :dataloader :entitydb]
                                   :params (page-eq? "details")}
    :darkmode {:keechma.controller/params true}}})