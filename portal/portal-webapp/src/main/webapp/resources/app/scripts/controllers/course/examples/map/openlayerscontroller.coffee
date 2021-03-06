'use strict';

###*
 # @ngdoc function
 # @name snowApp.controller:CourseExamplesMapOpenlayerscontrollerCtrl
 # @description
 # CourseExamplesMapOpenlayerscontrollerCtrl
 # Controller of the snowApp
###
console.log "Excute CourseExamplesMapOpenlayerscontrollerCtrl"
angular.module('snowApp').controller 'CourseExamplesMapOpenlayerscontrollerCtrl', ($scope) ->
    $scope.totalDisplayed = 10;
    $scope.change=()->
        items = (i for i in [1..30])
        $scope.items = items;
    $scope.isShow=false;
    $scope.items = [3];
    $scope.buildMap = (e) ->
        baseLayer = new OpenLayers.Layer.OSM "Simple OSM Map"
        OpenLayers.Layer.XYZ.prototype.getXYZ = (bounds)->
            res = this.getServerResolution();
            maxExtent = this.maxExtent;
            if this.metedata and this.metedata.contactsLayer
                maxExtent = this.map.maxExtent
            x = Math.round((bounds.left - maxExtent.left) / (res * this.tileSize.w))
            y = Math.round((maxExtent.top - bounds.top) / (res * this.tileSize.h))
            z = this.getServerZoom();

            if this.wrapDateLine
                limit = Math.pow 2, z
                x = ((x % limit) + limit) % limit

            {'x': x, 'y': y, 'z': z}

        overlayerExtent = new OpenLayers.Bounds(-13758743.4295939, 5591455.28887228, -13531302.3472101, 5757360.4178881);
        overlayer = new OpenLayers.Layer.XYZ(
            "ESRI"
            "http://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/${z}/${y}/${x}"
            {
                isBaseLayer: !true
                maxExtent: overlayerExtent
                displayOutsideMaxExtent: false
                sphericalMercator:true
                metedata: {contactsLayer: true}
                tileOrigin: new OpenLayers.LonLat(-180, -90).transform('EPSG:4326', 'EPSG:900913')
            }
        )
        polygonLayer = new OpenLayers.Layer.Vector(
            "PolygonsLayer"
            {
                styleMap: new OpenLayers.StyleMap({
                    "default": new OpenLayers.Style({
                        graphicName: "circle"
                        pointRadius: 2.5
                        fillColor: "#ee9900"
                        fillOpacity: 0.6
                        strokeColor: "#ee9900"
                        strokeWidth: 2
                        strokeOpacity: 1
                        graphicZIndex: 1
                    })
                    "select": {
                        cursor: "pointer"
                        strokeWidth: 1
                        strokeOpacity: 1
                        graphicZIndex: 1
                    }
                })
            }
        );

        mapOptions
            div: e.target
            maxExtent: new OpenLayers.Bounds(-20037508.3427892, -20037508.3427892, 20037508.3427892, 20037508.3427892)
            layers: [
                baseLayer
                overlayer
                polygonLayer
            ]
        window.map = new OpenLayers.Map(mapOptions);
        map.setCenter(
            overlayerExtent.getCenterLonLat(), 5
        );
        map.addControl(new OpenLayers.Control.LayerSwitcher());
        polygonLayer.addFeatures([new OpenLayers.Feature.Vector(overlayerExtent.toGeometry())]);
