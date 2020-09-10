(ns app.ui.components.card
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma]]
            [helix.hooks :as hooks]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified Content :div "w-full px-10 pt-4 pb-8 flex flex-col text-sm shadow-sm")

(defnc Title [props]
       (d/div {:class (str "font-bold text-lg mb-2 " (if (:darkmode? props) " text-white " " text-black "))}
              (:title props)))

(defnc Population [props]
       (d/div {:class (str "font-bold" (if (:darkmode? props) " text-white " " text-black "))}
              "Population: "
              (d/span
                {:class "text-darkGrayLightMode font-thin"}
                (:population props))))

(defnc Region [props]
       (d/div {:class (str "font-bold" (if (:darkmode? props) " text-white " " text-black "))}
              "Region: "
              (d/span
                {:class "text-darkGrayLightMode font-thin"}
                (:region props))))

(defnc Capital [props]
       (d/div {:class (str "font-bold" (if (:darkmode? props) " text-white " " text-black "))}
              "Capital: "
              (d/span
                {:class "text-darkGrayLightMode font-thin"}
                (:capital props))))


(defnc CardRenderer [props]
       (let [data (:data props)]
         (d/div {:class    (str " mx-10 my-6 cursor-pointer " (if (:darkmode? props) " bg-darkBlueDarkMode " " bg-white "))
                 :style    {:width "250px"}
                 :on-click #(router/redirect! props :router {:page "details" :subpage (:name data)})}
                (d/div {:class "w-full h-32"}
                       (d/img {:src (:flag data) :style {:maxWidth "100%" :maxHeight "100%"
                                                         :width "100%"}}))
                ($ Content
                   ($ Title {:darkmode? (:darkmode? props) :title (:name data)})
                   ($ Population {:darkmode? (:darkmode? props) :population "81.142.431" & data})
                   ($ Region {:darkmode? (:darkmode? props) :region "Europe" & data})
                   ($ Capital {:darkmode? (:darkmode? props) :capital "Berlin" & data})))))


(def Card (with-keechma CardRenderer))

