(ns app.ui.components.search
  (:require [helix.dom :as d]
            [keechma.next.helix.core :refer [with-keechma dispatch]]
            [keechma.next.helix.classified :refer [defclassified]]
            [helix.core :as hx :refer [$]]
            [helix.hooks :as hooks]
            [app.settings :refer [regions]]
            ["semantic-ui-react" :refer (Dropdown Search)]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified InputsWrap :div "w-full flex justify-between")

(defclassified ComboboxWrapper :div "")

(defnc SearchWrapRenderer [props]
       (let [ref (hooks/use-ref nil)]
         ($ InputsWrap
            (d/div {:class (str "relative shadow-md rounded-lg " (if (:darkmode? props) " bg-darkBlueDarkMode "  " bg-white"))
                    :style {:width            "320px"}}
                   (d/div {:style    {:position         "relative"
                                      :top              0
                                      :left             0
                                      :width            "35px"
                                      :height           "100%"}
                           :class    (str "cursor-pointer " (if (:darkmode? props) " bg-darkBlueDarkMode " " bg-white "))
                           :on-click #(dispatch props :countries :search-by-country ref)}
                          (d/i {:class (str "fa fa-search absolute " (if (:darkmode? props) " text-white " " text-darkGrayLightMode "))
                                :style {:top       "50%"
                                        :left      "50%"
                                        :z-index   10
                                        :transform "translate(-50%,-65%)"}}))
                   (d/input
                     {:type        "text"
                      :class       (str "py-3 text-md font-thin top-0 outline-none px-2 rounded-lg absolute" (if (:darkmode? props) " bg-darkBlueDarkMode text-white " " bg-white " ))
                      :placeholder "Search for a country..."
                      :style       {:left  "35px"
                                    :width "285px"}
                      :ref         ref}))
            ($ ComboboxWrapper
               ($ Dropdown
                  {:options     (clj->js regions)
                   :selection   true
                   :onChange    (fn [e v] (dispatch props :countries :filter-by-region (.-value v)))
                   :clearable   true
                   :placeholder "Filter by Region"})))))

(def SearchWrap (with-keechma SearchWrapRenderer))