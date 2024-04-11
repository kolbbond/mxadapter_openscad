(ns scad-demo.core
  (:use [scad-clj.scad])
  (:use [scad-clj.model]))

; Dimensions 
(def fs2_dist_btw_plugs 5.43)
;(def fs2_dist_btw_plugs 2.71)
(def fs2_plug_width 2.53)
(def fs2_plug_thick 0.8)
(def fs2_plug_height 3.00)
(def bridge_width   8.00)
(def bridge_thick   10.00)
(def bridge_height  1.20)
(def mx_width  4.40)
(def mx_thick  0.80)
(def mx_height  4.95)
(def fs2_support_height 1.00)
(def fs2_support_thick  0.40)

; plugs into freestyle2 switch
(def fs2_plugs
  (union
   (translate [(/ (+ fs2_dist_btw_plugs fs2_plug_thick) 2) 0 0]
              (cube fs2_plug_thick fs2_plug_width fs2_plug_height))
   (translate [(- (/ (+ fs2_dist_btw_plugs fs2_plug_thick) 2)) 0 0]
              (cube fs2_plug_thick fs2_plug_width fs2_plug_height))))

; bridge between adapter
(def bridge
  (union
   (cube bridge_thick bridge_width bridge_height)
   (translate [0 0 (- (/ bridge_height 2))]
              (cube   bridge_width fs2_support_thick fs2_support_height))))

; mx plugs
(def mx_plugs
  (union
   (cube mx_thick mx_width mx_height)
   (cube mx_width mx_thick mx_height)))

; combine plugs
(def plugs_with_bridge
  (union
   fs2_plugs
   (translate [0 0 (- fs2_plug_height bridge_height)]
              bridge)
   (translate [0 0 (+ bridge_height fs2_plug_height)]
              mx_plugs)))

; output to scad
(spit "models/post-demo.scad"
      (write-scad plugs_with_bridge))
